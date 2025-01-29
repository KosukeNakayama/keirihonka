package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassC;


public class ClassCDao extends Dao{

	public int searchGrade(int class_id) throws Exception{
		int grade;
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT grade FROM class where class_id= ?");
		st.setInt(1, class_id);
		ResultSet rs=st.executeQuery();
		rs.next();
		grade =rs.getInt("grade");
		return grade;
	}

	public List<ClassC> search() throws Exception {
		List<ClassC> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"SELECT class_id,grade,class_no FROM class where end_date is null and school_year=2024");
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			ClassC p=new ClassC();
			p.setClassId(rs.getInt("class_id"));
			p.setGrade(rs.getInt("grade"));
			p.setClassNo(rs.getInt("class_no"));
			list.add(p);
		}
		st.close();
		con.close();

		return list;
	}
}
