package servlet.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassC;
import bean.Score;
import bean.Student;
import bean.Subject;
import dao.ClassCDao;
import dao.ScoreDao;
import dao.SubjectDao;

@WebServlet(urlPatterns = { "/score/ScoRegExe" })
public class ScoRegExe extends HttpServlet {
	List<ClassC> list;
	List<Subject> list2;
	List<Student> list3 = null;
	ClassCDao dao = new ClassCDao();
	SubjectDao dao2 = new SubjectDao();

	// 成績入力 クラス 月 科目 １０・２３
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		// 現在運用しているクラス
		// SELECT distinct grade,class_no FROM public.class where end_date is
		// null

		ClassC cls = new ClassC();
		// 現在の科目
		Subject sub = new Subject();
		try {
			list = dao.search();
			list2 = dao2.search();
			req.setAttribute("list", list);
			req.setAttribute("list2", list2);
			req.setAttribute("list3", list3 != null ? list3 : new ArrayList<Student>());
			// JSPページにフォワードして、結果を表示
			req.getRequestDispatcher("scoReg.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "データの取得中にエラーが発生しました。");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String month = req.getParameter("month");
		Subject sub = new Subject();
		String subject_id = req.getParameter("subject_id");
		String count = req.getParameter("count");
		int grade;
		ScoreDao scdao = new ScoreDao();
		Score scoreBean  = new Score();
		System.out.println("count=" + count + "月=" + month + ' ' + ' ' + subject_id);
		try {
			int i;
			for (i = 0; i < Integer.parseInt(count); i++) {
				scoreBean.setStudentId(req.getParameter("student_id-"+i));
				scoreBean.setSubjectId(req.getParameter("subject_id"));
				scoreBean.setClassId(Integer.parseInt(req.getParameter("class_id")));
				scoreBean.setScore(Integer.parseInt(req.getParameter("score-"+i)));
				scoreBean.setYear(2024);
				scoreBean.setMonth(Integer.parseInt(month));
				scdao.insert(scoreBean);
			}
			 System.out.println(i + "件登録完了しました。");
			// JSPページにフォワードして、結果を表示
			req.getRequestDispatcher("scoRegExe.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "データの取得中にエラーが発生しました。");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}

	}

}
