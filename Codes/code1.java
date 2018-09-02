class ForLoopExample {
    public static void main(String args[]){
         
    	
    	
    	
    	
    	
    	
    	
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
}