package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	//ユーザー取得
	public Users getUser(String id,String pw) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM USERS WHERE USER_ID = ? AND PASSWORD = ?");
		st.setString(1, id);
		st.setString(2, pw);
		ResultSet rs=st.executeQuery();
		Users user = new Users();
		while(rs.next()){
			user.setUserId(rs.getString("USER_ID"));
			user.setManaged(rs.getBoolean("IS_MANAGE"));
		}

		st.close();
		con.close();
		return user;
	}

}
