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

import bean.ClassC;
import bean.ClassHistory;
import bean.StudentExp;
import servlet.attend.SchoolYear;

public class ClassC99DAO extends Dao {

	//クラス一覧取得
	public List<ClassC> selectAll(Date date) throws Exception {

		List<ClassC> list=new ArrayList<>();
		Connection con=getConnection();

		//今年度を取得
		int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//今年度クラス一覧取得
		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM class"
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
			crs.setVertical(rs.getInt("vertical"));
			crs.setHorizontal(rs.getInt("horizontal"));
			list.add(crs);
//			System.out.println("class:" + crs.getClassName() + " v:" + crs.getVertical() + " h:" + crs.getHorizontal());
		}

		st.close();
		con.close();

		return list;
	}

	//座席情報取得
	public List<StudentExp> searchByNo(int grade, int classNo, Date date) throws Exception {

		List<StudentExp> list=new ArrayList<>();
		Connection con=getConnection();

		//今年度を取得
		int inputSchoolYear = SchoolYear.returnSchoolYear(date);

		//対象クラス学生一覧取得
		PreparedStatement st=con.prepareStatement(
			"SELECT student.student_id, student_name, class_id, seat_no FROM student "
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

			ClassHistory ch = new ClassHistory();
			ch.setStudentId(rs.getString("student_id"));
			ch.setClassId(rs.getInt("class_id"));
			ch.setSeatNo(rs.getInt("seat_no"));

			stu.setClassHistoryList(ch);
			list.add(stu);
		}

		st.close();
		con.close();

		return list;
	}

	//座席情報更新
	public List<ClassC> updateClassHistory(int classId, String[] students, String[] seatNos) throws Exception {

		List<ClassC> list=new ArrayList<>();
		Connection con=getConnection();

		// 現在の日時を取得
		long ts = System.currentTimeMillis();
		Date startDate = new Date(ts);
		System.out.println(startDate);

		try {

			//SeatNoをUPDATE
			PreparedStatement st = con.prepareStatement(
				"UPDATE classHistory SET "
					+ "seat_no = ?, "
					+ "start_date = ?, "
					+ "end_date = ? "
					+ "WHERE student_Id = ? AND class_id = ?; "
			);

			for (int i=0; i<students.length; i++) {
				System.out.println("seatNo["+i+"]:"+seatNos[i]);

				//searNo未入力の場合はDBにNullを設定
				if (Objects.isNull(seatNos[i]) | seatNos[i].isEmpty()) {
					st.setNull(1, java.sql.Types.NULL);
				} else {
					st.setInt(1, Integer.parseInt(seatNos[i]));
				}

				st.setDate(2, startDate);
				st.setNull(3, java.sql.Types.NULL);
				st.setString(4, students[i]);
				st.setInt(5, classId);
				System.out.println("students[i]:"+seatNos[i]+" classId:"+classId+" seatNos[i]:"+seatNos[i]);
				int rs=st.executeUpdate();
			}
			st.close();

		} catch(SQLException e)  {
	              System.out.println("SQL Exception:"+e) ;
		}

		con.close();

		return list;
	}
}