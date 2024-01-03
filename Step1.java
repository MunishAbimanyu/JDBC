package LoadingDriver;

import java.sql.*;

public class Step1 {

	public static void main(String[] args) throws Exception {
		batch();
	}

	// Reading the Data RDBMS to Java Application
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

	// Insert the Data Directly
	public static void insertRecord() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		String query = "insert into emp values(4,'Munish',400)";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);

		System.out.println("Number of rows Affected " + rows);
		con.close();
	}

	// Insert the Data By Using Variable
	public static void insertVar() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";

		int id = 5;
		String name = "Abimanyu";
		int sal = 6000;
		String query = "insert into emp values(" + id + ",'" + name + "'," + sal + ");";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);

		System.out.println("Number of rows Affected " + rows);
		con.close();
	}

	// Inset the Date By Using Prepared Statement
	public static void insertpst() throws Exception {
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

	// Delete the Date By Using Query
	public static void delete() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		String query = "delete from emp where emp_id = 5";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);

		System.out.println(rows);
		con.close();

	}

	public static void update() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";
		String query = "update emp set sal = 1000 where emp_id = 3";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);

		System.out.println(rows);
		con.close();
	}

	// Show the Value by Using Procedure
	public static void procedure() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		CallableStatement cs = con.prepareCall("{call GetAbc()}");
		ResultSet rs = cs.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getInt(3));
		}
		con.close();
	}

	// Procedure Passing Value:
	public static void procedure2() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";

		int id = 2;
		Connection con = DriverManager.getConnection(url, userName, passWord);
		CallableStatement cs = con.prepareCall("{call GetBcd(?)}");
		cs.setInt(1, id);
		ResultSet rs = cs.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getInt(3));
		}
		con.close();
	}

	// Procedure Passing and Return Value:
	public static void procedure3() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String useName = "root";
		String passWord = "root";

		int id = 1;
		Connection con = DriverManager.getConnection(url, useName, passWord);
		CallableStatement cst = con.prepareCall("{call GetThird(?,?)}");
		cst.setInt(1, id);
		cst.registerOutParameter(2, Types.VARCHAR);
		cst.executeUpdate();

		System.out.println(cst.getString(2));

		con.close();
	}

	// Commit:
	public static void commits() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";

		String query1 = "update emp set sal = 100 where emp_id = 1";
		String query2 = "update emp set sal = 200 where emp_id = 2";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		int rows1 = st.executeUpdate(query1);
		System.out.println(rows1);

		int rows2 = st.executeUpdate(query2);
		System.out.println(rows2);

		if (rows1 > 0 && rows2 > 0) {
			con.commit();
		}
		con.close();
	}

	// Creating the Batch
	public static void batch() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String userName = "root";
		String passWord = "root";

		String query1 = "update emp set sal = 1000 where emp_id = 1";
		String query2 = "update emp set sal = 2000 where emp_id = 2";
		String query3 = "update emp set sal = 3000 where emp_id = 3";
		String query4 = "update emp set sal = 4000 where emp_id = 4";
		String query5 = "update emp set sal = 5000 where emp_id = 5";

		Connection con = DriverManager.getConnection(url, userName, passWord);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
		st.addBatch(query4);
		st.addBatch(query5);
		int arr[] = st.executeBatch();
		for (int i : arr) {
			if (i > 0) {
				continue;
			} else {
				con.rollback();
			}
			System.out.println(arr[i]);
		}

		con.commit();
		con.close();
		/*
		 * if(arr.length == 5) { con.commit(); }
		 */
	}
}
