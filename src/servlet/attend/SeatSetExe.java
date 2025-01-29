package servlet.attend;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassC;
import dao.ClassC99DAO;

@WebServlet(urlPatterns={"/servlet/attend/SeatSetExe"})
public class SeatSetExe extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		//POSTされた値を取得
		String className = request.getParameter("className");
		int seatRow = Integer.parseInt(request.getParameter("seatRow"));
		int seatCol = Integer.parseInt(request.getParameter("seatCol"));
		String[] students = request.getParameterValues("studentId");
		int classId = Integer.parseInt(request.getParameter("classId"));
		String[] seatNosString = request.getParameterValues("seatNo");

		try {
			//ClassHistory追加
			ClassC99DAO classHistoryDao = new ClassC99DAO();
			classHistoryDao.updateClassHistory(classId, students, seatNosString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			//今年度取得用当日日付
			long miliseconds = System.currentTimeMillis();
			Date date = new Date(miliseconds);

			// ClassC99DAO:クラス一覧取得テスト用DAO
			ClassC99DAO dao = new ClassC99DAO();
			List<ClassC> list = dao.selectAll(date);

			//SelectBox用クラスリストのrequest作成
			request.setAttribute("classList", list);

			//登録完了画面に遷移
			request.getRequestDispatcher("/attend/seatSetExe.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
	}
}