package graph;

import java.util.ArrayList;

import projectOfDataClass.Statement;

public class Node {
	
	private ArrayList<Integer> childList = new ArrayList<>();
	private int nodeNumber;
	private ArrayList<Statement> statements = new ArrayList<>();
	private Node parentNode;
	public boolean isLoop = false;
	public boolean isIf = false;
	public boolean parentOfIf = false;
	
	public void setParentNode(Node parent) {
		parentNode = parent;
	}
	
	public Node getParentNode() {
		return parentNode;
	}

	
	public Node(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	
	public void addChild(int childNumber) {
		childList.add(childNumber);
	}
	
	public void addStatement(Statement statement) {
		statements.add(statement);
	}
	
	public void printStatement() {
		System.out.println(statements.size());
		for(int i = 0; i < statements.size(); i++)
			System.out.println(statements.get(i).getStatement());
	}
	
	public void printChild() {
		System.out.print("Node number:  " + nodeNumber + "   >>>   ");
		for(int i = 0; i < childList.size(); i++) {
			System.out.print(childList.get(i)+ "   ");
		}
		System.out.println();
	}
	
	public int getNodeNumber() {
		return nodeNumber;
	}

	public boolean isChild(int childNumber) {
		
		for(int i = 0;i < childList.size(); i++)
			if(childList.get(i) == childNumber) 
				return true;
		
		
		return false;
	}
	
	public Node getParent() {
		return parentNode;
	}
	
	public ArrayList<Integer> getChildList() {
		return childList;

	}
	
	public int getTotalChild() {
		return childList.size();
	}
	
	
	
	

}
