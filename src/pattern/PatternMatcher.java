package pattern;

import java.util.regex.Pattern;

public class PatternMatcher {
	
	private Pattern MY_PATTERN;
	
	public boolean isMatch(String pattern,  String testString) {
		MY_PATTERN = Pattern.compile(pattern);
		java.util.regex.Matcher matcher = MY_PATTERN.matcher(testString);
		if (matcher.find())
			return true;
		return false;
	}

}
