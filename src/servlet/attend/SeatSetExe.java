package servlet.attend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassC99DAO;

@WebServlet(urlPatterns={"/servlet/attend/SeatSetExe"})
public class SeatSetExe extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		//POSTされ値を取得
		String className = request.getParameter("className");
		int seatRow = Integer.parseInt(request.getParameter("seatRow"));
		int seatCol = Integer.parseInt(request.getParameter("seatCol"));
//		System.out.println("className:" + className + " seatRow:" + seatRow+ " seatCol:" + seatCol);
		String[] students = request.getParameterValues("studentId");
//		System.out.println(Arrays.toString(students));
		int classId = Integer.parseInt(request.getParameter("classId"));
//		System.out.println("classId:"+classId);
		String[] seatNosString = request.getParameterValues("seatNo");
//		int[] seatNosInteger = Stream.of(seatNosString).mapToInt(Integer::parseInt).toArray();
//		System.out.println(Arrays.toString(seatNosInteger));


		try {
			//ClassHistory追加
			ClassC99DAO classHistoryDao = new ClassC99DAO();
			classHistoryDao.updateClassHistory(classId, students, seatNosString);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		request.getRequestDispatcher("/attend/seatSetExe.jsp")
//			.forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
	}
}