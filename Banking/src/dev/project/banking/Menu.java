package dev.project.banking;


import java.util.Scanner;

import dev.project.bankingOperation.*;

public class Menu {
	
	static final String password = "1234";
	
	public Menu(String person) {
		if (person.equalsIgnoreCase("user")) {
			userMenu();
		}else if (person.equalsIgnoreCase("admin")) {
			adminMenu();
		}
	}
	
	@SuppressWarnings("resource")
	private void userMenu() {
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n\n");
		System.out.println("\t\t--------------");
		System.out.println("\t\t|||| USER ||||");
		System.out.println("\t\t--------------");
		System.out.print("\n\t   Enter account number : ");
		int accountNo = inp.nextInt();
		new User(accountNo);
	}

	@SuppressWarnings("resource")
	private void adminMenu() {
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("\t\t---------------");
		System.out.println("\t\t|||| ADMIN ||||");
		System.out.println("\t\t---------------");
		System.out.print("\n\t      Enter password : ");
		String pass = inp.next();
		if (!pass.equals(password)) {
			System.out.println("\t  !!!! Invalid Password !!!!");
			adminMenu();
		}
		
		new Admin();
	}
	
}
