package servlet.attend;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/servlet/attend/AttRegExe"})
public class AttRegExe extends HttpServlet {
	public void doPost (
			HttpServletRequest request, HttpServletResponse response
		) throws ServletException, IOException {

		//POSTされた値を取得
		request.setCharacterEncoding("UTF-8");
		String attEntry = request.getParameter("attEntry");
		//先頭に区切り文字が入るため、先頭1文字を削除
		attEntry = attEntry.substring(1);
		System.out.println("attEntry:" + attEntry);

		//学生一人分毎にデータを分割（区切り文字 ';'）しstudents配列にセット
        List<String> students = Arrays.asList(attEntry.split(";"));
        for (String student : students) {
            System.out.println("\nstudent:" + student);
            //一人分のデータを[studentId, status, memo]に分割（区切り文字 ','）しelements配列にセット
            List<String> elements = Arrays.asList(student.split(","));
            for (String element : elements) {
            	//studentIdに値が入っていない場合は処理をしない
            	if ((elements.get(0).equals("null")) || (elements.get(0) == null)) {
            		continue;
            	} else {
                System.out.println("element:" + element);
//                System.out.println("比較:" + elements.get(0).equals("null"));
            	}
            }
        }

//		int seatRow = Integer.parseInt(request.getParameter("seatRow"));
//		int seatCol = Integer.parseInt(request.getParameter("seatCol"));
//		String[] students = request.getParameterValues("studentId");
//		int classId = Integer.parseInt(request.getParameter("classId"));
//		String[] seatNosString = request.getParameterValues("seatNo");
//
//		System.out.println("class_id:"+classId+" vertical:"+seatRow+" horizontal:"+seatCol);

//		try {
//			//Class座席情報UPDATE
//			ClassC99DAO classDao = new ClassC99DAO();
//			classDao.updateClass(classId, seatRow, seatCol);
//
//			//ClassHistory追加
////			ClassC99DAO classHistoryDao = new ClassC99DAO();
//			classDao.updateClassHistory(classId, students, seatNosString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			//今年度取得用当日日付
//			long miliseconds = System.currentTimeMillis();
//			Date date = new Date(miliseconds);
//
//			// ClassC99DAO:クラス一覧取得テスト用DAO
//			ClassC99DAO dao = new ClassC99DAO();
//			List<ClassC> list = dao.selectAll(date);
//
//			//SelectBox用クラスリストのrequest作成
//			request.setAttribute("classList", list);
//
//			//登録完了画面に遷移
//			request.getRequestDispatcher("/attend/seatSetExe.jsp")
//				.forward(request, response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		doPost(request, response);
	}
}