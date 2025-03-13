package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao{

	//検索　年度＆コース
	public List<Student> search(int enrollment,int course) throws Exception {
		List<Student> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"select * from student where enrollment_year = ? and course_id = ?");
		st.setInt(1, enrollment);
		st.setInt(2, course);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Student p=new Student();
			p.setStudentId(rs.getString("student_id"));
			p.setStudentName(rs.getString("student_name"));
			p.setEnrollmentYear(rs.getInt("enrollment_year"));
			p.setCourseId(rs.getInt("course_id"));
			p.setGraduationDay(rs.getDate("graduation_day"));
			p.setWithdrawalDay(rs.getDate("withdrawl_day"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}


	//学生登録
	public int insert(Student student) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"insert into student values(?, ?, ?, ?, null, null)");
		st.setString(1, student.getStudentId());
		st.setString(2, student.getStudentName());
		st.setInt(3, student.getEnrollmentYear());
		st.setInt(4, student.getCourseId());
		int line=st.executeUpdate();
		st.close();
		con.close();
		return line;
	}

	//学番の最大値の取得
	public int getStudentMaxID() throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
				"SELECT MAX(STUDENT_ID) as max FROM STUDENT");
		ResultSet rs=st.executeQuery();
		rs.next();
		int max = Integer.parseInt(rs.getString("max"));
		st.close();
		con.close();
		return max;
	}

	//学生検索　学生番号
	public List<Student> searchById(String student_id) throws Exception {
		List<Student> list=new ArrayList<>();

		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"select * from student where name like ?");
		st.setString(1, student_id);
	    ResultSet rs = st.executeQuery();

		Student p=new Student();
		p.setStudentId(rs.getString("student_id"));
		p.setStudentName(rs.getString("student_name"));
		p.setEnrollmentYear(rs.getInt("enrollment_year"));
		p.setCourseId(rs.getInt("course_id"));
		p.setGraduationDay(rs.getDate("graduation_day"));
		p.setWithdrawalDay(rs.getDate("withdrawl_day"));
		list.add(p);

		st.close();
		con.close();

		return list;
	}


}
