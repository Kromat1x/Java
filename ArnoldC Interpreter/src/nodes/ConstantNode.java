package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class ConstantNode extends Node {

	int value;

	public ConstantNode(int value) {
		super("ConstantNode");
		this.value = value;
	}

	public ConstantNode() {
		super("ConstantNode");
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
