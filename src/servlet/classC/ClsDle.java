package servlet.classC;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassC;
import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsDle"})
public class ClsDle extends HttpServlet {

	public void doGet (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

			//ログインチェック
			if(!Checker.isLogined(request)){
				String message = "ログインしてください";
				HttpSession session = request.getSession();
				session.invalidate();
				request.setAttribute("message", message);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
			//管理職チェック
			if(!Checker.isManage(request)){
				String message = "管理職でないとアクセスできません";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				return;
			}

			//ローカル変数の宣言 1
			ClassCDao clsDao = new ClassCDao();
			HttpSession session = request.getSession();
			ArrayList<ClassC> clsList= null;

			//リクエストパラメータ―の取得 2
			//なし

			//DBからデータ取得 3
			//削除していないクラスを取得
			try {
				clsList = clsDao.getClassList();
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "サーバーエラーが起きました。もう一度やり直してください。");
				request.getRequestDispatcher("/keirihonaka/main").forward(request, response);
				return;
			}

			//ビジネスロジック 4
			//なし

			//DBへデータ保存 5
			//なし

			//レスポンス値をセット 6
			//クラスのリストをセット
			session.setAttribute("clsList", clsList);

			//JSPへフォワード 7
			request.getRequestDispatcher("/class/ClsDle.jsp").forward(request, response);

		}

}
