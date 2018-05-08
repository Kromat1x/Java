package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class AndNode extends Node {

	ArrayList<Node> list;

	public AndNode() {

		super("AndNode");
		list = new ArrayList<Node>();

	}

	public void accept(Visitor v) {
		v.visit(this);
	}

}
