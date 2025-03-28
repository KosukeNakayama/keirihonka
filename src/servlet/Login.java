package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ログインページ表示
@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp")
		.forward(request, response);
    }

	public void doPet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
    }

}

