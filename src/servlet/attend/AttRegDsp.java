package servlet.attend;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentExp;
import dao.Attendance99DAO;

@WebServlet(urlPatterns={"/servlet/attend/AttRegDsp"})
public class AttRegDsp extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		//POSTされ値を取得
		String paramString = request.getParameter("paramString");
		String[] splitArray = paramString.split(",");
		String className = splitArray[0];

		if (className.equals("noSelect")) {
			request.getRequestDispatcher("/attend/attReg.jsp").forward(request, response);
		}

		int seatRow = Integer.parseInt(splitArray[1]);
		int seatCol = Integer.parseInt(splitArray[2]);
//		System.out.println("class:" + className + " row:" + seatRow + " col:" + seatCol);

		//ヘッダー部登録（bean化した方が良い？）
		SeatHeader sh = new SeatHeader();
		sh.setClassName(className);
		sh.setSeatRow(seatRow);
		sh.setSeatCol(seatCol);
		request.setAttribute("seatHeader", sh);

		//クラス名をgradeとclassNoに分解
		int endIndex = className.lastIndexOf('-');
		int grade = Integer.parseInt(className.substring(0, endIndex));
		int classNo = Integer.parseInt(className.substring(endIndex + 1));

		//座席配置情報取得


		//今年度取得用当日日付
	    long miliseconds = System.currentTimeMillis();
	    Date date = new Date(miliseconds);


		try {
			//学生座席情報取得
			Attendance99DAO classDao = new Attendance99DAO();
			List<StudentExp> stu = classDao.searchByClass(grade, classNo, date);

			request.setAttribute("stuList", stu);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/attend/attRegDsp.jsp").forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
	}
}