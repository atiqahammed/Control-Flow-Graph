package ForJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StaticAnalysisRefined {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input= new Scanner(new File("D:\\Users\\My-PC\\eclipse-workspace\\VaribleTypes\\src\\Variables.java"));
		
		String line, lineSplit[];
		while(input.hasNextLine()) {
			
			line=input.nextLine();
			/*lineSplit=line.split(" ");
			
			for(String word:lineSplit) {
				System.out.println(word);
			}*/
			int number = NeededFunctions.findToken(line);
			if(number>0) {
				NeededFunctions.parseAndStore(line);
			}
		}
		NeededFunctions.Print();
	}

}
