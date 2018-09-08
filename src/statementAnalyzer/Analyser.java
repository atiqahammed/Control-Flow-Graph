package statementAnalyzer;

import java.util.ArrayList;

import projectOfDataClass.Statement;

public class Analyser {
	
	public void analyzeStatement(ArrayList<Statement> method) {
		
		for(int i= 0; i < method.size(); i++) {
			System.out.println(method.get(i).getLineNumber() +"  "+ method.get(i).getStatement());
		}
		
		
	}
	
	
	
	
	
	
	

}
