package dev.project.bankingOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	
	public Admin() throws SQLException, InterruptedException {
		adminMenu();
	}
	
	@SuppressWarnings("unused")
	private int autoGenerateAccountNo() {
		int accountNo = 0;
		
		return accountNo;
	}
	
	private void addAccount() throws SQLException {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n\n\n");
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t           |||| DT-BANK | ADMIN ||||");
		System.out.println("\t--------------------------------------------------");
		System.out.println("\t\t --------------------------------");
		System.out.print("\t\t      Enter Account Details ");
		System.out.println("\n\t\t --------------------------------");
		System.out.println("\n");
		System.out.println("\t\t --------------------------------");
		System.out.print("\t\t | Name        : ");
		String name = inp.nextLine();
		System.out.print("\t\t | Age         : ");
		int age = inp.nextInt();
		System.out.print("\t\t | Phone No.   : ");
		String phoneNo = inp.next();
		System.out.print("\t\t | Account No. : ");
		String accountNo = inp.next();
		System.out.println("\t\t --------------------------------");
		Account account = new Account (accountNo);
		boolean confirm = account.createAccount(name,age,phoneNo);	
		if (confirm) {
			System.out.println("\t ------------------------------------------------------");
			System.out.print("\t        !! Account Created Successfully!!");
			System.out.println("\n\t ------------------------------------------------------");
		}else {
			System.out.println("\t ------------------------------------------------------");
			System.out.print("\t       !! Unable to create account , Please Try Again !!");
			System.out.println("\n\t ------------------------------------------------------");
		}
		
		account.closeConnection();
		
		try {
			Thread.sleep(2000);
			adminMenu();
		} catch (InterruptedException e) {
			System.out.print("# Thread Error : ");
			e.printStackTrace();
		}
//		System.out.println(autoGenerateAccountNo());	
	}

	private void viewTransactions() throws SQLException, InterruptedException {
		Account account = new Account();
		ResultSet transactions = account.todayTransactions();
		int transactionNo = 1;
		System.out.println("\n\n\n\n\n");
		System.out.println("\t\t\t\t--------------------------------------------------");
		System.out.println("\t\t\t\t           |||| DT-BANK | ADMIN ||||");
		System.out.println("\t\t\t\t--------------------------------------------------");
		System.out.println("\t\t\t\t      -----------------------------------");
		System.out.println("\t\t\t\t  	    TRANSACTIONS - [" + java.time.LocalDate.now().toString() + "]");
		System.out.println("\t\t\t\t      -----------------------------------");
		System.out.println("");
		System.out.println(" ******************************************************************************************************************************");
		System.out.println("  SNO.     ACCOUNT NO.		  DATE			AMOUNT DEPOSITED		AMOUNT WITHDRAWN		BALANCE");
		System.out.println(" ------------------------------------------------------------------------------------------------------------------------------");
		while (transactions.next()) {
			System.out.print("   " + transactionNo  + "\t     "
							+ transactions.getInt("acno") + "		  "
							+ transactions.getDate("date"));
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
				
		transactions.close();
		account.closeConnection();
		Thread.sleep(5000);
	}
	
	private void adminMenu() throws SQLException, InterruptedException {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		mainWhileLoop:
		while (true) {
			System.out.println("\n\n\n");
			System.out.println("\t\t\t\t--------------------------------------------------");
			System.out.println("\t\t\t\t           |||| DT-BANK | ADMIN ||||");
			System.out.println("\t\t\t\t--------------------------------------------------");
			System.out.println("\t\t\t\t\t ------------------------");
			System.out.println("\t\t\t\t\t |   1. Add Account");
			System.out.println("\t\t\t\t\t |   2. View Today's Transactions");
			System.out.println("\t\t\t\t\t |   3. Return to main Screen");
			System.out.println("\t\t\t\t\t |   4. Exit");
			System.out.println("\t\t\t\t\t ------------------------");
			System.out.print("\t\t\t\t\tChoice --> ");
			int choice = inp.nextInt();
			switch (choice) {
			case 1:
				addAccount();
				break;
			case 2:
				viewTransactions();
				break;
			case 3:
				break mainWhileLoop;
			case 4:
				
				System.exit(1);
				break;
			default:
				adminMenu();
				break;
			}			
		}
	}
}
