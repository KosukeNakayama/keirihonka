package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class ClassHistoryDao extends Dao{
	//成績入力の対象となる学生を検索
	public List<Student> search(int class_id)throws Exception{
		List<Student> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"select classhistory.student_id,student.student_name from classhistory inner join student on "
			+ "classhistory.student_id =student.student_id "
			+ "where class_id = ? ");
		st.setInt(1, class_id);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Student p=new Student();
			p.setStudentId(rs.getString("student_id"));
			p.setStudentName(rs.getString("student_name"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}
}
