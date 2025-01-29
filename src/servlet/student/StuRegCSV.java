package servlet.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.Student;
import dao.StudentDao;

/**
 * Servlet implementation class StuRegCSV
 */
@WebServlet("/StuRegCSV")@MultipartConfig(
		maxFileSize=10000000,
		maxRequestSize=10000000,
		fileSizeThreshold=10000000
	)
public class StuRegCSV extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 送信情報の取得
		Part csv = request.getPart("csv");
		BufferedReader br = null;
		// csv読み込み
		InputStream is = csv.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		String line;
		ArrayList<Student> stuList = new ArrayList();
		StudentDao stuDao = new StudentDao();
		int maxId = stuDao.getStundetMaxId();

		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			//学生Bean
			Student stu = new Student();
			stu.setStudentName(data[0]);
			stu.setCourseId(Integer.parseInt(data[1]));
			stu.setEnrollmentYear(2024);
			stu.setStudentId(++maxId);
			stuList.add(stu);
		}

		//listの中にcsvに記入されていた学生の情報が詰まっている
		request.setAttribute("stuList", stuList);

		//jsp
		request.getRequestDispatcher("stuList.jsp")
		.forward(request, response);
    }
}

