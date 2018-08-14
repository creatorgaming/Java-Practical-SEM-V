package dev.project.rmi.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

class testClass{
	public int add(int x, int y) {
		return x+y;
	}
};

public class ServerDriver {
	
	private ServerSocket server = null;
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	private String messageReceived = null;
	
	public ServerDriver(int port) {
		try {
			
			server = new ServerSocket(port);
			System.out.println("# SERVER listening at port " + port +" ... ");
			System.out.println("# Client connecting ... ");
			
			socket = server.accept();
			System.out.println("# Connection established ... ");
			
			input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			output = new DataOutputStream(socket.getOutputStream());
			
			mainControl();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	private void mainControl() {
		try {
			messageReceived = input.readUTF();
			classRetriver(messageReceived);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void classRetriver(String messageReceived) {
		String[] element = messageReceived.split("/");
		String className = element[0];
		String methodName = element[1];
		int arg1 = Integer.parseInt(element[2]);
		int arg2 = Integer.parseInt(element[3]);
		try {
			Class<?> cls = Class.forName(className);
			Class<?>[] params = {int.class, int.class};
			Object object = cls.newInstance();
			Method method = cls.getDeclaredMethod(methodName,params);
			int result = (int) method.invoke(object, arg1, arg2);
			output.writeUTF("Your sum is " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeConnection() {
		try {
			server.close();
			socket.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
