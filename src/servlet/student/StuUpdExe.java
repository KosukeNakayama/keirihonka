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

@WebServlet(urlPatterns={"/student/stuUpdExe"})
public class StuUpdExe extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");

		try {
			String studentId=request.getParameter("studentId");
			String studentName=request.getParameter("studentName");

			Student p=new Student();
			p.setStudentId(studentId);
			p.setStudentName(studentName);

			StudentDao dao=new StudentDao();
			int line = dao.update(p);

			if(line>0){
			request.getRequestDispatcher("/student/stuRegSuccess.jsp")
			.forward(request, response);
			return;
			}

			request.getRequestDispatcher("/student/stuRegError.jsp")
			.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(out);
		}

	}
}
