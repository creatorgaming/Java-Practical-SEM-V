package dev.project.rmi.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class clientDriver {

	private Socket socket = null;
	// Message Syntax  : ClassName/FunctionName/NoOfArguments/DataType_1/Arg_1/...
	// Default Message : dev.project.rmi.server.testClass/add/2/int/2/int/10
	private String messageSent = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	
	public clientDriver(String address,int port) {
		try {
			socket = new Socket(address,port);
			System.out.println("# Client : Socket Created at port " + port + "...");
			
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("# Client : Connection Created...");
			
			mainControl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}
	
	private void mainControl() {
		try {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.print("# Client : Enter the message : ");
			messageSent = input.next();
			out.writeUTF(messageSent);
			System.out.println("# Server : " + in.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeConnection() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
