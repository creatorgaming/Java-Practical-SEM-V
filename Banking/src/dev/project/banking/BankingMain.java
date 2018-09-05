package dev.project.banking;

import java.util.Scanner;

public class BankingMain {
	static private Scanner inp = null;
	public static void main(String[] args) {
		System.out.println("!!!!!!!!!!!!!!!!!! BANKING !!!!!!!!!!!!!!!!!!\n");
		System.out.println("\t\t  1. User");
		System.out.println("\t\t  2. Admin");
		System.out.println("\t\t  3. Exit");
		System.out.print("\n\t  Choice --> ");
		inp = new Scanner(System.in);
		int x = inp.nextInt();
		if (x == 1) {
			new Menu("user");
		}else if (x == 2) {
			new Menu("admin");
		}else if (x == 3) {			
			System.out.println("EXITING ....");
			System.exit(1);
		}else {
			System.out.println("!!!! INVALID OPTION !!!!");
		}
	}
}
