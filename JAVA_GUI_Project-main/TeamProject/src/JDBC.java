import java.sql.*;

public class JDBC {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "Noah@0717");
			System.out.println("DB ���� �Ϸ�");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC ����̺� �ε� ����");
		} catch(SQLException e) {
			System.out.println("DB ���� ����");
		}
	}
}
