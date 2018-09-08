package statementAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import pattern.PatternMatcher;
import projectOfDataClass.Statement;

public class Analyser {

	private PatternMatcher patternMatcher;
	Map<Integer, Boolean> isUsed;

	public Analyser() {
		patternMatcher = new PatternMatcher();
		isUsed = new HashMap<>();
	}

	public void analyzeStatement(ArrayList<Statement> method) {

		int i = 0;

		while (i < method.size()) {
			// System.out.println(method.get(i).getLineNumber() +" "+
			// method.get(i).getStatement());

			if (isForloopStarting(method.get(i).getStatement())) {

				if (paranthesisFound(method.get(i).getStatement())) {
					System.out.println("paichi");
					System.out.println(method.get(i).getLineNumber() + "  " + method.get(i).getStatement());
				} else if (paranthesisFound(method.get(i + 1).getStatement())) {
					System.out.println("paichi + 2s");
					System.out.println(method.get(i).getLineNumber() + "  " + method.get(i).getStatement());
				}

			}

			// break;
			i++;

		}

	}

	private boolean paranthesisFound(String statement) {
		return patternMatcher.isMatch("\\{(\\s)*$", statement);
	}

	private boolean isForloopStarting(String statement) {
		return patternMatcher.isMatch("^(\\s)*for", statement);
	}

}
