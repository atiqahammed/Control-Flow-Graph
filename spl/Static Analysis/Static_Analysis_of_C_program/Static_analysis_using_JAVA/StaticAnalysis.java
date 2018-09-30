package PackOfStaticAnalysis;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;

public class StaticAnalysis {
	
	//static Scanner input= new Scanner(new File("input.txt"));
	
	public static String tokens[] = {"int","double","float","char","bool","string"};
	public static String delimeters = "[ ;'.\"){}]+";
	public static boolean integer=false, Double=false, Float=false, Char=false,
			bool=false, string=false;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input= new Scanner(new File("input.txt"));
		
		ArrayList<String> intL, DoubleL, FloatL, CharL, boolL, stringL;
		intL= new ArrayList();
		DoubleL= new ArrayList();
		FloatL= new ArrayList();
		CharL= new ArrayList();
		boolL= new ArrayList();
		stringL= new ArrayList();
		
		String line, lineSplit[],previousName=null;
		boolean notVariable=true; //true--> not a variable, ignore it.
		
		while(input.hasNextLine()) {
			
			line=input.nextLine();
			lineSplit=line.split(delimeters);
			
			for(String word:lineSplit) {
				System.out.println(word);
				
				
				if(integer==true||Double==true||Float==true||
						Char==true||bool==true||string==true) {
					
					notVariable = FuncOrVar.varCheck(word);
								//notVariable true means a function, false means variable.
					
					if(notVariable==true) {
						int size = word.length();
						int index= word.indexOf('(');
					
						if(integer==true)integer=false;
						else if(Double==true)Double=false;
						else if(Float==true)Float=false;
						else if(Char==true)Char=false;
						else if(bool==true)bool=false;
						else if(string==true)string=false;
						
						if(index!=size-1) {
							String word1[]=word.split("\\(");
							
							if(word1[1].equals(tokens[0])) integer=true;
							else if(word1[1].equals(tokens[1])) Double=true;
							else if(word1[1].equals(tokens[2])) Float=true;
							else if(word1[1].equals(tokens[3])) Char=true;
							else if(word1[1].equals(tokens[4])) bool=true;
							else if(word1[1].equals(tokens[5])) string=true;
						}
					}
				
												//notVariable-->false means a variable.
					if(notVariable==false) {
							
						int size = word.length();
						int index= word.indexOf(',');
						
						if(index!=-1 && size!=index+1){
							
							String arr[];
							arr=word.split("[,=0123456789]+");
							
										// case example: int func(int "a,int" b)
							if(arr[1].equals(tokens[0])|| arr[1].equals(tokens[1])|| arr[1].equals(tokens[2])||
									arr[1].equals(tokens[3])|| arr[1].equals(tokens[4])|| arr[1].equals(tokens[5]))
							{
								
								if(integer==true ){ intL.add(arr[0]); previousName=arr[0]; }
								else if(Double==true ){ DoubleL.add(arr[0]); previousName=arr[0]; }
								else if(Float==true ){ FloatL.add(arr[0]); previousName=arr[0]; }
								else if(Char==true ) { CharL.add(arr[0]); previousName=arr[0]; }
								else if(bool==true ) { boolL.add(arr[0]); previousName=arr[0]; }
								else if(string==true ) { stringL.add(arr[0]); previousName=arr[0]; }
								
								integer=false; Double=false; Float=false; Char=false; bool=false; string=false;
								
								if(arr[1].equals(tokens[0])) integer=true;
								else if(arr[1].equals(tokens[1])) Double=true;
								else if(arr[1].equals(tokens[2])) Float=true;
								else if(arr[1].equals(tokens[3])) Char=true;
								else if(arr[1].equals(tokens[4])) bool=true;
								else if(arr[1].equals(tokens[5])) string=true;
							}
									 //element after comma(,) is not a data type [e.g. int a,b,c;]
							else {         
								for(String var:arr) {
									if(!FuncOrVar.varCheck(var)) {
										if(integer==true) { intL.add(var); previousName=var; } 
										else if(Double==true){ DoubleL.add(var); previousName=var; }
										else if(Float==true){ FloatL.add(var); previousName=var; }
										else if(Char==true){ CharL.add(var); previousName=var; }
										else if(bool==true) { boolL.add(var); previousName=var; }
										else if(string==true){ stringL.add(var); previousName=var; }
									}
									else break;
								}
								integer=false; Double=false; Float=false; Char=false; bool=false; string=false;
							}
							
							
						}
												//no variable with comma or variable ending in comma
												//e.g. int "a," or int "a" , b;
						else if(index==-1 || (index!=-1 && size==index+1)){
						
							String arr[];
							arr=word.split("[,=0123456789]+");
							
							if(integer==true ) intL.add(arr[0]);
							else if(Double==true ) DoubleL.add(arr[0]);
							else if(Float==true ) FloatL.add(arr[0]);
							else if(Char==true ) CharL.add(arr[0]);
							else if(bool==true ) boolL.add(arr[0]);
							else if(string==true ) stringL.add(arr[0]);
							
							integer=false; Double=false; Float=false; Char=false; bool=false; string=false;		
							
						}
					}
					
				}
				
				if(word.indexOf('(')!=-1 && previousName!=null) {
					
					System.out.println(word+"\t"+previousName);
					
					intL=FuncOrVar.ifFuncDelete(previousName, intL);
					DoubleL=FuncOrVar.ifFuncDelete(previousName, DoubleL);
					FloatL=FuncOrVar.ifFuncDelete(previousName, FloatL);
					CharL=FuncOrVar.ifFuncDelete(previousName, CharL);
					boolL=FuncOrVar.ifFuncDelete(previousName, boolL);
					stringL=FuncOrVar.ifFuncDelete(previousName, stringL);
					previousName=null;
				}
				
										//Data type(here tokens) matches.
				if(word.equals(tokens[0])) integer=true;
				else if(word.equals(tokens[1])) Double=true;
				else if(word.equals(tokens[2])) Float=true;
				else if(word.equals(tokens[3])) Char=true;
				else if(word.equals(tokens[4])) bool=true;
				else if(word.equals(tokens[5])) string=true;
				
			}
		}
		
		Print.showDetails(tokens,intL,DoubleL,FloatL,CharL,boolL,stringL);
	}
}
