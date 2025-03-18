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

	public ArrayList<StudentExp> getStudentCandidateList(int classId) throws Exception {
		//クラスIDからクラスの学生候補を取得する
		ArrayList<StudentExp> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM CLASS WHERE CLASS_ID = ?");
		st.setInt(1, classId);
		ResultSet rs=st.executeQuery();
		rs.next();
		int schoolYear = rs.getInt("SCHOOL_YEAR");
		int grade = rs.getInt("GRADE");

		st=con.prepareStatement(
			"SELECT * FROM STUDENT "
			+ "LEFT OUTER JOIN CLASSHISTORY ON STUDENT.STUDENT_ID = CLASSHISTORY.STUDENT_ID "
			+ "LEFT OUTER JOIN CLASS ON CLASSHISTORY.CLASS_ID = CLASS.CLASS_ID "
			+ "WHERE STUDENT.ENROLLMENT_YEAR = ? - ? +1"
			+ " AND STUDENT.GRADUATION_DAY IS NULL"
			+ " AND STUDENT.WITHDRAWAL_DAY IS NULL"
			+ " AND CLASSHISTORY.CLASS_ID <> ?"
			+ " AND CLASSHISTORY.END_DATE IS NULL"
			+ " ORDER BY STUDENT.STUDENT_ID");
		st.setInt(1, schoolYear);
		st.setInt(2, grade);
		st.setInt(3, classId);

		rs=st.executeQuery();
		while(rs.next()){
			StudentExp stu = new StudentExp();
			stu.setStudentId(rs.getString("STUDENT_ID"));
			stu.setStudentName(rs.getString("STUDENT_NAME"));
			ClassC cls = new ClassC();
			cls.setClassId(rs.getInt("CLASS_ID"));
			cls.setGrade(rs.getInt("GRADE"));
			cls.setClassNo(rs.getInt("CLASS_NO"));
			stu.setClassC(cls);
			list.add(stu);
		}

		return list;
	}

	public void regCls(int schoolYear,int grade,int no,Calendar startDate) throws Exception {
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

//			System.out.println("Insert complete!");
//
//			//クラスIDを取得
//			st = con.prepareStatement("SELECT MAX(CLASS_ID) as CLASS_ID FROM CLASS WHERE SCHOOL_YEAR = ? "
//					+ "AND GRADE = ? "
//					+ "AND CLASS_NO = ?");
//			st.setInt(1, schoolYear);
//			st.setInt(2, grade);
//			st.setInt(3, no);
//			ResultSet rs = st.executeQuery();
//			rs.next();
//			int clsId = rs.getInt("CLASS_ID");
//
//			//②クラス履歴にINSERT
//			SQL = "INSERT INTO CLASSHISTORY VALUES ";
//			for(int i=0;i<stuList.length;i++){
//				if(i==0){
//					SQL += "(?,?,1,?,NULL)";
//				}else{
//					SQL += ",(?,?,"+(i+1)+ ",?,NULL)";
//				}
//			}
//			//System.out.println(SQL);
//			st = con.prepareStatement(SQL);
//			for(int i=0;i<stuList.length;i++){
//				st.setString(1+3*i, stuList[i]);
//				st.setInt(2+3*i, clsId);
//				st.setDate(3+3*i, new Date(startDate.getTime().getTime()));
//			}
//			st.execute();

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

	public ArrayList<ClassC> getClassList() throws Exception  {
		ArrayList<ClassC> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
			"SELECT class_id,grade,class_no,school_year,start_date FROM class where end_date is null order by start_date");
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			ClassC cls=new ClassC();
			cls.setClassId(rs.getInt("class_id"));
			cls.setSchoolYear(rs.getInt("school_year"));
			cls.setGrade(rs.getInt("grade"));
			cls.setClassNo(rs.getInt("class_no"));
			cls.setStartDate(rs.getDate("start_date"));
			list.add(cls);
		}
		st.close();
		con.close();

		return list;
	}

	public void regClsStu(int classId, String[] stuIds, Calendar dayOfChange) throws Exception {
		PreparedStatement st = null;
		String SQL=null;
		Connection con=null;
		try{
			con=getConnection();
			con.setAutoCommit(false);
			//前のクラスの終了日を更新する
			SQL = "UPDATE CLASSHISTORY SET END_DATE = ? WHERE END_DATE IS NULL "
					+ "AND STUDENT_ID IN (";
			for(int i=0;i<stuIds.length;i++){
				if(i==0){
					SQL += "?";
				}else{
					SQL += ",?";
				}
			}
			SQL += ")";
			st = con.prepareStatement(SQL);
			java.util.Date date = dayOfChange.getTime();
			date.setDate(-1);
			st.setDate(1,new Date(date.getTime()));
			for(int i=0;i<stuIds.length;i++){
				st.setString(i+2,stuIds[i]);
			}
			st.execute();

			//クラス履歴にINSERT
			SQL = "INSERT INTO CLASSHISTORY VALUES ";
			for(int i=0;i<stuIds.length;i++){
				if(i==0){
					SQL += "(?,?,1,?,NULL)";
				}else{
					SQL += ",(?,?,"+(i+1)+ ",?,NULL)";
				}
			}
			//System.out.println(SQL);
			st = con.prepareStatement(SQL);
			for(int i=0;i<stuIds.length;i++){
				st.setString(1+3*i, stuIds[i]);
				st.setInt(2+3*i, classId);
				st.setDate(3+3*i, new Date(dayOfChange.getTime().getTime()));
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

	public ClassC searchById(int class_id) throws Exception{
		ClassC cls = new ClassC();
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT * FROM class where class_id= ?");
		st.setInt(1, class_id);
		ResultSet rs=st.executeQuery();
		rs.next();
		cls.setClassId(rs.getInt("CLASS_ID"));
		cls.setSchoolYear(rs.getInt("school_year"));
		cls.setGrade(rs.getInt("grade"));
		cls.setClassNo(rs.getInt("class_no"));
		cls.setStartDate(rs.getDate("start_date"));
		return cls;
	}

	public void DeleteClass(int classId,Calendar endDate) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"UPDATE CLASS SET END_DATE = ? WHERE CLASS_ID = ?");
		st.setDate(1, new Date(endDate.getTime().getTime()));
		st.setInt(2, classId);
		st.execute();
		return;
	}

	public int getNum(int classId) throws Exception {
		// クラスに所属している学生がクラスの削除日以降に所属するクラスの登録がされているかチェック
		// 戻り値はクラス履歴上、学生の所属終了日に値が入っていない数
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT COUNT(STUDENT_ID) as count FROM CLASSHISTORY WHERE CLASS_ID = ? AND END_DATE IS NULL");
		st.setInt(1, classId);
		ResultSet rs=st.executeQuery();
		rs.next();
		return rs.getInt("count");
	}

	public int getNum(int classId,Calendar date) throws Exception {
		// クラスに所属している学生がクラスの削除日以降に所属するクラスの登録がされているかチェック
		// 戻り値はクラス履歴上、学生の所属終了日に値が入っていない数
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT COUNT(STUDENT_ID) as count FROM CLASSHISTORY WHERE CLASS_ID = ? AND END_DATE < ?");
		st.setInt(1, classId);
		st.setDate(2, new Date(date.getTime().getTime()));
		ResultSet rs=st.executeQuery();
		rs.next();
		return rs.getInt("count");
	}

	public ClassC getClassById(int classId) throws Exception {
		// クラス１個分の情報取得
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT * FROM CLASS WHERE CLASS_ID = ?");
		st.setInt(1, classId);
		ResultSet rs=st.executeQuery();
		rs.next();
		ClassC cls = new ClassC();
		cls.setClassId(rs.getInt("CLASS_ID"));
		cls.setClassNo(rs.getInt("CLASS_NO"));
		cls.setGrade(rs.getInt("grade"));
		cls.setSchoolYear(rs.getInt("SCHOOL_YEAR"));
		cls.setStartDate(rs.getDate("START_DATE"));
		return cls;

	}

	public void updCls(int classId, int schoolYear, int grade, int classNo, Calendar startDate) throws Exception {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"UPDATE CLASS SET SCHOOL_YEAR = ?,GRADE = ?,CLASS_NO = ?,START_DATE = ? WHERE CLASS_ID = ?");
		st.setInt(1, schoolYear);
		st.setInt(2, grade);
		st.setInt(3, classNo);
		st.setDate(4, new Date(startDate.getTime().getTime()));
		st.setInt(5, classId);
		st.execute();

	}
}
