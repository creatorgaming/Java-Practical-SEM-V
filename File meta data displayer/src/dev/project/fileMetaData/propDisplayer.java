package dev.project.fileMetaData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.util.Scanner;

public class propDisplayer {
	
	private String path;
	private File file;

	void input() {
		System.out.print("Enter the path of file : ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		path = input.nextLine();
		System.out.println("");
		
		displayInfo();
	}
	
	void displayInfo() {
		file = new File(path);	
		if(file.exists()) {
			if(file.isDirectory()) {
				System.out.println("\nDirectory Properties...");
				displayDirectoryProperties();
				System.out.println("\nDirectory Contents...");
				displayDirectoryContents();
			}else {
				System.out.println("\nFile Content...");
				displayFileContents();
				System.out.println("\nFile Properties...");
				displayFileProperties();
			}
		}else {
			System.out.println("File/Directory does not exists.");
		}
	}
	
	void displayDirectoryContents() {
		File[] listOfFiles = file.listFiles();
		for (File file2 : listOfFiles) {
			if(file2.isFile()) {
				System.out.println("File 	  --> " + file2.getName());
			}else if(file2.isDirectory()) {
				System.out.println("Directory --> " + file2.getName());
			}
		}
	}
	
	long getFolderSize(File file) {
		long size = 0;
	    File[] files = file.listFiles();	 
	    for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile()) {
	            size += files[i].length();
	        }
	        else {
	            size += getFolderSize(files[i]);
	        }
	    }
		return size;
	}
	
	void displayDirectoryProperties(){
		Path path = file.toPath();
		BasicFileAttributes attributes = null;
		try {
			attributes = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File Name	   --> " + file.getName());
		System.out.println("File Size	   --> " + (getFolderSize(file)/(1024*1024*1024)) + " GB");
		System.out.println("File Created  On   --> " + attributes.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
		System.out.println("File Modified On   --> " + attributes.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
		System.out.println("File Accessed On   --> " + attributes.lastAccessTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
		System.out.println("File Absolute Path --> " + file.getAbsolutePath());		
	}
	
	void displayFileContents(){
		if(file.canRead()) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String line = null;
				 while ((line = br.readLine()) != null) {
				   System.out.println(line);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("!!!! Cannot Print the file !!!!");
		}
	}
	
	void displayFileProperties() {
		Path path = file.toPath();
		BasicFileAttributes attributes = null;
		try {
			attributes = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File Name	   --> " + file.getName());
		System.out.println("File Type	   --> " + getFileExtension(file));
		if(file.length() > (1024*1024)) {
			System.out.println("File Size	   --> " + (file.length()/(1024*1024)) + " MB");	
		}else if (file.length() > (1024)) {
			System.out.println("File Size	   --> " + (file.length()/(1024)) + " KB");
		}else {
			System.out.println("File Size	   --> " + file.length() + " Bytes");
		}
		
		System.out.println("File Created  On   --> " + attributes.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
		System.out.println("File Modified On   --> " + attributes.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
		System.out.println("File Accessed On   --> " + attributes.lastAccessTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().replaceAll("T", " "));
		System.out.println("File Absolute Path --> " + file.getAbsolutePath());
	}
	
	private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        else 
        	return "";
    }
}
