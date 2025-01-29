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
import bean.Student;
import bean.Subject;
import dao.ClassCDao;
import dao.ClassHistoryDao;
import dao.SubjectDao;

@WebServlet(urlPatterns = { "/score/ScoReg" })
public class ScoReg extends HttpServlet {
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
		// 選択されたクラスIDと科目から入力エリアを作る。
		String student_id = req.getParameter("student_id-0");
		// パラメータが存在するかどうか確認
		if (student_id == null || student_id.isEmpty()) {
			// パラメータがない場合の処理

			String month = req.getParameter("month");

			int class_id = Integer.parseInt(req.getParameter("class_id"));
			Subject sub = new Subject();
			String subject_id = req.getParameter("subject_id");
			int grade;
			ClassHistoryDao stdao = new ClassHistoryDao();
			System.out.println(month + ' ' + class_id + ' ' + subject_id);
			try {
				grade = dao.searchGrade(class_id);
				sub = dao2.search(subject_id);
				System.out.println(sub.getSubjectName());
				req.setAttribute("month", month);
				System.out.println(grade);
				req.setAttribute("subject", sub.getSubjectName());
				req.setAttribute("subject_id", sub.getSubjectId());
				req.setAttribute("grade", grade);
				req.setAttribute("class_id", class_id);
				list = dao.search();
				list2 = dao2.search();
				req.setAttribute("list", list);
				req.setAttribute("list2", list2);
				list3 = stdao.search(class_id);
				System.out.println(list3.size() + "件");
				req.setAttribute("list3", list3);
				// JSPページにフォワードして、結果を表示
				req.getRequestDispatcher("scoReg.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("errorMessage", "データの取得中にエラーが発生しました。");
				req.getRequestDispatcher("error.jsp").forward(req, resp);
			}
		} else {
			// パラメータがある場合の処理
			System.out.println("学生番号があります");
		}

	}

}
