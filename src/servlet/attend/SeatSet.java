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

@WebServlet(urlPatterns={"/servlet/attend/SeatSet"})
public class SeatSet extends HttpServlet {
	public void doPost (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {

		try {
			//今年度取得用当日日付
		    long miliseconds = System.currentTimeMillis();
		    Date date = new Date(miliseconds);

			// ClassC99DAO:クラス一覧取得テスト用DAO
			ClassC99DAO dao = new ClassC99DAO();
			List<ClassC> list = dao.selectAll(date);

			//クラスリストのrequest作成
			request.setAttribute("classList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//表示画面呼び出し
		request.getRequestDispatcher("/attend/seatSet.jsp")
			.forward(request, response);
	}

	//GETメソッド用（未使用）
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
			doPost(request, response);
	}
}
