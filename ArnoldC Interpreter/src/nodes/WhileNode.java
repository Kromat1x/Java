package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class WhileNode extends Node {

	ArrayList<Node> list;

	public WhileNode(String condName) {
		super("WhileNode");
		list = new ArrayList<Node>();
		ConditionNode n = new ConditionNode(condName);
		list.add(n);
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
