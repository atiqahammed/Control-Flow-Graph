package main;

import java.io.File;
import java.util.ArrayList;

import class_analyzer.MethodFinder;
import code_reader.CodeReader;
import projectOfDataClass.Statement;
import statementAnalyzer.Analyser;

public class Test3 {

	// public static void main(String[] args) {
	// TODO Auto-generated method stub

	public static void main(String[] args) {

		final File folder = new File("spl");
		listFilesForFolder(folder);

	}

	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if (fileEntry.getName().endsWith(".java")) {
					System.out.println(fileEntry.getPath());
					
					
					System.out.println("Control Flow Graph");
					System.out.println("++++++++++++++++++");
					System.out.println();
					System.out.println();

					
					//System.out.println(fileEntry.getAbsolutePath());
					CodeReader codeReader = new CodeReader();
					ArrayList<Statement> lines = codeReader.redeCode(fileEntry.getPath());
					
					System.out.println(lines.size());
					
					MethodFinder finder = new MethodFinder();
					ArrayList<ArrayList<Statement>> allMethods = finder.findMethods(lines);
					
					//System.out.println(allMethods.size());
					
					Analyser analyser = new Analyser();
					
					int totalCyclomaticNumber = 0;
					
					for(int i = 0; i < allMethods.size(); i++) {
						System.out.println(".........................");
						
						totalCyclomaticNumber += analyser.analyzeStatement(allMethods.get(i));
						
						System.out.println();
						System.out.println();
					}
					System.out.println("==============================================");
					System.out.println("total = "+totalCyclomaticNumber);

				}

			}
		}
	}

	// }

}
