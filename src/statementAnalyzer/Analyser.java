package statementAnalyzer;

import java.util.ArrayList;
import java.util.regex.Pattern;

import pattern.PatternMatcher;
import projectOfDataClass.Statement;

public class Analyser {
	
	private PatternMatcher patternMatcher;
	
	public Analyser() {
		patternMatcher = new PatternMatcher();
	}
	
	public void analyzeStatement(ArrayList<Statement> method) {
		
		for(int i= 0; i < method.size(); i++) {
			//System.out.println(method.get(i).getLineNumber() +"  "+ method.get(i).getStatement());
		
			if(isForloopStarting(method.get(i).getStatement())) {
				System.out.println(method.get(i).getLineNumber() +"  "+ method.get(i).getStatement());
			}
		
		
		
		
		
		}
		
		
	}

	private boolean isForloopStarting(String statement) {
		return patternMatcher.isMatch("^(\\s)*for", statement);
	}
	
	
	
	
	
	
	

}
