package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class ModuloNode extends Node {

	ArrayList<Node> list;

	public ModuloNode() {
		super("ModuloNode");
		list = new ArrayList<Node>();
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
