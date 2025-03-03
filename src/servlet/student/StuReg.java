package servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet(urlPatterns={"/stureg"})
public class StuReg extends HttpServlet {
	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();

		StudentDao stuDao=new StudentDao();
		int maxId=stuDao.getStudentMaxID();

		Student stu = new Student();
		stu.setStudentId(maxId+1);

		request.getRequestDispatcher("stuReg.jsp")
		.forward(request, response);

	}
}