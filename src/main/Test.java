package main;

import java.util.ArrayList;

import class_analyzer.MethodFinder;
import code_reader.CodeReader;
import projectOfDataClass.Statement;

public class Test {

	public static void main(String[] args) {
		System.out.println("Control Flow Graph");
		System.out.println("++++++++++++++++++");
		System.out.println();
		System.out.println();

		CodeReader codeReader = new CodeReader();
		ArrayList<Statement> lines = codeReader.redeCode("Codes/code1.java");
		
		
		MethodFinder finder = new MethodFinder();
		
		
		
		
		
		finder.findMethods(lines);
		
		
		
		
		
	}
	

}
