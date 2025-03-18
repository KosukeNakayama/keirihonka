package servlet.classC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassHistoryExp;
import bean.Student;
import dao.ClassHistoryDao;
import dao.StudentDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsHstGetStu"})
public class ClsHstGetStu extends HttpServlet {

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
				String studentIdStr = null;
				StudentDao stuDao = new StudentDao();
				ClassHistoryDao clsHstDao = new ClassHistoryDao();
				HttpSession session = request.getSession();
				Student stu;
				ArrayList<ClassHistoryExp> clsHstLst = new ArrayList();

				//リクエストパラメータ―の取得 2
				studentIdStr = request.getParameter("studentId");

				if(Objects.isNull(studentIdStr)){
					request.setAttribute("message", "学生番号を選択してください");
					request.getRequestDispatcher("/class/ClsStuReg.jsp").forward(request, response);
					return;
				}

				//DBからデータ取得 3
				//該当する学生を取得する
				stu = stuDao.getStudent(studentIdStr);
				clsHstLst = clsHstDao.getHistory(studentIdStr);

				//ビジネスロジック 4
				//なし
				//DBへデータ保存 5
				//なし
				//レスポンス値をセット 6
				request.setAttribute("stu", stu);
				request.setAttribute("clsHstLst", clsHstLst);

				//JSPへフォワード 7
				request.getRequestDispatcher("/class/ClsHst.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

}
