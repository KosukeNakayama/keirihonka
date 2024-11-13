package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.Student;

public class Dao {
	/**
	 * データソース:DataSource:クラスフィールド
	 */
	static DataSource ds;

	/**
	 * getConnectionメソッド データベースへのコネクションを返す
	 *
	 * @return データベースへのコネクション:Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		// データソースがnullの場合
		if (ds == null) {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup("java:/comp/env/jdbc/keirihonka");
		}
		// データベースへのコネクションを返却
		return ds.getConnection();
	}

	/**
	 * test用メソッド
	 * DBと接続できてるかのテスト
	 */
	public void test() throws Exception {
			// 学校インスタンスを初期化
			Student stu = new Student();
			// データベースへのコネクションを確率
			Connection connection = getConnection();
			// プリペアードステートメント
			PreparedStatement statement = null;

			try {
				// プリペアードステートメントにSQL文をセット
				statement = connection.prepareStatement("select * from student");
				// プリペアードステートメントを実行
				ResultSet rSet = statement.executeQuery();

				if (rSet.next()) {
					// リザルトセットが存在する場合
					// 学校インスタンスに学校コードと学校名をセット
					stu.setStudentId(rSet.getString("student_id"));
					stu.setStudentName(rSet.getString("student_name"));

				} else {
					// 存在しない場合
					// 学校インスタンスにnullをセット
					stu = null;
				}
			} catch (Exception e) {
				throw e;
			} finally {
				// プリペアードステートメントを閉じる
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}
				// コネクションを閉じる
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}
			}
			System.out.println(stu);
	}
}
