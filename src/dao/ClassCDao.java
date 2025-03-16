package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.ClassC;
import bean.StudentExp;


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

	public ArrayList<StudentExp> getStudentCandidateList(int schoolYear, int grade, Calendar startDate) throws Exception {
		//入学年、学年、開始日付からクラスの学生候補を取得する
		ArrayList<StudentExp> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM STUDENT LEFT OUTER JOIN CLASSHISTORY ON STUDENT.STUDENT_ID = CLASSHISTORY.STUDENT_ID "
			+ "WHERE STUDENT.ENROLLMENT_YEAR = ? - ? +1"
			+ " AND STUDENT.GRADUATION_DAY IS NULL"
			+ " AND STUDENT.WITHDRAWAL_DAY IS NULL"
			+ " AND CLASSHISTORY.END_DATE IS NULL");
		st.setInt(1, schoolYear);
		st.setInt(2, grade);
		//st.setDate(3, new Date(startDate.getTime().getTime()));

		//System.out.println(st.toString());

		ResultSet rs=st.executeQuery();
		while(rs.next()){
			StudentExp stu = new StudentExp();
			stu.setStudentId(rs.getString("STUDENT_ID"));
			stu.setStudentName(rs.getString("STUDENT_NAME"));
			ClassC cls = new ClassC();
			cls.setClassId(rs.getInt("CLASS_ID"));
			stu.setClassC(cls);
			list.add(stu);
		}

		return list;
	}

	public void regCls(String[] stuList,int schoolYear,int grade,int no,Calendar startDate) throws Exception {
		PreparedStatement st = null;
		String SQL=null;
		Connection con=null;
		try{
			con=getConnection();
			con.setAutoCommit(false);

			//①クラスにINSERT
			SQL = "INSERT INTO CLASS (SCHOOL_YEAR,GRADE,CLASS_NO,START_DATE) VALUES (?,?,?,?)";
			st = con.prepareStatement(SQL);
			st.setInt(1, schoolYear);
			st.setInt(2, grade);
			st.setInt(3, no);
			st.setDate(4, new Date(startDate.getTime().getTime()));
			st.execute();

			System.out.println("Insert complete!");

			//クラスIDを取得
			st = con.prepareStatement("SELECT MAX(CLASS_ID) as CLASS_ID FROM CLASS WHERE SCHOOL_YEAR = ? "
					+ "AND GRADE = ? "
					+ "AND CLASS_NO = ?");
			st.setInt(1, schoolYear);
			st.setInt(2, grade);
			st.setInt(3, no);
			ResultSet rs = st.executeQuery();
			rs.next();
			int clsId = rs.getInt("CLASS_ID");

			//②クラス履歴にINSERT
			SQL = "INSERT INTO CLASSHISTORY VALUES ";
			for(int i=0;i<stuList.length;i++){
				if(i==0){
					SQL += "(?,?,1,?,NULL)";
				}else{
					SQL += ",(?,?,"+(i+1)+ ",?,NULL)";
				}
			}
			//System.out.println(SQL);
			st = con.prepareStatement(SQL);
			for(int i=0;i<stuList.length;i++){
				st.setString(1+3*i, stuList[i]);
				st.setInt(2+3*i, clsId);
				st.setDate(3+3*i, new Date(startDate.getTime().getTime()));
			}
			st.execute();

			con.commit();

		}catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw new Exception();
		}finally{
			con.setAutoCommit(true);
			con.close();
		}
	}
}
