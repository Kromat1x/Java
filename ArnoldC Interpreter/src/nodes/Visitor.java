package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public interface Visitor {
	public void visit(MainNode n); // All the nodes that can be visited

	public void visit(DeclareNode n);

	public void visit(StringNode n);

	public void visit(ConstantNode n);

	public void visit(LvalNode n);

	public void visit(VariableNode n);

	public void visit(PrintNode n);

	public void visit(RvalNode n);

	public void visit(AssignmentNode n);

	public void visit(MultiplicationNode n);

	public void visit(SumNode n);

	public void visit(OrNode n);

	public void visit(ConditionNode n);

	public void visit(IfNode n);

	public void visit(WhileNode n);

	public void visit(BodyNode n);

	public void visit(DifferenceNode n);

	public void visit(ModuloNode n);

	public void visit(GreaterThanNode n);

	public void visit(EqualToNode n);

	public void visit(DivisionNode n);

	public void visit(AndNode n);
}
