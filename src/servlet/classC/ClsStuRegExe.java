package servlet.classC;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassC;
import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsStuRegExe"})
public class ClsStuRegExe extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

			//System.out.println("ClsStuRegExe");
			//ログインチェック
			if(!Checker.isLogined(request)){
				String message = "ログインしてください";
				HttpSession session = request.getSession();
				session.invalidate();
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			//管理職チェック
			if(!Checker.isManage(request)){
				String message = "管理職でないとアクセスできません";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				return;
			}

			try {
				//ローカル変数の宣言 1
				String classIdStr;
				int classId;
				String dayOfChangeStr;
				Calendar dayOfChange;
				ClassCDao clsDao = new ClassCDao();
				String[] stuIds;
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				HttpSession session = request.getSession();
				int schoolYear = 0;
				ClassC cls = null;

				//リクエストパラメータ―の取得 2
				classIdStr = request.getParameter("classId");
				dayOfChangeStr = request.getParameter("dayOfChange");
				stuIds = request.getParameterValues("stuIds");

				//System.out.println(!Objects.isNull(schoolYearStr) && !Objects.isNull(gradeStr) && !Objects.isNull(classNoStr) && !Objects.isNull(startDateStr)&& !startDateStr.isEmpty() && !Objects.isNull(stuIds));

				//if(!Objects.isNull(schoolYearStr) && !Objects.isNull(gradeStr) && !Objects.isNull(classNoStr) && !Objects.isNull(startDateStr)&& !startDateStr.isEmpty() && !Objects.isNull(stuIds)){
				if(!Objects.isNull(classIdStr) && !Objects.isNull(dayOfChangeStr) && !Objects.isNull(stuIds)){
					classId = Integer.parseInt(classIdStr);
					//dateのオブジェクト・・・？
					dayOfChange = Calendar.getInstance();
					dayOfChange.setTime(sf.parse(dayOfChangeStr));
				}else{
					request.setAttribute("message", "学生を選択してください");
					request.getRequestDispatcher("/class/ClsStuReg.jsp").forward(request, response);
					return;
				}

				//DBからデータ取得 3
				cls = clsDao.searchById(classId);

				//ビジネスロジック 4
				if(!Checker.isWithinSchoolYearAndBeforeClassDate(cls, dayOfChange)){
					request.setAttribute("message", "年度内かつ、クラスの作成日より後の日付を入力して下さい");
					request.getRequestDispatcher("/class/ClsStuReg.jsp").forward(request, response);
					return;
				}
				//保存する学生
				//DBへデータ保存 5

				clsDao.regClsStu(classId,stuIds,dayOfChange);

				//レスポンス値をセット 6
				request.setAttribute("message","クラスの学生登録が完了しました");
				session.removeAttribute("stuList");
				session.removeAttribute("clsList");

				//JSPへフォワード 7
				request.getRequestDispatcher("/main.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
