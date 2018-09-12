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
		Stack<Node> parentOfEndParenthasis = new Stack<>();

		Node currentNode = new Node(nodeNumber);
		nodeNumber++;
		graph.add(currentNode);
		nodeStack.push(currentNode);

		while (i < method.size()) {
			// System.out.println(method.get(i).getLineNumber() +" "+
			// method.get(i).getStatement());
			
			
			if(isIfStateent(method.get(i).getStatement())) {
				//System.out.println("paichi if statement");
				
				Node previousNode = nodeStack.pop();
				
				
				currentNode = new Node(nodeNumber);
				nodeNumber++;
				
				
				currentNode.setParentNode(previousNode);
				previousNode.addChild(currentNode.getNodeNumber()); 
				
				graph.add(currentNode);
				
				
				
			}
			

			if (isForloopStarting(method.get(i).getStatement())) {
				Node previousNode = nodeStack.pop();
				previousNode.addChild(nodeNumber);
				currentNode = new Node(nodeNumber);
				nodeNumber++;
				
				currentNode.setParentNode(previousNode);
				currentNode.addStatement(method.get(i));

				graph.add(currentNode);
				
				
				if (paranthesisFound(method.get(i).getStatement())) {

					Node newNode = new Node(nodeNumber);
					nodeNumber++;
					graph.add(newNode);
					nodeStack.push(newNode);

					newNode.setParentNode(currentNode);
					currentNode.addChild(newNode.getNodeNumber());
					currentNode.isLoop = true;
					parentOfEndParenthasis.push(currentNode);

				} else if (paranthesisFound(method.get(i + 1).getStatement())) {
					//System.out.println("paichi + 2s");
					//System.out.println(method.get(i).getLineNumber() + "  " + method.get(i).getStatement());
					i++;
					
					Node newNode = new Node(nodeNumber);
					nodeNumber++;
					graph.add(newNode);
					nodeStack.push(newNode);

					newNode.setParentNode(currentNode);
					currentNode.addChild(newNode.getNodeNumber());
					currentNode.isLoop = true;
					parentOfEndParenthasis.push(currentNode);
				
				} else {

					currentNode.addChild(nodeNumber);
					Node nestedNode = new Node(nodeNumber);
					graph.add(nestedNode);
					nodeNumber++;
					nestedNode.addStatement(method.get(i + 1));
					i += 2;

					nestedNode.setParentNode(currentNode);
					nestedNode.addChild(currentNode.getNodeNumber());

					Node newNode = new Node(nodeNumber);
					nodeNumber++;
					graph.add(newNode);

					newNode.setParentNode(currentNode);
					currentNode.addChild(newNode.getNodeNumber());
					nestedNode.addChild(newNode.getNodeNumber());
					nodeStack.push(newNode);
					continue;
				}

			}

			else if (endParanthesisFound(method.get(i).getStatement()) && !parentOfEndParenthasis.isEmpty() ) {

				// System.out.println("end of bracket");

				Node startOfParanthesis = parentOfEndParenthasis.pop();

				if (startOfParanthesis.isLoop) {

					Node previousNode = nodeStack.pop();

					Node newNode = new Node(nodeNumber);
					nodeNumber++;
					graph.add(newNode);
					previousNode.addChild(newNode.getNodeNumber());
					startOfParanthesis.addChild(newNode.getNodeNumber());
					newNode.setParentNode(previousNode);
					nodeStack.push(newNode);
					previousNode.addChild(startOfParanthesis.getNodeNumber());

				}

				// previousNode.addChild(st);

			}

			else {
				if (nodeStack.isEmpty())
					break;

				Node tempNode = nodeStack.pop();
				tempNode.addStatement(method.get(i));
				nodeStack.push(tempNode);
			}

			i++;

		}

		// graph.get(0).printStatement();
		for (int index = 0; index < graph.size(); index++) {
			graph.get(index).printChild();
			graph.get(index).printStatement();
		}
		
		
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		System.out.print("");

		
		for(int index1 = 0; index1 < graph.size(); index1++) {
			Node node = graph.get(index1);
			
			System.out.print("Node Number:  " + node.getNodeNumber() +"\t\t");
			for(int index2 = 0; index2 < graph.size(); index2++) {
				if(node.isChild(index2+1)) {
					System.out.print("1\t");
				} else {
					System.out.print("0\t");
				}
				//System.out.print(index2+1+"\t");
			}
			
			System.out.println();
		}
		
		
		
	}

	private boolean isIfStateent(String statement) {
		
		return patternMatcher.isMatch("^(\\s)*if", statement);
	}

	private boolean endParanthesisFound(String statement) {
		return patternMatcher.isMatch("\\}(\\s)*$", statement);
	}

	private boolean paranthesisFound(String statement) {
		return patternMatcher.isMatch("\\{(\\s)*$", statement);
	}

	private boolean isForloopStarting(String statement) {
		return patternMatcher.isMatch("^(\\s)*for", statement);
	}

}
