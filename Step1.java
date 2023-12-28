package LoadingDriver;

import java.sql.*;

public class Step1 {

	public static void main(String[] args) throws Exception {
		delete();
	}

	public static void readRecords() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		String query = "select * from emp";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			System.out.println("Employee Id :" + rs.getInt(1));
			System.out.println("Employee Name:" + rs.getString(2));
			System.out.println("Employee Salary:" + rs.getInt(3));
		}
		con.close();
	}
	
	public static void insertRecord() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		String query = "insert into emp values(4,'Munish',400)";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		
		System.out.println("Number of rows Affected "+rows);
		con.close();
	}
	public static void insertVar() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		
		int id = 5;
		String name = "Abimanyu";
		int sal = 6000;
		String query = "insert into emp values("+id+",'"+name+"',"+sal+");";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		
		System.out.println("Number of rows Affected "+rows);
		con.close();
	}
	public static void insertpst()throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		
		int emp_id = 5;
		String name = "abi";
		int sal = 500;
		
		String query = "insert into emp values (?,?,?);";
		
		Connection con = DriverManager.getConnection(url, userName, passWord);
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, emp_id);
		pst.setString(2, name);
		pst.setInt(3, sal);
		int rows = pst.executeUpdate();
		System.out.println(rows);
		con.close();
	}
	
	public static void delete()throws SQLException
	{
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		String query = "update emp set sal = 1000 where emp_id = 5";
		
		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		
		System.out.println(rows);
		con.close();
		
	}
}