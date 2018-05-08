import java.util.ArrayList;

public class Node {
	char value;
	ArrayList<Character> neighbors;
	
	public Node(char value) {
		this.value = value;
		neighbors = new ArrayList<Character>();
	}
	
	public void addNeighbor(char v) {
		neighbors.add(v);
	}

	public char getValue() {
		return value;
	}

	public ArrayList<Character> getNeighbors() {
		return neighbors;
	}
	
	public void printNode() {
		System.out.println(value + " : " + neighbors);
	}
	
}
