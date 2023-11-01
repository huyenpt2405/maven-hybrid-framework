package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTestConnection {

	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnUtils.getMySQLConnection();
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Get connection... ");

		// Lấy ra đối tượng Connection kết nối vào database.
		Connection conn = MySQLTestConnection.getMyConnection();

//		Statement statement = conn.createStatement();
//
//		String sql = "Select Emp.Emp_Id, Emp.First_Name, Emp.Last_Name, Emp.Dept_Id From Employee Emp;";
//
//		// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
//		ResultSet rs = statement.executeQuery(sql);

		// Duyệt trên kết quả trả về
//		while (rs.next()) {
//			// Di chuyển con trỏ xuống bản ghi kế tiếp.
//			int empId = rs.getInt(1);
//			String empFirstName = rs.getString(2);
//			String empLastName = rs.getString("Last_Name");
//
//			System.out.println("--------------------");
//			System.out.println("Emp Id:" + empId);
//			System.out.println("Emp Firstname:" + empFirstName);
//			System.out.println("Emp Lastname:" + empLastName);
//		}
		System.out.println("---------- Closed connection ----------");
		
		String sqlParam = "Select Emp.Emp_Id, Emp.First_Name, Emp.Last_Name, Emp.Dept_Id From Employee Emp Where Emp.Title like ? And Emp.Dept_Id = ?;";
		
		PreparedStatement prpStatement = conn.prepareStatement(sqlParam);
		prpStatement.setString(1, "%ent");
		prpStatement.setInt(2, 3);
		
		ResultSet result = prpStatement.executeQuery();
		while (result.next()) {
			int empId = result.getInt(1);
			String empFirstName = result.getString(2);
			String empLastName = result.getString("Last_Name");
			int deptID = result.getInt("Dept_Id");

			System.out.println("--------------------");
			System.out.println("Emp Id:" + empId);
			System.out.println("Emp Firstname:" + empFirstName);
			System.out.println("Emp Lastname:" + empLastName);
			System.out.println("Dept id :" + deptID );
		}
		
		// Đóng kết nối
		conn.close();
	}
}
