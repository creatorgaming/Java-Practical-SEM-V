package dev.project.banking;

import java.sql.SQLException;
import java.util.Scanner;

public class BankingMain {
	@SuppressWarnings("unused")
	private static Scanner inp = null;
	public static void main(String[] args) throws SQLException {
//		new Menu("admin");
		while (true) {
			System.out.println("\t\t-----------------");
			System.out.println("\t\t|||| DT-BANK ||||");
			System.out.println("\t\t-----------------");
			System.out.println("\t\t   ----------");
			System.out.println("\t\t    1. User");
			System.out.println("\t\t    2. Admin");
			System.out.println("\t\t    3. Exit");
			System.out.println("\t\t   ----------");
			System.out.print("\t\tChoice --> ");
			inp = new Scanner(System.in);
			int x = inp.nextInt();
			if (x == 1) {
				new Menu("user");
			}else if (x == 2) {
				new Menu("admin");
			}else if (x == 3) {			
				System.out.println("\t\tEXITING ....");
				System.exit(1);
			}else {
				System.out.println("\n\t    !!!! INVALID OPTION !!!!");
				continue;
			}			
		}
	}
}
