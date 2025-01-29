package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.ClassC;

public class ClassDao extends Dao{
	//クラス登録
	public int insert(ClassC classc) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"insert into class values(null, ?, ?, ?, ?, ?, ?, null)");
		st.setInt(1, classc.getSchoolYear());
		st.setInt(2, classc.getGrade());
		st.setInt(3, classc.getClassNo());
		st.setInt(4, classc.getVertical());
		st.setInt(5, classc.getHorizontal());
		st.setDate(6, new java.sql.Date(classc.getStartDate().getTime()));
		int line=st.executeUpdate();
		st.close();
		con.close();
		return line;
	}
}
