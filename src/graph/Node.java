package graph;

import java.util.ArrayList;

public class Node {
	
	private ArrayList<Integer> childList = new ArrayList<>();
	private int nodeNumber;
	
	public Node(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
	
	
	public void addChild(int childNumber) {
		childList.add(childNumber);
	}
	
	
	
	

}
