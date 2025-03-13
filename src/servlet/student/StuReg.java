package servlet.student;

import java.io.IOException;

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

		StudentDao stuDao=new StudentDao();
		int maxId = 0;
		try {
			maxId = stuDao.getStudentMaxID();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		Student stu = new Student();
		stu.setStudentId(String.valueOf(maxId+1));

		request.getRequestDispatcher("/student/stuReg.jsp")
		.forward(request, response);

	}
}