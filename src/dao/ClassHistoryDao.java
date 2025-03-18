package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassC;
import bean.ClassHistoryExp;
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

	public ArrayList<ClassHistoryExp> getHistory(String studentId) throws Exception {
		ArrayList<ClassHistoryExp> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"select "
			+ "CLASSHISTORY.class_id,CLASSHISTORY.Start_date,CLASSHISTORY.End_date,GRADE,CLASS_NO"
			+ " from classhistory "
			+ "LEFT OUTER JOIN CLASS ON CLASSHISTORY.CLASS_ID = CLASS.CLASS_ID"
			+ " where student_Id = ? Order BY CLASSHISTORY.Start_date");
		st.setString(1, studentId);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			ClassHistoryExp clsHst=new ClassHistoryExp();
			clsHst.setClassId(rs.getInt("class_id"));
			clsHst.setStartDate(rs.getDate("Start_date"));
			clsHst.setEndDate(rs.getDate("End_date"));
			ClassC cls =new ClassC();
			cls.setGrade(rs.getInt("GRADE"));
			cls.setClassNo(rs.getInt("CLASS_NO"));
			clsHst.setClassC(cls);
			list.add(clsHst);
		}
		st.close();
		con.close();

		return list;
	}
}
