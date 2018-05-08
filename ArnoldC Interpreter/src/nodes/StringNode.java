package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class StringNode extends Node {

	String text;

	public StringNode(String text) {
		super("StringNode");
		this.text = text;
	}

	public StringNode() {
		super("StringNode");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
