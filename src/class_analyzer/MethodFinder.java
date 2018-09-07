package class_analyzer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

public class MethodFinder {

	public void findMethods(ArrayList<String> allCodeLines) {

		for (int i = 0; i < allCodeLines.size(); i++) {
			String nextLine = null;
			String currentLine = allCodeLines.get(i);

			if (i < allCodeLines.size() - 1) {
				nextLine = allCodeLines.get(i + 1);
			}

			if (isMethodStartingLine(currentLine, nextLine)) {
				System.out.println(currentLine);
			}

		}

	}

	public boolean isMethodStartingLine(String currentLine, String nextLine) {
		String allWards[] = currentLine.split(" ");
		boolean isClassDeclaration = checkClassDecleration(allWards);
		boolean numverOfWordCheck = checkNumberOfWords(allWards);
		boolean bracketFound = checkBreacket(currentLine);
		boolean endWithSemicolon  = checkSemicolon(currentLine);
		boolean keyWordFound = checkKeyWords(currentLine); 
		boolean paranthesisFound = false;
		
		if(nextLine == null) {
			if(currentLine.endsWith("{")) paranthesisFound = true;
		} else {
			if(currentLine.endsWith("{")) paranthesisFound = true;
			Pattern MY_PATTERN = Pattern.compile("\\{");
			java.util.regex.Matcher matcher = MY_PATTERN.matcher(nextLine);
			if(matcher.find()) paranthesisFound = true;
			
			//if(!paranthesisFound && nextLine.startsWith("{")) paranthesisFound = true; 
		}
		
		if(!isClassDeclaration && !keyWordFound && numverOfWordCheck && bracketFound && !endWithSemicolon && paranthesisFound) return true;

		return false;
	}

	private boolean checkKeyWords(String currentLine) {
		Pattern MY_PATTERN = Pattern.compile("for");
		java.util.regex.Matcher matcher = MY_PATTERN.matcher(currentLine);
		if(matcher.find()) return true;
		MY_PATTERN = Pattern.compile("while");
		matcher = MY_PATTERN.matcher(currentLine);
		if(matcher.find()) return true;
		return false;
	}

	private boolean checkSemicolon(String currentLine) {
		if(currentLine.endsWith(";")) return true;
		return false;
	}

	private boolean checkBreacket(String currentLine) {
		boolean startingBracker = false, endingBracket = false;
		for (int i = 0; i < currentLine.length(); i++) {
			if (currentLine.charAt(i) == '(')
				startingBracker = true;
			if (currentLine.charAt(i) == ')')
				endingBracket = true;
		}

		if (startingBracker && endingBracket)
			return true;
		return false;
	}

	private boolean checkNumberOfWords(String[] allWards) {
		if (allWards.length >= 2)
			return true;
		return false;
	}

	private boolean checkClassDecleration(String[] allWards) {
		for (int i = 0; i < allWards.length; i++) {
			if (allWards[i].equals("class"))
				return true;
		}
		return false;
	}

}
