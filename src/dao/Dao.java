package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// データベースのURL
        final String URL = "jdbc:postgresql://localhost:5432/keirihonka";
        // データベースにアクセスするユーザー
        final String USER = "postgres";
        // パスワード
        final String PASSWORD = "postgres";

        Class.forName("org.postgresql.Driver");
        // データベースへ接続する
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
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
