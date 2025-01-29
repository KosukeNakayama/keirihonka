package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao{

	public Subject search(String subject_id) throws Exception{
		Subject sub=new Subject();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"SELECT subject_id,subject_name FROM subject where subject_id = ? ");
		st.setString(1, subject_id);
		ResultSet rs=st.executeQuery();
		rs.next();
		sub.setSubjectId(rs.getString("subject_id"));
		sub.setSubjectName(rs.getString("subject_name"));
		st.close();
		con.close();
		return sub;
	}


	//検索
	public List<Subject> search() throws Exception {
		List<Subject> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"SELECT subject_id,subject_name FROM subject");
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Subject p=new Subject();
			p.setSubjectId(rs.getString("subject_id"));
			p.setSubjectName(rs.getString("subject_name"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}

	//科目登録
		public int insert(Subject subject) throws Exception {
			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement(
				"insert into subject values(?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, subject.getSubjectId());
			st.setString(2, subject.getSubjectName());
			st.setInt(3, subject.getCourseId());
			st.setInt(4, subject.getGrade());
			st.setInt(5, subject.getCredit());
			st.setInt(6, subject.getClassNum());
			st.setBoolean(7, subject.getisFirstHalf());
			int line=st.executeUpdate();
			st.close();
			con.close();
			return line;
		}
}
