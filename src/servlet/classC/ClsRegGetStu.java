package servlet.classC;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.StudentExp;
import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsRegGetStu"})
public class ClsRegGetStu extends HttpServlet {

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
				String schoolYearStr;
				int schoolYear;
				String gradeStr;
				int grade;
				String classNoStr;
				int classNo;
				String startDateStr;
				Calendar startDate;
				ClassCDao clsDao = new ClassCDao();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				HttpSession session = request.getSession();

				//リクエストパラメータ―の取得 2
				schoolYearStr = request.getParameter("schoolYear");
				gradeStr = request.getParameter("grade");
				classNoStr = request.getParameter("classNo");
				startDateStr = request.getParameter("startDate");

				if(!Objects.isNull(schoolYearStr) && !Objects.isNull(gradeStr) && !Objects.isNull(classNoStr) && !Objects.isNull(startDateStr)){
					schoolYear = Integer.parseInt(schoolYearStr);
					grade = Integer.parseInt(gradeStr);
					classNo = Integer.parseInt(classNoStr);
					//dateのオブジェクト・・・？
					startDate = Calendar.getInstance();
					startDate.setTime(sf.parse(startDateStr));

				}else{
					request.setAttribute("message", "項目は必須入力です");
					request.getRequestDispatcher("/class/ClsReg.jsp").forward(request, response);
					return;
				}

				//DBからデータ取得 3
				//該当する学生の一覧を取得する
				ArrayList<StudentExp> stuList;
				stuList = clsDao.getStudentCandidateList(schoolYear,grade,startDate);


				//ビジネスロジック 4
				//なし
				//DBへデータ保存 5
				//なし
				//レスポンス値をセット 6
				session.setAttribute("stuList", stuList);
				request.setAttribute("schoolYear", schoolYear);
				request.setAttribute("grade", grade);
				request.setAttribute("classNo", classNo);
				request.setAttribute("startDate", startDateStr);
				//JSPへフォワード 7
				request.getRequestDispatcher("/class/ClsReg.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

}
