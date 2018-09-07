package dev.project.bankingOperation;

import java.util.Scanner;

public class User {
	
	private static int accountNo = 0;
	
	public User(int accountNo) {
		this.accountNo = accountNo;
		deposit();
//		userMenu();
	}
	
	private void deposit() {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n\n\n");
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
			boolean confirm = depositDriver(depositAmount);
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
	
	private boolean depositDriver(int depositAmount) {
		return false;
		
	}
	
	private void withdraw() {
		
	}
	
	private void accDetails() {
		
	}
	
	private void passbook() {
		
	}
	
	private void userMenu() {
		@SuppressWarnings("resource")
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("\t\t--------------------------------------------------");
		System.out.println("\t\t           |||| DT-BANK | USER - " + accountNo +" ||||");
		System.out.println("\t\t--------------------------------------------------");
		System.out.println("\t\t\t -----------------------");
		System.out.println("\t\t\t    1. Deposit");
		System.out.println("\t\t\t    2. Withdraw");
		System.out.println("\t\t\t    3. View Account Details");
		System.out.println("\t\t\t    4. View Passbook");
		System.out.println("\t\t\t    5. Exit");
		System.out.println("\t\t\t -----------------------");
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
			System.exit(1);
			break;
		default:
			userMenu();
			break;
		}
	}
}
