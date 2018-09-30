package forspl;


import static forspl.VarNameCollector.stackInt;
import java.util.Stack;
import java.util.regex.Pattern;

public class VarNameCollector {
    
    static String tokens[] = {"int ","double ","float ","char ","boolean ","String "};
    //static Stack<String> stackInt,stackDouble,stackFloat,stackChar,stackBoolean,stackString =new Stack <>();
    static Stack<String>stackInt = new Stack<String>();
    static Stack<String>  stackDouble = new Stack<>();
    static Stack<String> stackFloat = new Stack<>();
    static Stack<String>    stackChar = new Stack<>();
    static Stack<String>    stackBoolean = new Stack<>();
    static Stack<String>    stackString = new Stack<>();
    public static int count, count1=0;
    
    
    public static String GetAllVariables(int i) {
    	count =0;
    	count1=0;
    	//int len =0;
    	String str = "";
    	Stack<String> stack = null ;
        switch(i){
            case 0:
                stack = stackInt;
                break;
            case 1:
                stack = stackDouble;    
                break;
            case 2:
                stack = stackFloat;
                break;
            case 3:
                stack = stackChar;
                break;
            case 4:
                stack = stackBoolean;
                break;
            case 5:
                stack = stackString;
                break;
        }
        if(!stack.empty()) {
        	//int len =0;
            for(String item: stack){
                str= str+item+"\n";
                if(i==5) {
                	count+=item.length();
                	count1++;
                }
                else count++;
            }
        }
        else {
        	str= "No Variable of this type found in the program.\n";
        }
        
        str= str+"\n";
        return str;
    	
    }
    
    public void PushInRespectiveStack(String str,int i){
        
         
        switch(i){
            case 0:             
                stackInt.push(str);
                ExtractVariable(stackInt,i);
                break;
            case 1:   
                stackDouble.push(str);
                ExtractVariable(stackDouble,i);
                break;
            case 2:
              
                stackFloat.push(str);
                ExtractVariable(stackFloat,i);
                break;
            case 3:
                stackChar.push(str);
                ExtractVariable(stackChar,i);
                break;
            case 4:
                stackBoolean.push(str);
                ExtractVariable(stackBoolean,i);
                break;
            case 5:
                stackString.push(str);
                ExtractVariable(stackString,i);
                break;
        }
        
    }
    
    
    public void ExtractVariable(Stack<String> stack, int i){
        
        int tokenPosition, semicolonPosition, assignOperatorPos, commaPosition;
        int quoteStart, quoteEnd;
        String substr; 
        
        String str = stack.pop();
        
        tokenPosition= str.indexOf(tokens[i]);
        
        if(tokenPosition>0){
            
            substr = str.substring(0,tokenPosition);
            if(substr.contains("(")){
                substr = substr.replace('(', ' ');
                str = str.replace(')', ' ');
            }
            str = str.replaceFirst(substr, "");
            tokenPosition= str.indexOf(tokens[i]);
            
        }
        
        semicolonPosition = str.indexOf(";");
        assignOperatorPos = str.indexOf("=");
        commaPosition = str.indexOf(",");
        
        if(semicolonPosition==-1){
            
            if(str.contains("(")){
                int leftBracketPos, rightBracketPos;
                leftBracketPos = str.indexOf("(");
                rightBracketPos = str.indexOf(")");
                str = str.substring(leftBracketPos+1, rightBracketPos);
            }
            
            String arr[];
            arr = str.split("[ ,]+");
            for (String token : tokens) {
                for (int j = 0; j<arr.length; j++) {
                    if (arr[j].equals(token)) {
                        String variable = arr[j+1]; 
                        j++;
                        variable = variable.trim();
                        stack.push(variable);
                    }
                }
            }
            
        }
        else{
            
            if(assignOperatorPos!=-1 && commaPosition==-1)
                str = str.substring(tokenPosition, assignOperatorPos);
            else str = str.substring(tokenPosition, semicolonPosition);
            
            String toBeNulled="";
            while(str.contains("\"")){
                if(str.contains("\"")) toBeNulled = "\"";
                else if(str.contains("'")) toBeNulled = "'";

                quoteStart = str.indexOf(toBeNulled);
                str = str.replaceFirst(toBeNulled, "");
                quoteEnd = str.indexOf(toBeNulled);

                substr = str.substring(quoteStart,quoteEnd+1);
                str = str.replace(substr, "");
            }
        
            String arr[];
            arr = str.split("[ ,=]+");
            
            for(int j=1; j<arr.length; j++){
                String variable = arr[j];
                variable = variable.trim();
                String pattern = "[ \\d* | ([0-9]\\.[0-9]*) ]+";
                if(Pattern.matches(pattern,variable)) continue;
                stack.push(variable);
            }
            
        }
    }
    
    
    void PrintVariables(){
        
        for(int i=0; i<6; i++){
            
            Stack<String> stack = null ;
            switch(i){
                case 0:
                    stack = stackInt;
                    System.out.println("\n==== INTEGER VARIABLES ====\n");
                    break;
                case 1:
                    stack = stackDouble;
                    System.out.println("\n==== DOUBLE VARIABLES ====\n");
                    break;
                case 2:
                    stack = stackFloat;
                    System.out.println("\n==== FLOAT VARIABLES ====\n");
                    break;
                case 3:
                    stack = stackChar;
                    System.out.println("\n==== CHAR VARIABLES ====\n");
                    break;
                case 4:
                    stack = stackBoolean;
                    System.out.println("\n==== BOOLEAN VARIABLES ====\n");
                    break;
                case 5:
                    stack = stackString;
                    System.out.println("\n==== STRING VARIABLES ====\n");
                    break;
            }
            if(!stack.empty()) {
  
                for(String item: stack){
                    System.out.println(item);
                }
                
            }
            System.out.println();
            
        }
        System.out.println("\n======================================================================\n");
        
    }
    
}