package AOP;

public class AOPexampl {
	
	int a1=10;

	static int b1;
	
	public String s="AspectJ";   char ch='t';
	public int num=500, axis=-65;

	public static void main(String[] args) {
		
		b1= 123; 
		
		AOPexampl aop = new AOPexampl();
		aop.method1(404);
		aop.method2(404,"My roll");
		aop.method3("I am Tanvir");

	}
	public void method1(int var) {
		System.out.println(">> Method one (with integer variable).");
	}
	public void method2(int var, String str) {
		System.out.println(">> Method two (with integer and string variable).");
	}
	public void method3(String str) {
		System.out.println(">> Method three (with string variable).");
	}

}
