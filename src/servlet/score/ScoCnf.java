package servlet.score;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import dao.ScoreDao;

@WebServlet(urlPatterns = { "/score/ScoCnf" })
public class ScoCnf extends HttpServlet{
	ScoreDao scdao = new ScoreDao();
	List<Score> list;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String student_id = req.getParameter("student_id");
		try{
			list=scdao.searchStudent(student_id);
			System.out.println(list.size() + "件");
			req.setAttribute("list", list);
			// JSPページにフォワードして、結果を表示
			req.getRequestDispatcher("scoCnf.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("errorMessage", "データの取得中にエラーが発生しました。");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}

}
