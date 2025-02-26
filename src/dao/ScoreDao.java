package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Score;
import bean.Student;


public class ScoreDao extends Dao{

	//スコアの登録　もし得点が既にあれば、それを出力、なければ、レコードを作成して出力
	public List<Score> search(List<Student> stlist,String subject_id) throws Exception{
		Score record ;
		List<Score> list =new ArrayList<>();
		for(Student p :stlist){
			record=searchStudent(p.getStudentId(), subject_id);
			record.setStudentName(p.getStudentName());
			list.add(record);
		}
		return list;
	}
	//学生で検索
	public List<Score> searchStudent(String student_id) throws Exception{
		Connection con=getConnection();
		List<Score> list =new ArrayList<>();
		PreparedStatement st=con.prepareStatement(
				"select score.student_id,student_name,subject_name,score,year,month from score join subject on score.subject_id = subject.subject_id"
				+ " join student on score.student_id = student.student_id "
				+ " where score.student_id= ? ");
		st.setString(1, student_id);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Score p=new Score();
			p.setStudentId(rs.getString("student_id"));
			p.setStudentName(rs.getString("student_name"));
			p.setSubjectName(rs.getString("subject_name"));
			p.setScore(rs.getInt("score"));
			p.setYear(rs.getInt("year"));
			p.setMonth(rs.getInt("month"));
			list.add(p);
		}
		st.close();
		con.close();
		return list;
	}
	//学生と科目で検索
	public Score searchStudent(String student_id,String subject_id) throws Exception{
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"select score.student_id,student_name,subject_name,score,year,month from score join subject on score.subject_id = subject.subject_id"
				+ " join student on score.student_id = student.student_id "
				+ " where score.student_id = ? and score.subject_id = ? ");
		st.setString(1, student_id);
		st.setString(2 ,subject_id);
		ResultSet rs=st.executeQuery();
		Score p=new Score();
		if(rs.next()) {
			p.setStudentId(rs.getString("student_id"));
			p.setStudentName(rs.getString("student_name"));
			p.setSubjectName(rs.getString("subject_name"));
			p.setScore(rs.getInt("score"));
			p.setYear(rs.getInt("year"));
			p.setMonth(rs.getInt("month"));
		}else{
			p.setStudentId(student_id);
		}
		st.close();
		con.close();
		return p;
	}

	//学生登録
	public int insert(Score score) throws Exception {
		Connection con=getConnection();
		//削除→登録も一つの方法。
		PreparedStatement del=con.prepareStatement(
				"delete from score where student_id=? and subject_id=? ");
		del.setString(1,score.getStudentId());
		del.setString(2, score.getSubjectId());
		del.executeUpdate();

		PreparedStatement st=con.prepareStatement(
			"insert into score values(?, ?, ?, ?, ?)");
		st.setString(1, score.getStudentId());
		st.setString(2, score.getSubjectId());
		st.setInt(3, score.getScore());
		st.setInt(4, score.getYear());
		st.setInt(5, score.getMonth());
		int line=st.executeUpdate();
		st.close();
		con.close();
		return line;
	}

}
