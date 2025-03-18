package servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet(urlPatterns={"/student/stuupd"})
public class StuUpd extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {

		String studentId=request.getParameter("studentId");

		StudentDao dao = new StudentDao();
		Student stuUpdList = null;
		try {
			stuUpdList = dao.getStudent(studentId);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("student", stuUpdList);

		request.getRequestDispatcher("stuUpdExe.jsp")
		.forward(request, response);


	}
}