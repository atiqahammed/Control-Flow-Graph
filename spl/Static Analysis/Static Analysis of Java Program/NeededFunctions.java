package ForJava;
import java.util.*;

public class NeededFunctions {
	public static  String tokens[] = {"int","double","float","char","boolean","String"};
	static HashMap<String, ArrayList<String>> hash = new HashMap<>();
	static ArrayList<String> list=new ArrayList<String>();
	
	public static int findToken(String line) {
		int count=0;
		for(int i=0; i<tokens.length; i++) {
			if(line.contains(tokens[i])) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean findSemicolon(String word, String word1) {
		if(word1.contains(";"))return true;
		else if(word1.contains("(")) {
			if(word1.contains("=")) {
				String arr[]=word1.split("=");
				list.clear();
				list.add(arr[0]);
				hash.put(word,list);
			}
			else return true;
		}
		return false;
	}
	public static void Store(String word,String word1) {
		String arr[]=word1.split(",");
		//boolean 
		for(String var :arr) {
			var=var.trim();
			list.clear();
			list.add(var);
			hash.put(word,list);
		}
	}
	
	public static void parseAndStore(String line) {
		String lineSplit[]=line.split(" ");
		
		for(int i=0; i<lineSplit.length; i++) {
			String word=lineSplit[i];
			//for(int j=0; j<tokens.length; j++) {
			
				word=word.trim();
				int num = findToken(word);
				if(num>0) {
					String exit="";
					
					while(exit!=";") {
						i++;
						if(lineSplit[i]!=";") {
							
							String word1=lineSplit[i];
							boolean semicolonOrBracket=findSemicolon(word,word1);
							if(semicolonOrBracket)exit=";";
							else {
								word1=word1.trim();
								//String token =Store(word,word1);
								Store(word,word1);
							}
						}
						else {
							break;
						}
					}
					
				}
			//}
		}
		
	}
	
	
	public static void Print() {
		System.out.print("\n\n=====VARIABLE ANALYSIS=====\n\n");
		System.out.print("[TYPE]\t\t[VARIABLES]\n");
		for(Map.Entry<String, ArrayList<String>> entry:hash.entrySet()){   
			String key=entry.getKey();  
			System.out.println(key+"\t\t"); 
			System.out.println(entry.getValue());
		}
	}
}
