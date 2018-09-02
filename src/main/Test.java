package main;

import java.util.ArrayList;

import code_reader.CodeReader;

public class Test {

	public static void main(String[] args) {
		System.out.println("Control Flow Graph");
		System.out.println("++++++++++++++++++");
		System.out.println();
		System.out.println();
		
		CodeReader codeReader= new CodeReader();
		ArrayList<String> lines = codeReader.redeCode("Codes/code1.java");
		System.out.println(lines.size());
		
		
		for(String string: lines)
			System.out.println(string);

	}

}
