package dev.project.bankingOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	
	private String accountNo = null;
	private Connection conn = null;

	public Account() {
		conn = Connector.createConnection();
	}
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
		conn = Connector.createConnection();
	}
	
	private int getBalance() throws SQLException {
		int balance = 0;
		String query = "SELECT `currentBalance` "
				     + "FROM `accountholderinfo` WHERE acno = " + accountNo;
		PreparedStatement stt = conn.prepareStatement(query);
		ResultSet result = stt.executeQuery();
		while (result.next()) {
			balance = result.getInt("currentBalance");
		}
		stt.close();		
		return balance;
	}
	
	private void transactionsUpdate(int amountDeposited, int amountWithdrawn, int balance) throws SQLException {
		String query = null;
		PreparedStatement stt = null;
		if (amountDeposited > 0) {
			query = "INSERT INTO `transactions`(`acno`, `date`, `amountDeposited`, `balance`) "
					+ "VALUES (?,?,?,?)";
			stt = conn.prepareStatement(query);
			stt.setInt(1, Integer.parseInt(accountNo));
			stt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stt.setInt(3, amountDeposited);
			stt.setInt(4, balance);
			
		}else if (amountWithdrawn > 0) {
			query = "INSERT INTO `transactions`(`acno`, `date`, `amountWithdrawn`, `balance`) "
					+ "VALUES (?,?,?,?)";
			stt = conn.prepareStatement(query);
			stt.setInt(1, Integer.parseInt(accountNo));
			stt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
			stt.setInt(3, amountWithdrawn);
			stt.setInt(4, balance);
		}else {
			System.out.println("# INVALID TRANSACTION, EXITING ...");
			System.exit(1);
		}
		stt.execute();
		stt.close();
	}
	
	public boolean depositDriver(int depositAmount) throws SQLException {
		String query = "UPDATE `accountholderinfo` SET  currentBalance = currentBalance + " + depositAmount + 
			           " WHERE acno = " + accountNo;
		PreparedStatement stt = conn.prepareStatement(query);
		boolean confirm = false;
		int updatedRows = stt.executeUpdate();
		if (updatedRows > 0) {
			confirm = true;
		}
		stt.close();
		transactionsUpdate(depositAmount, 0, getBalance());
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
		transactionsUpdate(0, withdrawAmount, getBalance());
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
	
	public ResultSet accountTransactions() throws SQLException {
		ResultSet resultSet = null;
		PreparedStatement stt = null;
		String query = "SELECT * FROM `transactions` WHERE acno = " + accountNo;
		stt = conn.prepareStatement(query);
		resultSet = stt.executeQuery();
		return resultSet;
	}
		
	public ResultSet todayTransactions() throws SQLException {
		ResultSet resultSet = null;
		PreparedStatement stt = null;
		String query = "SELECT * FROM `transactions` WHERE date = '" + java.sql.Date.valueOf(java.time.LocalDate.now()).toString() + "'";
		stt = conn.prepareStatement(query);
		resultSet = stt.executeQuery();
		return resultSet;
	}
	
	public void closeConnection() throws SQLException {
		conn.close();
		Connector.closeConnection();
	}
}
