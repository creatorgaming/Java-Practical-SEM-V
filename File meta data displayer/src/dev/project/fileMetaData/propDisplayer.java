package dev.project.fileMetaData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class propDisplayer {
	
	private String path;
	private File file;

	void input() {
		System.out.print("Enter the path of file : ");
		Scanner input = new Scanner(System.in);
		path = input.nextLine();
		System.out.println("");
		
		displayInfo();
	}
	
	void displayInfo() {
		file = new File(path);	
		if(file.exists()) {
			if(file.isDirectory()) {
				displayDirectoryDetails();
			}else {
				displayFileProperties();
			}
		}else {
			System.out.println("File/Directory does not exists.");
		}
	}
	
	void displayDirectoryDetails() {
		File[] listOfFiles = file.listFiles();
		for (File file2 : listOfFiles) {
			if(file2.isFile()) {
				System.out.println("File 	  --> " + file2.getName());
			}else if(file2.isDirectory()) {
				System.out.println("Directory --> " + file2.getName());
			}
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
		System.out.println("File Size	   --> " + (file.length()/(1024*1024)) + " MB");
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
