package dev.project.banking;

import java.util.Scanner;

import dev.project.bankingOperation.*;

public class Menu {
	
	static final String password = "1234";
	
	public Menu(String person) {
		System.out.flush();
		if (person.equalsIgnoreCase("user")) {
			userMenu();
		}else if (person.equalsIgnoreCase("admin")) {
			adminMenu();
		}
	}
	
	@SuppressWarnings("resource")
	private void userMenu() {
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n!!!!!!!!!!!!!!!!!! USER !!!!!!!!!!!!!!!!!!");
		System.out.print("\n\t\tEnter account number : ");
		int accountNo = inp.nextInt();
		new User(accountNo);
	}

	@SuppressWarnings("resource")
	private void adminMenu() {
		Scanner inp = new Scanner(System.in);
		System.out.println("\n\n!!!!!!!!!!!!!!!!!! ADMIN !!!!!!!!!!!!!!!!!!");
		System.out.print("\n\t\tEnter password : ");
		String pass = inp.next();
		if (!pass.equals(password)) {
			System.out.println("!!!! Invalid Password !!!!");
			System.exit(1);
		}
		
		new Admin();
	}
	
}