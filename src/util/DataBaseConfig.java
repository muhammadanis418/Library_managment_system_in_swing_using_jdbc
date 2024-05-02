package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConfig {

	Connection con = null;
	private static String url = "jdbc:mysql://localhost:3306/book";
	private static String user = "root";
	private static String password = "root";

	public static Connection getDbConnection() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, user, password);
		}
		
		catch (Exception ex) {
			System.err.print("Connection not Established");
			return null;
		}
	}
}
