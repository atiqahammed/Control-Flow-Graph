package PackOfStaticAnalysis;

import java.util.ArrayList;

public class Print {
	
	public static void showDetails(String tokens[],ArrayList<String> intL,ArrayList<String> DoubleL,
	  ArrayList<String> FloatL,ArrayList<String> CharL,ArrayList<String> boolL,ArrayList<String> stringL)
	{
		
		int size_int, size_double, size_float, size_char, size_bool, size_string;
		size_int=intL.size();		size_double=DoubleL.size();
		size_float=FloatL.size();	size_char=CharL.size();
		size_bool=boolL.size();		size_string=stringL.size();
		
		System.out.print("\n\n=====VARIABLE ANALYSIS=====\n\n");
		System.out.println("\nNumber of different types of variables:\n"+ "[TYPE]\t\t[NUMBERS]\n\n"
			+tokens[0]+"\t\t"+size_int+"\n"  +tokens[1]+"\t\t"+size_double+"\n"
			+tokens[2]+"\t\t"+size_float+"\n"  +tokens[3]+"\t\t"+size_char+"\n"
			+tokens[4]+"\t\t"+size_bool+"\n"  +tokens[5]+"\t\t"+size_string+"\n");
		
		System.out.print("\n\nName of the variables of different types:\n"+ "[TYPE]\t\t[NAME]\n\n");
		
		if(size_int!=0) {
			System.out.print(tokens[0]+"\t\t");
			for(String name:intL) {
				System.out.print(name+" ");
			}
			System.out.print("\n");
		}
		
		if(size_double!=0) {
			System.out.print(tokens[1]+"\t\t");
			for(String name:DoubleL) {
				System.out.print(name+" ");
			}
			System.out.print("\n");
		}
		
		if(size_float!=0) {
			System.out.print(tokens[2]+"\t\t");
			for(String name:FloatL) {
				System.out.print(name+" ");
			}
			System.out.print("\n");
		}
		
		if(size_char!=0) {
			System.out.print(tokens[3]+"\t\t");
			for(String name:CharL) {
				System.out.print(name+" ");
			}
			System.out.print("\n");
		}
		
		if(size_bool!=0) {
			System.out.print(tokens[4]+"\t\t");
			for(String name:boolL) {
				System.out.print(name+" ");
			}
			System.out.print("\n");
		}
		
		if(size_string!=0) {
			System.out.print(tokens[5]+"\t\t");
			for(String name:stringL) {
				System.out.print(name+" ");
			}
			System.out.print("\n");
		}
		
		System.out.print("\n");
	}
}
