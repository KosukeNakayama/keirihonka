package servlet.classC;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsReg"})
public class ClsReg extends HttpServlet {

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
			//なし

			//リクエストパラメータ―の取得 2
			//なし

			//DBからデータ取得 3
			//なし

			//ビジネスロジック 4
			//なし

			//DBへデータ保存 5
			//なし

			//レスポンス値をセット 6
			//なし

			//JSPへフォワード 7
			request.getRequestDispatcher("/class/ClsReg.jsp").forward(request, response);

		}

}
