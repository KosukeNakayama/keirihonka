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

import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsUpdExe"})
public class ClsUpdExe extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

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
				String schoolYearStr;
				int schoolYear;
				String gradeStr;
				int grade;
				String classNoStr;
				int classNo;
				String startDateStr;
				Calendar startDate;
				ClassCDao clsDao = new ClassCDao();
				//String[] stuIds;
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				HttpSession session = request.getSession();

				//リクエストパラメータ―の取得 2
				classIdStr = request.getParameter("classId");
				schoolYearStr = request.getParameter("schoolYear");
				gradeStr = request.getParameter("grade");
				classNoStr = request.getParameter("classNo");
				startDateStr = request.getParameter("startDate");
				//stuIds = request.getParameterValues("stu");

				//System.out.println(!Objects.isNull(schoolYearStr) && !Objects.isNull(gradeStr) && !Objects.isNull(classNoStr) && !Objects.isNull(startDateStr)&& !startDateStr.isEmpty() && !Objects.isNull(stuIds));

				//if(!Objects.isNull(schoolYearStr) && !Objects.isNull(gradeStr) && !Objects.isNull(classNoStr) && !Objects.isNull(startDateStr)&& !startDateStr.isEmpty() && !Objects.isNull(stuIds)){
				if(!Objects.isNull(classIdStr) && !Objects.isNull(schoolYearStr) && !Objects.isNull(gradeStr) && !Objects.isNull(classNoStr) && !Objects.isNull(startDateStr)&& !startDateStr.isEmpty()){
					classId = Integer.parseInt(classIdStr);
					schoolYear = Integer.parseInt(schoolYearStr);
					grade = Integer.parseInt(gradeStr);
					classNo = Integer.parseInt(classNoStr);
					//dateのオブジェクト・・・？
					startDate = Calendar.getInstance();
					startDate.setTime(sf.parse(startDateStr));

					if(!Checker.isWithinSchoolYear(schoolYear, startDate)){
						request.setAttribute("message", "指定した年度の日付を入力して下さい");
						request.getRequestDispatcher("/class/ClsReg.jsp").forward(request, response);
						return;
					}

				}else{
					request.setAttribute("message", "項目は必須入力です");
					request.getRequestDispatcher("/class/ClsReg.jsp").forward(request, response);
					return;
				}

				//DBからデータ取得 3
				//なし
				//ビジネスロジック 4
				//保存する学生
				//DBへデータ保存 5

				clsDao.updCls(classId,schoolYear,grade,classNo,startDate);

				//レスポンス値をセット 6
				request.setAttribute("message","クラスの更新が完了しました");
				session.removeAttribute("stuList");

				//JSPへフォワード 7
				request.getRequestDispatcher("/class/ClsUpd.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
