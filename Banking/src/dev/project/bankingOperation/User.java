package dev.project.bankingOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class User {
	
	private String accountNo = null;
	private Account account;
	
	public User(String accountNo) throws SQLException {
		this.accountNo = accountNo;
		account = new Account(this.accountNo);
		userMenu();
	}
	
	private void deposit() throws SQLException {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t           |||| DT-BANK | USER - " + accountNo +" ||||");
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t\t -----------------------------------");
		System.out.print("\t\t    Enter Deposit Amount :  ");
		int depositAmount = inp.nextInt();
		System.out.println("\t ------------------------------------------------------");
		System.out.print("\t    Do you confirm to deposit Rs. " + depositAmount + " (y/n) ? : ");
		String confirmChoice = inp.next();
		if (confirmChoice.equalsIgnoreCase("y")) {
			boolean confirm = account.depositDriver(depositAmount);
			if (confirm) {
				System.out.println("\t ------------------------------------------------------");
				System.out.print("\t      Rs. " + depositAmount + " deposited in account no : " + accountNo);
				System.out.println("\n\t ------------------------------------------------------");
			}else {
				System.out.println("\t ------------------------------------------------------");
				System.out.print("\t       !! Unable to deposit , Please Try Again !!");
				System.out.println("\n\t ------------------------------------------------------");
			}
			try {
				Thread.sleep(2000);
				userMenu();
			} catch (InterruptedException e) {
				System.out.print("# Thread Error : ");
				e.printStackTrace();
			}
		}else if (confirmChoice.equalsIgnoreCase("n")) {
			userMenu();
		}else {
			deposit();
		}
	}
		
	private void withdraw() throws SQLException {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n\n\n\n\n");
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t           |||| DT-BANK | USER - " + accountNo +" ||||");
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t\t -----------------------------------");
		System.out.print("\t\t    Enter Withdraw Amount :  ");
		int withdrawAmount = inp.nextInt();
		System.out.println("\t ------------------------------------------------------");
		System.out.print("\t    Do you confirm to withdraw Rs. " + withdrawAmount + " (y/n) ? : ");
		String confirmChoice = inp.next();
		if (confirmChoice.equalsIgnoreCase("y")) {
			boolean confirm = account.withdrawDriver(withdrawAmount);
			if (confirm) {
				System.out.println("\t ------------------------------------------------------");
				System.out.print("\t      Rs. " + withdrawAmount + " withdrawn from account no : " + accountNo);
				System.out.println("\n\t ------------------------------------------------------");
			}else {
				System.out.println("\t ------------------------------------------------------");
				System.out.print("\t       !! Unable to withdraw , Please Try Again !!");
				System.out.println("\n\t ------------------------------------------------------");
			}
			try {
				Thread.sleep(2000);
				userMenu();
			} catch (InterruptedException e) {
				System.out.print("# Thread Error : ");
				e.printStackTrace();
			}
		}else if (confirmChoice.equalsIgnoreCase("n")) {
			userMenu();
		}else {
			withdraw();
		}

	}
	
	private void accDetails() throws SQLException {
		ResultSet result = account.accountDetails();
		while (result.next()) {
			System.out.println("\n\n\n\n\n");
			System.out.println("\t--------------------------------------------------");
			System.out.println("\t           |||| DT-BANK | ADMIN ||||");
			System.out.println("\t--------------------------------------------------");
			System.out.println("\t\t -------------------------------------");
			System.out.println("\t\t | Name            : "+result.getString("name"));
			System.out.println("\t\t | Age             : "+result.getInt("age"));
			System.out.println("\t\t | Phone No.       : "+result.getString("phoneNo"));
			System.out.println("\t\t | Current Balance : "+result.getInt("currentBalance"));
			System.out.println("\t\t -------------------------------------");				
		}
		try {
			Thread.sleep(4000);
			userMenu();
		} catch (InterruptedException e) {
			System.out.print("# Thread Error : ");
			e.printStackTrace();
		}

		result.close();
	}
	
	private void passbook() throws SQLException {
		ResultSet transactions = account.accountTransactions();
		int transactionNo = 1;
		System.out.println("\n\n\n\n\n");
		System.out.println("\t\t--------------------------------------------------");
		System.out.println("\t\t           |||| DT-BANK | USER - " + accountNo +" ||||");
		System.out.println("\t\t--------------------------------------------------");
		System.out.println("\t\t	  -----------------------");
		System.out.println("\t\t  	   	 PASSBOOK   	     ");
		System.out.println("\t\t	  -----------------------");
		System.out.println("");
		System.out.println(" ***************************************************************************************************************");
		System.out.println("  SNO.		  DATE			AMOUNT DEPOSITED		AMOUNT WITHDRAWN		BALANCE");
		System.out.println(" ---------------------------------------------------------------------------------------------------------------");
		while (transactions.next()) {
			System.out.print("  " + transactionNo + "		  " + transactions.getDate("date"));
			if (transactions.getInt("amountDeposited") == 0) {
				System.out.print("\t\t      ");				
			}else {
				System.out.print("\t\t      " + transactions.getInt("amountDeposited"));
			}
			
			if (transactions.getInt("amountWithdrawn") == 0) {
				System.out.print("\t\t\t\t      ");				
			}else {
				System.out.print("\t\t\t\t      " + transactions.getInt("amountWithdrawn"));
			}
			System.out.print( "\t\t\t" + transactions.getInt("balance"));
			System.out.println("");
			transactionNo++;
		}
		try {
			Thread.sleep(5000);
			userMenu();
		} catch (InterruptedException e) {
			System.out.print("# Thread Error : ");
			e.printStackTrace();
		}
		transactions.close();
	}
	
	private void userMenu() throws SQLException {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		mainWhileLoop:
		while (true) {
			System.out.println("\n\n\n");
			System.out.println("\t\t--------------------------------------------------");
			System.out.println("\t\t           |||| DT-BANK | USER - " + accountNo +" ||||");
			System.out.println("\t\t--------------------------------------------------");
			System.out.println("\t\t\t ------------------------");
			System.out.println("\t\t\t |   1. Deposit");
			System.out.println("\t\t\t |   2. Withdraw");
			System.out.println("\t\t\t |   3. View Account Details");
			System.out.println("\t\t\t |   4. View Passbook");
			System.out.println("\t\t\t |   5. Return to Main Screen");
			System.out.println("\t\t\t |   6. Exit");
			System.out.println("\t\t\t ------------------------");
			System.out.print("\t\t\tChoice --> ");
			int choice = inp.nextInt();
			switch (choice) {
			case 1:
				deposit();
				break;
			case 2:
				withdraw();
				break;
			case 3:
				accDetails();
				break;
			case 4:
				passbook();
				break;
			case 5:
				break mainWhileLoop;
			case 6:
				account.closeConnection();
				System.exit(1);
				break;
			default:
				userMenu();
				break;
			}	
		}
	}
}
