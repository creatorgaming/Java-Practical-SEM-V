package dev.project.bankingOperation;

import java.sql.*;

public class Connector {
	private static String url      = "jdbc:mysql://localhost:3306/banking";
	private static String user     = "root";
	private static String password = "";
	private static Connection conn = null;
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("# Connection Created... Conn : " + conn);
		} catch (Exception e) {
			System.out.println("# Connection Error : ");
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("# Closing Error : ");
			e.printStackTrace();
		}
	}
}
