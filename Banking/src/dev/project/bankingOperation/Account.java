package dev.project.bankingOperation;

import java.sql.*;

public class Account {
	
	private String accountNo = null;
	private Connection conn = null;
	public Account(String accountNo) {
		this.accountNo = accountNo;
		conn = Connector.createConnection();
	}
	
	public boolean depositDriver(int depositAmount) throws SQLException {
		String query = "UPDATE `accountholderinfo` SET  currentBalance = currentBalance + " + depositAmount + 
			           " WHERE acno = " + accountNo;
		PreparedStatement stt = conn.prepareStatement(query);
		boolean confirm = false;
		stt.execute();
		int updatedRows = stt.executeUpdate();
		if (updatedRows > 0) {
			confirm = true;
		}
		stt.close();
		return confirm;
	}
	
	public boolean withdrawDriver(int withdrawAmount) throws SQLException {
		boolean confirm = false;
		int currentBalance = 0;
		String query_1 = "SELECT `currentBalance` FROM `accountholderinfo` WHERE acno = " + accountNo;
		PreparedStatement stt_1 = conn.prepareStatement(query_1);
		ResultSet result = stt_1.executeQuery();
		while (result.next()) {
			currentBalance = result.getInt("currentBalance");
		}
		if (currentBalance > withdrawAmount) {			
			int newBalance = currentBalance - withdrawAmount;
			String query_2 = "UPDATE `accountholderinfo` SET  currentBalance = " + newBalance + 
			           " WHERE acno = " + accountNo;
			PreparedStatement stt_2 = conn.prepareStatement(query_2);
			stt_2.execute();
			int updatedRows = stt_2.executeUpdate();
			if (updatedRows > 0) {
				confirm = true;
			}			
			stt_2.close();
		}
		result.close();
		stt_1.close();
		return confirm;
	}
	
	public ResultSet accountDetails() throws SQLException {
		String query = "SELECT `acno`, `name`, `age`, `phoneNo`, `currentBalance` FROM `accountholderinfo` "
				        + "WHERE acno = " + accountNo;
		PreparedStatement stt = conn.prepareStatement(query);
		ResultSet result = stt.executeQuery();
		return result;
	}
	
	public boolean createAccount(String name, int age, String phoneNo) throws SQLException {
		String query = "INSERT INTO `accountholderinfo`(`acno`, `name`, `age`, `phoneNo`, `currentBalance`) "
				        + "VALUES (?,?,?,?,?)";
		PreparedStatement stt = conn.prepareStatement(query);
		stt.setInt(1, Integer.parseInt(accountNo));
		stt.setString(2, name);
		stt.setInt(3, age);
		stt.setString(4, phoneNo);
		stt.setInt(5, 0);
		boolean confirm = false;
		int updatedRows = stt.executeUpdate();
		if (updatedRows > 0) {
			confirm = true;
		}
		stt.close();
		return confirm;		
	}
}
