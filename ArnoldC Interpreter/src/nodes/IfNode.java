package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class IfNode extends Node {

	ArrayList<Node> list;
	int type;

	// type 1 only ifbody
	// type 2 ifbody and elsebody
	public IfNode(String condName) {
		super("IfNode");
		list = new ArrayList<Node>();
		ConditionNode n = new ConditionNode(condName);
		list.add(n);
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
