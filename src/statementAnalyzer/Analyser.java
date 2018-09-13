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
		Stack<Node> parentOfIf = new Stack<>();

		Stack<Node> parentOfEndParenthasis = new Stack<>();

		Node currentNode = new Node(nodeNumber);
		nodeNumber++;
		graph.add(currentNode);
		nodeStack.push(currentNode);

		while (i < method.size()) {
			// System.out.println(method.get(i).getLineNumber() + " " +
			// method.get(i).getStatement());

			if (isElseStatement(method.get(i).getStatement())) {
				// System.out.println("paichi");

				parentOfIf.add(currentNode.getParent());

				Node elseNode = new Node(nodeNumber);
				nodeNumber++;

				graph.add(elseNode);

				Node parentOfifNode = parentOfIf.pop();
				parentOfifNode.addChild(elseNode.getNodeNumber());
				elseNode.setParentNode(parentOfifNode);
				elseNode.addStatement(method.get(i));

				if (paranthesisFound(method.get(i).getStatement())) {
					// System.out.println("Paichi");
					elseNode.isElse = true;

					parentOfEndParenthasis.push(elseNode);
					nodeStack.push(elseNode);
					i++;

				} else if (paranthesisFound(method.get(i + 1).getStatement())) {
					//System.out.println("paichi");
					i++;
					elseNode.addStatement(method.get(i));
					elseNode.isElse = true;

					parentOfEndParenthasis.push(elseNode);
					nodeStack.push(elseNode);
					i++;

				} else {

					i++;
					elseNode.addStatement(method.get(i));
					i++;

					Node nextNode = new Node(nodeNumber);
					nodeNumber++;

					ArrayList<Integer> childList = parentOfifNode.getChildList();

					for (int index1 = 0; index1 < childList.size(); index1++) {
						for (int index2 = 0; index2 < graph.size(); index2++) {
							if (graph.get(index2).getNodeNumber() == childList.get(index1)) {
								graph.get(index2).addChild(nextNode.getNodeNumber());
							}
						}
					}

					graph.add(nextNode);
					nextNode.setParentNode(elseNode);

					nodeStack.add(nextNode);

				}

			}

			if (isIfStateent(method.get(i).getStatement())) {
				// System.out.println("paichi if statement");

				Node previousNode = nodeStack.pop();

				currentNode = new Node(nodeNumber);
				currentNode.isIf = true;
				nodeNumber++;

				currentNode.setParentNode(previousNode);
				previousNode.addChild(currentNode.getNodeNumber());

				graph.add(currentNode);

				if (paranthesisFound(method.get(i).getStatement())) {

				} else if (paranthesisFound(method.get(i + 1).getStatement())) {

				} else {
					// System.out.println("paichi");
					currentNode.addStatement(method.get(i));
					i++;

					// System.out.println(method.get(i+1).getStatement());

					if (isElseStatement(method.get(i + 1).getStatement())
							|| isElseIfStatement(method.get(i + 1).getStatement())) {

						currentNode.addStatement(method.get(i));

						// System.out.println("paichi");
						// System.out.println(method.get(i).getStatement());
						// currentNode.addStatement(method.get(i));

					} else {

						currentNode.addStatement(method.get(i));
						i++;

						Node newNode = new Node(nodeNumber);
						nodeNumber++;
						currentNode.addChild(newNode.getNodeNumber());
						currentNode.getParent().addChild(newNode.getNodeNumber());
						graph.add(newNode);
						nodeStack.push(newNode);

					}

				}

			}

			else if (isForloopStarting(method.get(i).getStatement())) {
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
					// System.out.println("paichi + 2s");
					// System.out.println(method.get(i).getLineNumber() + " " +
					// method.get(i).getStatement());
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

			else if (endParanthesisFound(method.get(i).getStatement()) && !parentOfEndParenthasis.isEmpty()) {

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
				if (startOfParanthesis.isElse) {
					startOfParanthesis.addStatement(method.get(i));
					Node nextNode = new Node(nodeNumber);
					nodeNumber++;

					Node parentOfifNode = startOfParanthesis.getParent();
					
					ArrayList<Integer> childList = parentOfifNode.getChildList();

					for (int index1 = 0; index1 < childList.size(); index1++) {
						for (int index2 = 0; index2 < graph.size(); index2++) {
							if (graph.get(index2).getNodeNumber() == childList.get(index1)) {
								graph.get(index2).addChild(nextNode.getNodeNumber());
							}
						}
					}

					graph.add(nextNode);
					nextNode.setParentNode(startOfParanthesis);

					nodeStack.add(nextNode);
					
					
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

		System.out.print("\t  node\t\t");
		for (int index = 0; index < graph.size(); index++)
			System.out.print(index + 1 + "\t");

		System.out.println();

		for (int index1 = 0; index1 < graph.size(); index1++) {
			Node node = graph.get(index1);

			System.out.print("Node Number:  " + node.getNodeNumber() + "\t\t");
			for (int index2 = 0; index2 < graph.size(); index2++) {
				if (node.isChild(index2 + 1)) {
					System.out.print("1\t");
				} else {
					System.out.print("0\t");
				}
			}

			System.out.println();
		}

	}

	private boolean isElseIfStatement(String statement) {
		return patternMatcher.isMatch("^(\\s)*else if", statement);
	}

	private boolean isElseStatement(String statement) {
		return patternMatcher.isMatch("^(\\s)*else", statement);
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
