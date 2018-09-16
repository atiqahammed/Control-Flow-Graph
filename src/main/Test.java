package main;

import java.util.ArrayList;

import class_analyzer.MethodFinder;
import code_reader.CodeReader;
import projectOfDataClass.Statement;
import statementAnalyzer.Analyser;

public class Test {

	public static void main(String[] args) {
		System.out.println("Control Flow Graph");
		System.out.println("++++++++++++++++++");
		System.out.println();
		System.out.println();

		CodeReader codeReader = new CodeReader();
		ArrayList<Statement> lines = codeReader.redeCode("Codes/code1.java");
		
		MethodFinder finder = new MethodFinder();
		ArrayList<ArrayList<Statement>> allMethods = finder.findMethods(lines);
		
		System.out.println(allMethods.size());
		
		//Analyser analyser = new Analyser();
		//analyser.analyzeStatement(allMethods.get(4));
		
		
		
		
		
		for(int i = 0; i < allMethods.size(); i++) {
			System.out.println(".........................");
			Analyser analyser = new Analyser();
			analyser.analyzeStatement(allMethods.get(i));
			
			System.out.println();
			System.out.println();
			
			System.out.println("======================================================");
		}
		
		
		
		
	}
	

}
