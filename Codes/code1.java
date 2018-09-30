public class RunThreads  {
    
    public static void main(String args[]){  
    	
        ReadIt t1=new ReadIt();  
        Main t2=new Main();  
  
        t1.start();  
        t2.start();  
    }  
    
}