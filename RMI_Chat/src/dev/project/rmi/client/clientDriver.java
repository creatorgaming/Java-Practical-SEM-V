package dev.project.rmi.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class clientDriver {

	private Socket socket = null;
	private String messageSent = "2";
	private DataOutputStream out = null;
	private DataInputStream in = null;
	
	public clientDriver(String address,int port) {
		try {
			socket = new Socket(address,port);
			System.out.println("# Socket Created...");
			in = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
			displayData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}
	
	private void displayData() {
		
	}
	
	private void closeConnection() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
