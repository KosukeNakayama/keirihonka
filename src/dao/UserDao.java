package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Users;

public class UserDao extends Dao{
	//ユーザー登録
	public int insert(Users users) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"insert into users values(?, ?, ?)");
		st.setString(1, users.getUserId());
		st.setString(2, users.getPassword());
		st.setBoolean(3, users.getManaged());
		int line=st.executeUpdate();
		st.close();
		con.close();
		return line;
	}

}
