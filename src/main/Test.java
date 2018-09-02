package main;

import code_reader.CodeReader;

public class Test {

	public static void main(String[] args) {
		System.out.println("Control Flow Graph");
		System.out.println("++++++++++++++++++");
		System.out.println();
		System.out.println();
		
		CodeReader codeReader= new CodeReader();
		codeReader.redeCode("Codes/code1.java");

	}

}
