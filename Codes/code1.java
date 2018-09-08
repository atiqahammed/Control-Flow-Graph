class ForLoopExample {
    public static void main(String args[]){
    	
    	List<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile(p).matcher(s);
    	
        
        
        
        
        
    	        for (;;)
    	        {
            System.out.println("---> Your choice: ");
            choice = input.nextInt();
            if (choice==1)
                playGame();
            if (choice==2)
                loadGame();
            if (choice==3)
                options();
            if (choice==4)
                credits();
            if (choice==5)
                System.out.println("End of Game\n Thank you for playing with us!");
                break;
            else
                System.out.println("Not a valid choice!\n Please try again...\n");=[;'mm
        }
    	
    	
    	
    	
    	
    	for(int i=10; i>1; i--){
              System.out.println("The value of i is: "+i);
        }
    }
    
    public static List<String> get_matches(String s, String p) 
    {
        // returns all matches of p in s for first group in regular expression 
        List<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile(p).matcher(s);
        while(m.find()) {
            matches.add(m.group(1));
        }
        return matches;
    }
    
    
    public static int minFunction(int n1, int n2) {
    	   int min;
    	   if (n1 > n2)
    	      min = n2;
    	   else
    	      min = n1;

    	   return min; 
    	}
    
    public static void main(String[] args) {
        int a = 11;
        int b = 6;
        int c = minFunction(a, b);
        System.out.println("Minimum Value = " + c);
     }
    
    
    public static void main(String[] args) {
        methodRankPoints(255.7);
     }

    public static void methodRankPoints(double points) {
        if (points >= 202.5) {
           System.out.println("Rank:A1");
        }else if (points >= 122.4) {
           System.out.println("Rank:A2");
        }else {
           System.out.println("Rank:A3");
        }
     }
}