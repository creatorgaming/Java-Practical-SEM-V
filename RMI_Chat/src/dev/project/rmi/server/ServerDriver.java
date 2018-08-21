package dev.project.rmi.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class testClass{
	public int add(int x, int y) {
		return x+y;
	}
	public double add(double x, double y) {
		return x+y;
	}
	public double add(int x, int y, double z) {
		return x+y+z;
	}
};

public class ServerDriver {
	
	private ServerSocket server = null;
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	private String messageReceived = null;
	private String className = null;
	private String methodName = null;
	private String[] element = null;
	private Class<?>[] params = null;
	private Object[] args = null;
	private int noOfArgs = 0;
	
	private ArrayList<String> dataTypes = new ArrayList<String>();
	private ArrayList<String> dataValues = new ArrayList<String>();
	
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
	
	private void extractArgs(int noOfArgs) throws IOException {
		int checkArgsBit = 0;
		for (int i = 3; i < element.length; i += 2) {
			dataTypes.add(element[i]);
			dataValues.add(element[i+1]);
			checkArgsBit++;
		}
		if(checkArgsBit != noOfArgs)
			errorDisplay();
	}
	
	private void extractParams() throws IOException {
		int paramsIndex = 0;
		for (String string : dataTypes) {	
			switch (string) {
			case "int":
				params[paramsIndex++] = int.class;
				args[paramsIndex++] = Integer.parseInt(dataValues.get(paramsIndex));
				break;

			case "float":
				params[paramsIndex++] = float.class;
				break;

			case "double":
				params[paramsIndex++] = double.class;
				break;
				
			case "char":
				params[paramsIndex++] = char.class;
				break;

			default:
				errorDisplay();
				break;
			}
		}
	}
	
	private void extractArgs() {
		for (String dataType : dataValues) {
			switch (dataValues) {
				case "":
				
					break;
				case "":
					
					break;
				case "":
					
					break;
				case "":
					
					break;

				default:
					break;
			}
		}
	}
	
	private void classRetriver(String messageReceived) throws IOException {
		element = messageReceived.split("/");
		className = element[0];
		methodName = element[1];
		noOfArgs = Integer.parseInt(element[2]);
		extractArgs(noOfArgs);
		
		try {
			Class<?> cls = Class.forName(className);
			params = new Class<?>[noOfArgs];
			args = new Object[noOfArgs];
			extractParams();
			extractArgs();
			@SuppressWarnings("unused")
			Object object = cls.newInstance();
			Method method = cls.getDeclaredMethod(methodName,params);
			System.out.println(method.toGenericString());
			output.writeUTF("Your result is " + method.invoke(object, args));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void errorDisplay() throws IOException {
		output.writeUTF("!! WRONG INPUT !!");
		closeConnection();
		System.exit(1);
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
