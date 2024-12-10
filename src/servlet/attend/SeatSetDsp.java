package servlet.attend;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassC;
import dao.ClassC99DAO;

@WebServlet(urlPatterns={"/servlet/attend/SeatSetDsp"})
public class SeatSetDsp extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

			//POSTされたクラス名を取得
			String className = request.getParameter("className");

			//クラス名をgradeとclassNoに分解
			int endIndex = className.lastIndexOf('-');
			int grade = Integer.parseInt(className.substring(0, endIndex));
			int classNo = Integer.parseInt(className.substring(endIndex + 1));

			//今年度取得用当日日付
		    Calendar calendar = Calendar.getInstance();
		    Date date = calendar.getTime();

			try {
				ClassC99DAO classDao = new ClassC99DAO();
				ClassC99DAO stu = classDao.searchByNo(grade, classNo, date);

				request.setAttribute("student", stu);

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.getRequestDispatcher("/chap20/mondai13disp.jsp")
				.forward(request, response);
		}
		request.getRequestDispatcher("/attend/seatSet.jsp")
			.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
}
}