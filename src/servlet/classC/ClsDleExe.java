package servlet.classC;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassCDao;
import util.Checker;

@WebServlet(urlPatterns={"/Class/ClsDleExe"})
public class ClsDleExe extends HttpServlet {

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
				String dayOfDeleteStr;
				Calendar dayOfDelete;
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				HttpSession session = request.getSession();

				//リクエストパラメータ―の取得 2
				classIdStr = request.getParameter("selectClass");
				dayOfDeleteStr = request.getParameter("dayOfDelete");

				if(!Objects.isNull(classIdStr) && !Objects.isNull(dayOfDeleteStr)){
					classId = Integer.parseInt(classIdStr);
					//dateのオブジェクト・・・？
					dayOfDelete = Calendar.getInstance();
					dayOfDelete.setTime(sf.parse(dayOfDeleteStr));
				}else{
					request.setAttribute("message", "クラスと終了日を入力してください");
					request.getRequestDispatcher("/class/ClsDle.jsp").forward(request, response);
					return;
				}

				//DBからデータ取得 3
				//削除しても大丈夫かチェックする
				//削除日のチェックと所属学生のチェック
				if(clsDao.getNum(classId) != 0){ //まずは所属学生のチェック
					//０じゃない場合、まだ次の所属先が決まっていないので、クラスは削除させない。
					request.setAttribute("message", "クラス削除後の所属が決まって居ない学生がいます。<br>先に削除後の所属を決めてからクラスを削除してください。");
					request.getRequestDispatcher("/class/ClsDle.jsp").forward(request, response);
					return;
				}else if(clsDao.getNum(classId,dayOfDelete) != 0) { //削除日のチェック
					//０じゃない場合、削除日がクラス履歴の最終日より前なので、クラスは削除させない。
					request.setAttribute("message", "学生のクラス履歴上、問題が発生しました。<br>クラスの削除日は学生の所属最終日以降の日付を指定してください。");
					request.getRequestDispatcher("/class/ClsDle.jsp").forward(request, response);
					return;
				}

				//この二つのチェックののちに削除をする。

				//ビジネスロジック 4
				//なし
				//DBへデータ保存 5
				clsDao.DeleteClass(classId,dayOfDelete);
				//レスポンス値をセット 6
				session.setAttribute("message", "クラスを削除しました");

				//JSPへフォワード 7
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

}
