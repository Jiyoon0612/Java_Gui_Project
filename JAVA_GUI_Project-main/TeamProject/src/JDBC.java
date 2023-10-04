import java.sql.*;

public class JDBC {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "Noah@0717");
			System.out.println("DB 연결 완료");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이브 로드 에러");
		} catch(SQLException e) {
			System.out.println("DB 연결 오류");
		}
	}
}
