package servlet.classC;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsHst"})
public class ClsHst extends HttpServlet {

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

			//ローカル変数の宣言 1
			ClassCDao clsDao = new ClassCDao();
			HttpSession session = request.getSession();

			//リクエストパラメータ―の取得 2
			//なし

			//DBからデータ取得 3

			//ビジネスロジック 4
			//なし

			//DBへデータ保存 5
			//なし

			//レスポンス値をセット 6

			//JSPへフォワード 7
			request.getRequestDispatcher("/class/ClsHst.jsp").forward(request, response);

		}

}
