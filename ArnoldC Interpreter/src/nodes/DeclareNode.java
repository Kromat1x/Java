package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class DeclareNode extends Node {

	LvalNode n;
	ConstantNode c;

	public DeclareNode(LvalNode n, ConstantNode c) {
		super("DeclareNode");
		this.n = n;
		this.c = c;
	}

	public DeclareNode() {
		super("DeclareNode");
	}

	public LvalNode getLval() {
		return n;
	}

	public void setLval(LvalNode n) {
		this.n = n;
	}

	public ConstantNode getConst() {
		return c;
	}

	public void setConst(ConstantNode c) {
		this.c = c;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
