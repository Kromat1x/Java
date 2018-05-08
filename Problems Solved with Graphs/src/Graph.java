import java.util.ArrayList;

public class Graph {

	ArrayList<Node> nodes;

	public Graph() {
		nodes = new ArrayList<Node>();
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public Boolean containsChar(char c) {
		Boolean found = false;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getValue() == c) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void addChar(char parent, char kid) {
		Boolean parentExists = false;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getValue() == parent) {
				parentExists = true;
				if(!nodes.get(i).neighbors.contains(kid))
					nodes.get(i).addNeighbor(kid);
				break;
			}
		}

		if (!parentExists) {
			Node newNode = new Node(parent);
			newNode.addNeighbor(kid);
			nodes.add(newNode);
		}
	}

	public void printGraph() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).printNode();
		}
	}

	public ArrayList<Node> noIncomingEdges() {
		ArrayList<Node> list = new ArrayList<Node>();
		int incoming = 0;
		for (int i = 0; i < nodes.size(); i++) {
			incoming = 0;
			Node aux = nodes.get(i);
			for(int j = 0; j < nodes.size(); j++) {
				if(nodes.get(j).getNeighbors().contains(aux.getValue())) {
					incoming = 1;
					break;
				}
			}
			if(incoming == 0) {
				list.add(aux);
			}
		}
		return list;
	}
}
