package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Users;
import dao.UserDao;


@WebServlet(urlPatterns={"/loginExe"})
public class LoginExe extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ローカル変数の宣言 1
		String id;
		String pw;
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		//リクエストパラメータ―の取得 2
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		//DBからデータ取得 3
		Users user;
		try {
			user = userDao.getUser(id,pw);
			if(Objects.isNull(user.getUserId())){
				String message = "IDかPWが正しくありません。もう一度入力してください。";
				request.setAttribute("message", message);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			String message = "システムエラー \n もう一度ログイン画面からやり直してください。";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//ビジネスロジック 4
		session.setAttribute("user", user);
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		request.getRequestDispatcher("main.jsp").forward(request, response);
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "もう一度ログイン画面からやり直してください。";

		request.setAttribute("message", message);
		request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}

