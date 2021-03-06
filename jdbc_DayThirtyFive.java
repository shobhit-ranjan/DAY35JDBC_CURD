import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_DayThirtyFive {

	public static void main(String[] args) {
		getsqlConnection();
		//writeempData();
		readEmployeePayroll();
		showEmployeesbtweenDate();
		findingAlldetails();

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
			if (rowInserted > 0) {
				System.out.println("Data Inserted");
			}
		} catch (SQLException e) {
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

	private static void readEmployeePayroll() {
		System.out.println("Displaying all data of employee_payroll table");
		Connection conn = getsqlConnection();

		try {
			if (conn != null) {
				String readEmpPayroll = "SELECT * FROM employee_payrolldar35";

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(readEmpPayroll);
				while (resultSet.next()) {
					Integer id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					Integer salary = resultSet.getInt(3);
					String date = resultSet.getString(4);
					String row = String.format("User record: \n Id: %d, \n Name: %s,\n DAte: %d, \n", id, name, salary,
							date);
					System.out.println(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

				}
			}
		}

	}

	private static void showEmployeesbtweenDate() {
		System.out.println("Displaying employees joined between given dates");
		Connection conn = getsqlConnection();

		try {
			if (conn != null) {

				String selQuery = "SELECT * FROM employee_payrolldar35 WHERE dateofjoin > ? AND dateofjoin < ?";
				PreparedStatement Statement = conn.prepareStatement(selQuery);
				Statement.setDate(1, new Date(896520206000L));
				Statement.setDate(2, new Date(987620206000L));
				ResultSet resultData = Statement.executeQuery();

				while (resultData.next()) {

					int id = resultData.getInt("empid");
					String name = resultData.getString("empname");
					int salary = resultData.getInt("empsalary");
					String startDate = resultData.getDate("dateofjoin").toString();

					String rowData = String.format("\nId : %d \nName : %s \nGender : %s \nSalary : %d \nStartDate : %s",
							id, name, salary, startDate);
					System.out.println(rowData);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

				}
			}
		}

	}

	private static void findingAlldetails() {
		System.out.println("Displaying all values ");
		Connection conn = getsqlConnection();

		try {
			if (conn != null) {
				String readEmpPayroll = "SELECT min(empsalary), max(empsalary),sum(empsalary),avg(empsalary),count(empsalary) FROM employee_payrolldar35";

				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(readEmpPayroll);
				while (resultSet.next()) {

					int minSalary = resultSet.getInt(1);
					int maxSalary = resultSet.getInt(2);
					int sumSalary = resultSet.getInt(3);
					int avgSalary = resultSet.getInt(4);
					int countSalary = resultSet.getInt(5);

					String row = String.format(
							"User record: \n Min: %d, \n Max: %d,\n Sum: %d,\n Avg: %d,\n Count: %d,", minSalary,
							maxSalary, sumSalary, avgSalary, countSalary);
					System.out.println(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlException) {
					System.out.println(sqlException.getMessage());

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
