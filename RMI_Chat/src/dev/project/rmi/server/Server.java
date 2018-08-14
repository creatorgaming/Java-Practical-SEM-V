package dev.project.rmi.server;

import java.util.Scanner;

public class Server {
	
	private static int port = 0;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter server port : ");
		port = input.nextInt();
		System.out.println("");
		new ServerDriver(port);
	}
}
