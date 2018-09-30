package AOP;

public class AOPexampl {

	public static void main(String[] args) {
		AOPexampl aop = new AOPexampl();
		aop.method1(404);
		aop.method1(404,"My roll");
		aop.method2("I am Tanvir");

	}
	public void method1(int var) {
		System.out.println(">> Method one (with integer variable).");
	}
	public void method1(int var, String str) {
		System.out.println(">> Method one (with integer and string variable).");
	}
	public void method2(String str) {
		System.out.println(">> Method two (with string variable).");
	}

}
