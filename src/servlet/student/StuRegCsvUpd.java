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

@WebServlet(urlPatterns={"/student/sturegcsvupd"})
public class StuRegCsvUpd extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		// 文字化け対策
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");

		PrintWriter out=response.getWriter();

        // 登録人数を取得
        int Count = Integer.parseInt(request.getParameter("Count"));
        //DBに登録できた人数のカウント変数を初期化
        int line = 0;

        //学生情報の登録
		for(int i=1; i<=Count ; i++){

			try {
				Integer enrollmentYear=Integer.parseInt(request.getParameter("stu"+i+"EnrollmentYear"));
				String studentId=request.getParameter("stu"+i+"StudentId");
				String studentName=request.getParameter("stu"+i+"Name");
				Integer courseId=Integer.parseInt(request.getParameter("stu"+i+"CourseId"));

				Student p=new Student();
				p.setStudentId(studentId);
				p.setStudentName(studentName);
				p.setEnrollmentYear(enrollmentYear);
				p.setCourseId(courseId);

				StudentDao dao=new StudentDao();
				line = line + dao.insert(p);

			} catch (Exception e) {
				e.printStackTrace(out);
			}
		}

		if(line==Count){
		request.getRequestDispatcher("stuRegSuccess.jsp")
		.forward(request, response);
		}

		request.getRequestDispatcher("stuRegError.jsp")
		.forward(request, response);


	}
}
