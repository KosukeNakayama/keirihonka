package servlet.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

//@WebServlet(urlPatterns={"/sturegupd"})
public class StuUpd extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();

		String studentId=request.getParameter("studentId");

		StudentDao dao = new StudentDao();
		List<Student> stuUpdList = dao.searchById(studentId);

		request.setAttribute("stuUpdList", stuUpdList);

		request.getRequestDispatcher("stuUpdExe.jsp")
		.forward(request, response);


	}
}