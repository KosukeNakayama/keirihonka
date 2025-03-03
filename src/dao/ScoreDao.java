package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import bean.Score;


public class ScoreDao extends Dao{

	//スコアの登録　もし得点が既にあれば、それを出力、なければ、レコードを作成して出力
	public List<Score> search(int student_id,String subject_id,int year,int month) throws Exception{
		List<Score> list =new ArrayList<>();

		return list;
	}


	//学生登録
	public int insert(Score score) throws Exception {
		Connection con=getConnection();
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
