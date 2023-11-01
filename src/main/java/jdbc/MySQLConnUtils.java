package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnUtils {

	public static Connection getMySQLConnection() {
		String hostName = "localhost";
		String dbName = "automation_test";
		String userName = "root";
		String password = "";
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
		Connection conn = null;
		try {
			// Cấu trúc URL Connection dành cho MySQL
			// Ví dụ: jdbc:mysql://localhost:3306/automation_test
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionURL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}