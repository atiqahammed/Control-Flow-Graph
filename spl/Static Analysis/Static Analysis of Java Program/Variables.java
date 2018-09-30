
public class Variables {
	public static void main(String[] args) {
		final int a=101;
		double x=10.4; char ch ; String str = "This is string." ;
		
		boolean bool , notBool,possible= true;
		
		int sum=a+19;
		
		int againSum=Function(15,5), sum2  =  Function2(againSum,5);
		System.out.println(againSum+"\t"+sum2);
	}
	public static int Function(int a , int b) {
		return a+b;
	}
	public static int Function2     (int a , int b) {
		return a+b;
	}
}
