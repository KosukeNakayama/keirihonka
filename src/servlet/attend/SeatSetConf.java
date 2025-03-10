package servlet.attend;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassHistory;
import bean.StudentExp;
import dao.ClassC99DAO;

@WebServlet(urlPatterns={"/servlet/attend/SeatSetConf"})
public class SeatSetConf extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		//POSTされた値を取得
		String className = request.getParameter("className");
		int seatRow = Integer.parseInt(request.getParameter("seatRow"));
		int seatCol = Integer.parseInt(request.getParameter("seatCol"));
		String[] students = request.getParameterValues("studentId");
		String[] studentNames = request.getParameterValues("studentName");
		int classId = Integer.parseInt(request.getParameter("classId"));
		String[] seatNosString = request.getParameterValues("seatNo");

//		if (className.equals("noSelect")) {
//			request.getRequestDispatcher("/attend/seatSet.jsp").forward(request, response);
//		}

		//ヘッダー部登録（bean化した府が良い？）
		SeatHeader sh = new SeatHeader();
		sh.setClassName(className);
		sh.setSeatRow(seatRow);
		sh.setSeatCol(seatCol);
		request.setAttribute("seatHeader", sh);

		//クラス名をgradeとclassNoに分解
		int endIndex = className.lastIndexOf('-');
		int grade = Integer.parseInt(className.substring(0, endIndex));
		int classNo = Integer.parseInt(className.substring(endIndex + 1));

		//今年度取得用当日日付
	    long miliseconds = System.currentTimeMillis();
	    Date date = new Date(miliseconds);


		try {
			//学生座席情報取得
			ClassC99DAO classDao = new ClassC99DAO();
			List<StudentExp> list = new ArrayList<>();
			List<StudentExp> stuConf = new ArrayList<>();

			int size = students.length;

			for(int i = 0; i < size; i++) {
				StudentExp stu = new StudentExp();
				stu.setStudentId(students[i]);
				stu.setStudentName(studentNames[i]);

//				System.out.println("id:" + students[i] + " name:" + studentNames[i]);

				ClassHistory ch = new ClassHistory();
				ch.setStudentId(students[i]);
				ch.setClassId(classId);
				if (Objects.isNull(seatNosString[i]) | seatNosString[i].isEmpty()) {
					ch.setSeatNo(java.sql.Types.NULL);
				} else {
					ch.setSeatNo(Integer.parseInt(seatNosString[i]));
				}
				stu.setClassHistoryList(ch);
				list.add(stu);
			}

			stuConf = list;
			request.setAttribute("stuListConf", stuConf);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/attend/seatSetConf.jsp")
			.forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
	}

}