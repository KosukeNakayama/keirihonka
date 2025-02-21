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

@WebServlet(urlPatterns={"/sturegupd"})
public class StuRegUpd extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();

		try {
			Integer enrollmentYear=Integer.parseInt(request.getParameter("enrollmentYear"));
			String studentId=request.getParameter("studentId");
			String studentName=request.getParameter("studentName");
			Integer courseId=Integer.parseInt(request.getParameter("courseId"));

			Student p=new Student();
			p.setStudentId(studentId);
			p.setStudentName(studentName);
			p.setEnrollmentYear(enrollmentYear);
			p.setCourseId(courseId);

			StudentDao dao=new StudentDao();
			int line = dao.insert(p);

			if(line>0){
			request.getRequestDispatcher("stuRegSuccess.jsp")
			.forward(request, response);
			}

			request.getRequestDispatcher("stuRegError.jsp")
			.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(out);
		}


	}
}
