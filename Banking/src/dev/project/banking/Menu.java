package dev.project.banking;


import java.sql.SQLException;
import java.util.Scanner;

import dev.project.bankingOperation.*;

public class Menu {
	
	static final String password = "1234";
	
	public Menu(String person) throws SQLException, InterruptedException {
		if (person.equalsIgnoreCase("user")) {
			userMenu();
		}else if (person.equalsIgnoreCase("admin")) {
			adminMenu();
		}
	}
	
	@SuppressWarnings("resource")
	private void userMenu() throws SQLException {
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n\n\n\n\n");
		System.out.println("\t\t--------------");
		System.out.println("\t\t|||| USER ||||");
		System.out.println("\t\t--------------");
		System.out.print("\n\t   Enter account number : ");
		String accountNo = inp.nextLine();
		new User(accountNo);
	}

	@SuppressWarnings("resource")
	private void adminMenu() throws SQLException, InterruptedException {
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
