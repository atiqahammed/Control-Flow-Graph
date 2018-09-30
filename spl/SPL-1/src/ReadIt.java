package forspl;

import java.util.Scanner;

public class ReadIt extends Thread {

    static Scanner in = new Scanner(System.in);
    
    static double PI = 3.1416;

	double e = 2.418182;
    int MAX = (int) 1e9;
    

    public static int Add(){
        
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        
        int res = num1+num2;
        
        return res;
    }

    public static double CircleArea(double rad){
        
        double area;
        area = PI*rad*rad;

        return area;
    }

    public static int Subtraction(int numA,int numB){
        
        int subResult= (numA - numB);
        return subResult;
    }
    

   // public static void main(String[] args){  
    public void run(){

        int result, numA,numB ;
        String addMsg ="sum is: ";
        String subMsg = "Subtraction result : ";
        int number=10;
        double AreaOfCircle, radius;  char ch='T';
        String str = "This is a text file" , str2 = "sample string" ;

        float count= (float) 1.00056, divider=(float) 2.0;
        boolean flag = false;

        result = Add();
        System.out.println( addMsg + result);

        radius = in.nextDouble();
        CircleArea(radius);
        System.out.println( "Circle Area : " + result);

        numA = in.nextInt();
        numB = in.nextInt();
        Subtraction(numA,numB);

        for(int i=0; i<10; i++)
            System.out.println("i = " + i);

    }

}
