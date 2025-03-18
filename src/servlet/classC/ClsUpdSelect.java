package servlet.classC;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassC;
import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsSelect"})
public class ClsUpdSelect extends HttpServlet {

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

			try {
				//ローカル変数の宣言 1
				String classIdStr = null;
				int classId = 0;
				ClassCDao clsDao = new ClassCDao();
				HttpSession session = request.getSession();

				//リクエストパラメータ―の取得 2
				classIdStr = request.getParameter("selectClass");

				if(!Objects.isNull(classIdStr)){
					classId = Integer.parseInt(classIdStr);
				}else{
					request.setAttribute("message", "クラスを選択してください");
					request.getRequestDispatcher("/class/ClsStuReg.jsp").forward(request, response);
					return;
				}

				//DBからデータ取得 3
				//クラスの情報を取得
				ClassC cls = clsDao.getClassById(classId);

				//ビジネスロジック 4
				//なし
				//DBへデータ保存 5
				//なし
				//レスポンス値をセット 6
				session.setAttribute("cls", cls);

				//JSPへフォワード 7
				request.getRequestDispatcher("/class/ClsStuReg.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

}
