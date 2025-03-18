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

import bean.StudentExp;
import dao.StudentDao;

@WebServlet(urlPatterns={"/Class/StuSearch"})
public class StuSearch extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

			try {
				//ローカル変数の宣言 1
				String stuName;
				StudentDao stuDao = new StudentDao();
				HttpSession session = request.getSession();

				//リクエストパラメータ―の取得 2
				stuName = request.getParameter("name");

				if(Objects.isNull(stuName)){
					stuName = "";
				}

				//DBからデータ取得 3
				//該当する学生の一覧を取得する
				ArrayList<StudentExp> stuList;
				stuList = stuDao.searchByName(stuName);

				//ビジネスロジック 4
				//DBへデータ保存 5
				//なし
				//レスポンス値をセット 6
				request.setAttribute("stuList", stuList);
				request.setAttribute("isPopup", true);
				request.setAttribute("name",stuName);

				//JSPへフォワード 7
				request.getRequestDispatcher("/class/ClsHst.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

}
