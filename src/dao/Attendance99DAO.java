//小川動作確認用DAO

package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bean.Attendance;
import bean.ClassHistory;
import bean.Holiday;
import bean.StudentExp;
import servlet.attend.SchoolYear;

public class Attendance99DAO extends Dao {

	//出欠一覧取得
	public List<StudentExp> searchByCurrentMonth(int grade, int classNo, Date date) throws Exception {

		List<StudentExp> list=new ArrayList<>();
		Connection con=getConnection();

		//当日日付
//		Date today = SchoolYear.returnToday(date);

		//今年度を取得
		int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//対象クラス学生一覧取得（当月のみ）
		PreparedStatement st=con.prepareStatement(
			"SELECT student.student_id, student_name, class_id, seat_no, date, status, memo "
			+ "FROM student "
				+ "JOIN classhistory ON student.student_id = classhistory.student_id "
				+ "LEFT JOIN attendance ON student.student_id = attendance.student_id "
				+ "						AND date_part('month',now())=date_part('month', date)  "
				+ "WHERE class_id = ( "
					+"SELECT class_id FROM class "
						+ "WHERE grade = ? AND class_no = ? AND school_year = ? "
						+ "ORDER BY start_date DESC LIMIT 1"
				+ ")"
			+ "ORDER BY student.student_id"
		);

//		st.setDate(1, today);
		st.setInt(1, grade);
		st.setInt(2, classNo);
		st.setInt(3, inputSchoolYear);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			StudentExp stu = new StudentExp();
			stu.setStudentId(rs.getString("student_id"));
			stu.setStudentName(rs.getString("student_name"));

			ClassHistory ch = new ClassHistory();
			ch.setStudentId(rs.getString("student_id"));
			ch.setClassId(rs.getInt("class_id"));
			ch.setSeatNo(rs.getInt("seat_no"));
			stu.setClassHistoryList(ch);

			Attendance at = new Attendance();
			at.setStudentId(rs.getString("student_id"));
			at.setDate(rs.getDate("date"));
			if (Objects.nonNull(rs.getString("status"))){
				char status[] = rs.getString("status").toCharArray();
				at.setStatus(status[0]);
			}
			at.setMemo(rs.getString("memo"));
			stu.setAttendanceList(at);

			list.add(stu);
		}

		st.close();
		con.close();

		return list;
	}


	//座席情報取得
	public List<StudentExp> searchByClass(int grade, int classNo, Date date) throws Exception {

		List<StudentExp> list=new ArrayList<>();
		Connection con=getConnection();

		//当日日付
		Date today = SchoolYear.returnToday(date);

		//今年度を取得
		int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//対象クラス学生一覧取得
		PreparedStatement st=con.prepareStatement(
			"SELECT student.student_id, student_name, class_id, seat_no, date, status, memo , withdrawal_day "
			+ "FROM student "
				+ "JOIN classhistory ON student.student_id = classhistory.student_id "
				+ "LEFT JOIN attendance ON student.student_id = attendance.student_id AND attendance.date = ? "
				+ "WHERE class_id = ( "
					+"SELECT class_id FROM class "
						+ "WHERE grade = ? AND class_no = ? AND school_year = ? "
						+ "ORDER BY start_date DESC LIMIT 1"
				+ ")"
				+ "AND withdrawal_day IS NULL "
			+ "ORDER BY student.student_id"
		);

		st.setDate(1, today);
		st.setInt(2, grade);
		st.setInt(3, classNo);
		st.setInt(4, inputSchoolYear);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			StudentExp stu = new StudentExp();
			stu.setStudentId(rs.getString("student_id"));
			stu.setStudentName(rs.getString("student_name"));
			stu.setWithdrawalDay(rs.getDate("withdrawal_day"));

			ClassHistory ch = new ClassHistory();
			ch.setStudentId(rs.getString("student_id"));
			ch.setClassId(rs.getInt("class_id"));
			ch.setSeatNo(rs.getInt("seat_no"));
			stu.setClassHistoryList(ch);

			Attendance at = new Attendance();
			at.setStudentId(rs.getString("student_id"));
			at.setDate(rs.getDate("date"));
			if (Objects.nonNull(rs.getString("status"))){
				char status[] = rs.getString("status").toCharArray();
				at.setStatus(status[0]);
			}
			at.setMemo(rs.getString("memo"));
			stu.setAttendanceList(at);

			list.add(stu);
		}

		st.close();
		con.close();

		return list;
	}


	//更新データ存在確認
	public List<Attendance> selectByStuDate(String studentId, Date date) throws Exception {

		List<Attendance> list=new ArrayList<>();
		Connection con=getConnection();
//		System.out.println("select");
		//今年度を取得
		//int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//今年度クラス一覧取得
		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM attendance"
				+ " WHERE student_id = ? AND date = ?"
		);
		st.setString(1, studentId);;
		st.setDate(2, date);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Attendance att=new Attendance();
			char[] c = rs.getString("status").toCharArray();
			att.setStatus(c[0]);
			att.setMemo(rs.getString("memo"));
			list.add(att);
		}

		st.close();
		con.close();

		return list;
	}


	//座席情報更新
	public void updateAttendance(String studentId, Date date,  String status, String memo) throws Exception {

		List<Attendance> list=new ArrayList<>();
		Connection con=getConnection();
//		System.out.println("update");
		try {
			//指定学生＋日付データをUPDATE
			PreparedStatement st = con.prepareStatement(
				"UPDATE attendance SET "
					+ "status = ?,"
					+ "memo = ? "
					+ "WHERE student_Id = ? AND date = ?; "
			);

			st.setString(1, status);
			st.setString(2, memo);
			st.setString(3, studentId);
			st.setDate(4, date);
			int rs=st.executeUpdate();

			st.close();

		} catch(SQLException e)  {
	              System.out.println("SQL Exception:"+e) ;
		}

		con.close();

		return;
	}

	//座席情報追加
	public void insertAttendance(String studentId, Date date, String status, String memo) throws Exception {

		Connection con=getConnection();
//		System.out.println("insert");
		try {

			//Attendanceデータ追加
			PreparedStatement st = con.prepareStatement(
				"INSERT INTO attendance VALUES( ?, ?, ?, ?);"
			);

			st.setString(1, studentId);
			st.setDate(2, date);
			st.setString(3, status);
			st.setString(4, memo);

			st.executeUpdate();

			st.close();

		} catch(SQLException e)  {
	              System.out.println("SQL Exception:"+e) ;
		}

		con.close();

		return;
	}

	//出席（statuds=0）の時はデータ削除
	public void deleteAttendance(String studentId, Date date) throws Exception {

		Connection con=getConnection();
//		System.out.println("delete");
		//今年度を取得
		//int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		try {
			//対象出欠データ削除
			PreparedStatement st = con.prepareStatement(
				"DELETE FROM attendance"
					+ " WHERE student_id = ? AND date = ?"
			);

			st.setString(1, studentId);;
			st.setDate(2, date);
			st.executeUpdate();

			st.close();

		} catch(SQLException e)  {
            System.out.println("SQL Exception:"+e) ;
		}

		con.close();

		return;
	}

	//当日休日情報取得
	public List<Holiday> searchHolidayByToday(int grade, int classNo, Date date) throws Exception {

		List<Holiday> list=new ArrayList<>();
		Connection con=getConnection();

		//当日日付
		Date today = SchoolYear.returnToday(date);

		//今年度を取得
		int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//対象クラス休日取得
		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM holiday "
				+ "WHERE holiday = ? "
				+ "AND class_id = ( "
					+"SELECT class_id FROM class "
						+ "WHERE grade = ? AND class_no = ? AND school_year = ? "
						+ "ORDER BY start_date DESC LIMIT 1"
				+ ")"
		);

		st.setDate(1, today);
		st.setInt(2, grade);
		st.setInt(3, classNo);
		st.setInt(4, inputSchoolYear);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Holiday hol = new Holiday();
			hol.setHoliday(rs.getDate("holiday"));
			hol.setClassId(rs.getInt("class_id"));
			hol.setManaged(rs.getBoolean("is_manage"));
			list.add(hol);
		}

		st.close();
		con.close();

		return list;
	}

	//当日休日情報取得
	public List<Holiday> searchHolidayByMonth(int grade, int classNo, Date date) throws Exception {

		List<Holiday> list=new ArrayList<>();
		Connection con=getConnection();

		//当日日付
		Date today = SchoolYear.returnToday(date);

		//今年度を取得
		int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//対象クラス休日取得
		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM holiday "
				+ "WHERE class_id = ( "
					+"SELECT class_id FROM class "
						+ "WHERE grade = ? AND class_no = ? AND school_year = ? "
						+ "ORDER BY start_date DESC LIMIT 1"
					+ ")"
				+ " AND  date_part('month',now())=date_part('month', holiday)  "
				+ " ORDER BY holiday "
		);

		st.setInt(1, grade);
		st.setInt(2, classNo);
		st.setInt(3, inputSchoolYear);
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Holiday hol = new Holiday();
			hol.setHoliday(rs.getDate("holiday"));
			hol.setClassId(rs.getInt("class_id"));
			hol.setManaged(rs.getBoolean("is_manage"));
			list.add(hol);
		}

		st.close();
		con.close();

		return list;
	}

}