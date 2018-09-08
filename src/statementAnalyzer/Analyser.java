package statementAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

import graph.Node;
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
		
		int nodeNumber = 1;
		
		ArrayList<Node> graph = new ArrayList<>();
		Stack<Node> nodeStack = new Stack<>();
		
		
		
		Node currentNode = new Node(nodeNumber);
		nodeNumber++;
		graph.add(currentNode);
		nodeStack.push(currentNode);
		
		
		while (i < method.size()) {
			 System.out.println(method.get(i).getLineNumber() +" "+ method.get(i).getStatement());

			if (isForloopStarting(method.get(i).getStatement())) {

				Node previousNode = nodeStack.pop();
				previousNode.addChild(nodeNumber);
				currentNode = new Node(nodeNumber);
				nodeNumber++;
				
				currentNode.setParentNode(previousNode);
				currentNode.addStatement(method.get(i));
				
				graph.add(currentNode);
				
				
				
				
				if (paranthesisFound(method.get(i).getStatement())) {
					System.out.println("paichi");
					System.out.println(method.get(i).getLineNumber() + "  " + method.get(i).getStatement());
				} else if (paranthesisFound(method.get(i + 1).getStatement())) {
					System.out.println("paichi + 2s");
					System.out.println(method.get(i).getLineNumber() + "  " + method.get(i).getStatement());
				} else {
					
					currentNode.addChild(nodeNumber);
					Node nestedNode = new Node(nodeNumber);
					graph.add(nestedNode);
					nodeNumber++;
					nestedNode.addStatement(method.get(i+1));
					i+=2;
					
					nestedNode.setParentNode(currentNode);
					nestedNode.addChild(currentNode.getNodeNumber());
					
					Node newNode = new Node(nodeNumber);
					nodeNumber++;
					graph.add(newNode);
					
					
					newNode.setParentNode(currentNode);
					currentNode.addChild(newNode.getNodeNumber()); 
					nestedNode.addChild(newNode.getNodeNumber());
					
					
					
					//// kaj baki ase stack ee add kora
					nodeStack.push(newNode);
					continue;
					
					
				}

			} else {
				if(nodeStack.isEmpty()) 
					break;
				
				Node tempNode = nodeStack.pop();
				tempNode.addStatement(method.get(i));
				nodeStack.push(tempNode);
			}

			
			i++;

		}
		
		
		//graph.get(0).printStatement();
		for(int index = 0; index<graph.size(); index++) {
			graph.get(index).printChild();
			graph.get(index).printStatement();
		}

	}

	private boolean paranthesisFound(String statement) {
		return patternMatcher.isMatch("\\{(\\s)*$", statement);
	}

	private boolean isForloopStarting(String statement) {
		return patternMatcher.isMatch("^(\\s)*for", statement);
	}

}
