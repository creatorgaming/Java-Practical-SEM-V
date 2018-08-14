package dev.project.rmi.client;

import java.util.Scanner;

public class clientMain {
	
	private static String ip = null;
	private static int port = 0;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter server IP : ");
		ip = input.next();
		System.out.print("Enter server port : ");
		port = input.nextInt();
		new clientDriver(ip,port);
//		new clientDriver("127.0.0.1",5000);
	}
}
