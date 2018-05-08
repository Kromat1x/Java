package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class VariableNode extends Node {

	String varName;

	public VariableNode(String varName) {
		super("VariableNode");
		this.varName = varName;
	}

	public VariableNode() {
		super("VariableNode");
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
