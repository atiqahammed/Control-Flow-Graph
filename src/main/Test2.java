package main;

import java.io.File;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final File folder = new File("JDeodorant");
		listFilesForFolder(folder);
		
		
	}
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	if(fileEntry.getName().endsWith(".java")){
	        		System.out.println(fileEntry.getPath());
	        	}
	            
	        }
	    }
	}

}
