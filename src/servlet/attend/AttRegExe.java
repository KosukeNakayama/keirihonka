package servlet.attend;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.ClassC;
import dao.Attendance99DAO;
import dao.ClassC99DAO;

@WebServlet(urlPatterns={"/servlet/attend/AttRegExe"})
public class AttRegExe extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		// 現在の日時を取得
		long ts = System.currentTimeMillis();
		Date date = new Date(ts);
		//日時をyyyy-mm-ddで取得
		Date today = SchoolYear.returnToday(date);

		//POSTされた値を取得
		request.setCharacterEncoding("UTF-8");
		String attEntry = request.getParameter("attEntry");

		//修正なしで登録されたときはDB登録しない
		if (attEntry.length() != 0) {
			//先頭に区切り文字が入るため、先頭1文字を削除
			attEntry = attEntry.substring(1);

			//学生一人分毎にデータを分割（区切り文字 ';'）しstudents配列にセット
			List<String> students = Arrays.asList(attEntry.split(";"));
			for (String student : students) {

				//引数設定
				String studentId = "";
				String status = "";
				String memo = "";
//				System.out.println("\nstudent:" + student);

				//一人分のデータを[studentId, status, memo]に分割（区切り文字 ','）しelements配列にセット
				List<String> elements = Arrays.asList(student.split(","));
//    			System.out.println("size:" + elements.size());

				//studentIdに値が入っていない場合は処理をしない
				if ((elements.get(0).equals("null")) || (elements.get(0) == null)) {
					continue;
				} else {
//        			System.out.println("elements0:" + elements.get(0));
					studentId = elements.get(0);
					status = elements.get(1);

					//elementsの項目数が2（memoが未入力）の場合の設定
//            		System.out.println("elements2:" + elements.get(2).trim() + " size:" + elements.size());
					if ((elements.size() < 3 ) || (elements.get(2).trim().equals("null"))) {
						memo = "";
					} else {
						memo = elements.get(2);
					}

					try {

						//Attencence情報処理
						Attendance99DAO attendanceDao = new Attendance99DAO();
						List<Attendance> list = attendanceDao.selectByStuDate(studentId, today);

						//指定日出欠データが存在しない
						if (list.size() == 0) {
							attendanceDao.insertAttendance(studentId, today, status, memo);
						} else {
							//入力ミスで登録済みを【出席】に再設定時は出欠データを削除
							if (status.equals("0")) {
								attendanceDao.deleteAttendance(studentId, today);
							} else {
								//指定日出欠データを再設定
								attendanceDao.updateAttendance(studentId, today, status, memo);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

        try {
        	//今年度取得用当日日付
//          long miliseconds = System.currentTimeMillis();
//          Date date = new Date(miliseconds);

            // ClassC99DAO:クラス一覧取得テスト用DAO
           	ClassC99DAO dao = new ClassC99DAO();
           	List<ClassC> classList = dao.selectAll(today);

           	//入力SelectBox用クラスリストのrequest作成
           	request.setAttribute("classList", classList);

           	//登録完了画面に遷移
           	request.getRequestDispatcher("/attend/attRegExe.jsp")
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