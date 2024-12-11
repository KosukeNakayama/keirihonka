package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bean.ClassC;
import bean.StudentExp;

public class ClassC99DAO extends Dao {

	//クラス一覧取得
	public List<ClassC> selectAll(Date date) throws Exception {

		List<ClassC> list=new ArrayList<>();
		Connection con=getConnection();

		//今年度を取得
		int inputSchoolYear = returnSchoolYear(date);

		//今年度クラス一覧取得
		PreparedStatement st = con.prepareStatement(
			"SELECT grade, class_no FROM class"
				+ " WHERE school_year = ?"
				+ " ORDER BY grade, class_no"
		);
		st.setInt(1, inputSchoolYear);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			ClassC crs=new ClassC();
			crs.setGrade(rs.getInt("grade"));
			crs.setClassNo(rs.getInt("class_no"));
			crs.getClassName();
			list.add(crs);
		}

		st.close();
		con.close();

		return list;
	}

	public List<StudentExp> searchByNo(int grade, int classNo, Date date) throws Exception {

		List<StudentExp> list=new ArrayList<>();
		Connection con=getConnection();

		//今年度を取得
		int inputSchoolYear = returnSchoolYear(date);

		//対象クラス学生一覧取得
		PreparedStatement st=con.prepareStatement(
			"SELECT student.student_id, student_name FROM student "
				+ "JOIN classhistory ON student.student_id = classhistory.student_id "
				+ "WHERE class_id = ( "
					+"SELECT class_id FROM class "
						+ "WHERE grade = ? AND class_no = ? AND school_year = ? "
						+ "ORDER BY start_date DESC LIMIT 1"
			+")"
			+ "ORDER BY student.student_id"
		);
		st.setInt(1, grade);
		st.setInt(2, classNo);
		st.setInt(3, inputSchoolYear);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			StudentExp stu = new StudentExp();
			stu.setStudentId(rs.getString("student_id"));
			stu.setStudentName(rs.getString("student_name"));

//			ClassHistory ch = new ClassHistory();
//			ch.setStudentId(rs.getString("student_id"));
//			ch.setSeatNo(rs.getInt("seat_no"));
//			System.out.println(ch.getSeatNo());
//
//			stu.setClassHistoryList(ch);
			list.add(stu);
		}

		st.close();
		con.close();

		return list;
	}

	//年度の取得
	int returnSchoolYear(Date date) {

		//日付をカレンダーオブジェクトに設定
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //年度を取得するためカレンダーオブジェクトで指定月-3(MONTHは0オリジン）
        calendar.add(Calendar.MONTH, -3);

        //カレンダーオブジェクトから年を取得
        int schoolYear = calendar.get(Calendar.YEAR);
//        System.out.println(schoolYear);

	    return schoolYear;
	}
}