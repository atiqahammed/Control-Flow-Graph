package class_analyzer;

import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

public class MethodFinder {

	public void findMethods(ArrayList<String> allCodeLines) {

		for (int i = 0; i < allCodeLines.size(); i++) {
			String nextLine = null;
			String currentLine = allCodeLines.get(i);

			if (i < allCodeLines.size() - 1) {
				nextLine = allCodeLines.get(i + 1);
			}
			
			if(isMethodStartingLine(currentLine, nextLine)) {
				
			}

		}

	}

	public boolean isMethodStartingLine(String currentLine, String nextLine) {
		String allWards[] = currentLine.split(" ");
		boolean isClassDeclaration = checkClassDecleration(allWards);
		
		//if(isClassDeclaration)
		//	System.out.println("class found");
		
		
		
		return false;
	}

	private boolean checkClassDecleration(String[] allWards) {
		for(int i = 0; i < allWards.length; i++) {
			if(allWards[i].equals("class"))
				return true;
		}
		return false;
	}

}
