package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class AssignmentNode extends Node {

	ArrayList<Node> list;

	public AssignmentNode(String valAssign) { // I need to make more
												// constructors depending on the
												// type
		super("AssignmentNode"); // of operation that is done to the assigned
									// value
		list = new ArrayList<Node>();
		LvalNode n = new LvalNode(valAssign);
		list.add(n);
		// Adding the needed node OrNode, SumNode, DifferenceNode etc
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

}
