import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class jdbc_DayThirtyFive {

	public static void main(String[] args) {
		getsqlConnection();
		writeempData();

	}
	
	
	private static void writeempData() {
		Connection conn = getsqlConnection();
		try {

			String insertQuery = "INSERT INTO  employee_payrolldar35(empid, empname, empsalary, dateofjoin) VALUES (?,?,?,?)";
			PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
			insertStatement.setInt(1, 110);
			insertStatement.setString(2, "Autal");
			insertStatement.setInt(3, 4900);
			insertStatement.setDate(4, new Date(896520206000L));
			   
			int rowInserted = insertStatement.executeUpdate();
			if(rowInserted > 0){
				System.out.println("Data Inserted");
		}
		}catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	private static Connection getsqlConnection() {
		Connection conn = null;
		String hostUrl = "jdbc:mysql://localhost:3306/emp_payroll";
		String userName = "root";
		String password = "mymaths00";
		try {
			conn = DriverManager.getConnection(hostUrl, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return conn;

	}

}

