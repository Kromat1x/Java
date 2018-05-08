package nodes;

import java.util.ArrayList;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class InterpreterVisitor implements Visitor {

	static ArrayList<Variable> list = new ArrayList<Variable>();
	ArrayList<String> values = new ArrayList<String>();
	public String output = "";

	/**
	 * Checking a variable for existence by iterating through the list and
	 * verifying if we find the name of our variable in the list of existing
	 * variables
	 * 
	 * @param x
	 *            the name of the variable we search for
	 * @return if we find it or not
	 */
	public static boolean checkVar(String x) {
		for (Variable aux : list) {
			if (aux.name.equals(x))
				return true;
		}
		return false;
	}

	/**
	 * Add a new variable in the variables list of the program
	 * 
	 * @param value
	 *            the value of the new variable
	 * @param name
	 *            the name of the new variable
	 */
	public void addNewVar(int value, String name) {
		Variable v = new Variable(value, name);
		list.add(v);
	}

	/**
	 * Returns the value of a certain variable it finds it by iterating through
	 * the list and if it finds it, returns the value of it
	 * 
	 * @param x
	 *            the name of the variable we search for
	 * @return the value
	 */
	public static int getValue(String x) {
		for (Variable aux : list) {
			if (aux.name.equals(x))
				return aux.value;
		}
		return 0;
	}

	/**
	 * Modify value of a variable in the variables list with another value, this
	 * is used for assignment
	 * 
	 * @param x
	 *            the name of the variable we search for
	 * @param newValue
	 *            the new value of the variable
	 */
	public static void modifyValue(String x, int newValue) {
		for (Variable aux : list) {
			if (aux.name.equals(x))
				aux.value = newValue;
		}
	}

	/**
	 * Does an arithmetic or logical operation
	 * <p>
	 * This method is used exclusively for operations, either mathematical or
	 * logical, it basically gets a node that consists in an operation node and
	 * breaks it down to the last node in the tree, so when it gets to RvalNode
	 * or ConstantNode it will stop from using the function recursively. For an
	 * operation, it returns the operation done between two recursively calls
	 * for the same doOperation method, so the method will go down on the tree
	 * and see what are the values of those operators so it can make the sum,
	 * difference or what ever we need
	 * 
	 * @param n
	 *            the starting node
	 * @return the result of the operation
	 */
	public static int doOperation(Node n) {
		if (n instanceof RvalNode) {

			return getValue(((RvalNode) n).varName);

		} else if (n instanceof ConstantNode) {

			return ((ConstantNode) n).value;

		} else if (n instanceof OrNode) {

			if (doOperation(((OrNode) n).list.get(0)) != 0 || doOperation(((OrNode) n).list.get(1)) != 0) {

				return 1;

			} else {

				return 0;
			}

		} else if (n instanceof SumNode) {

			return (doOperation(((SumNode) n).list.get(0)) + doOperation(((SumNode) n).list.get(1)));

		} else if (n instanceof DifferenceNode) {

			return (doOperation(((DifferenceNode) n).list.get(1)) - doOperation(((DifferenceNode) n).list.get(0)));

		} else if (n instanceof MultiplicationNode) {

			return (doOperation(((MultiplicationNode) n).list.get(0))
					* doOperation(((MultiplicationNode) n).list.get(1)));

		} else if (n instanceof AndNode) {

			if (doOperation(((AndNode) n).list.get(0)) != 0 && doOperation(((AndNode) n).list.get(1)) != 0) {

				return 1;

			} else {

				return 0;
			}

		} else if (n instanceof ModuloNode) {

			return (doOperation(((ModuloNode) n).list.get(1)) % doOperation(((ModuloNode) n).list.get(0)));

		} else if (n instanceof DivisionNode) {

			return (doOperation(((DivisionNode) n).list.get(1)) / doOperation(((DivisionNode) n).list.get(0)));

		} else if (n instanceof EqualToNode) {

			if (doOperation(((EqualToNode) n).list.get(1)) == doOperation(((EqualToNode) n).list.get(0))) {
				return 1;
			} else {
				return 0;
			}

		} else if (n instanceof GreaterThanNode) {

			if (doOperation(((GreaterThanNode) n).list.get(1)) > doOperation(((GreaterThanNode) n).list.get(0))) {
				return 1;
			} else {
				return 0;
			}

		}
		return 0;
	}

	// the implementation for the interpreting visitor
	// for main, all the nodes in the list are visited
	// in a for loop
	// same for the other nodes that have one or more children
	// they are visited too
	@Override
	public void visit(MainNode n) {
		for (int i = 0; i < n.list.size(); i++) {
			Node aux = n.list.get(i);
			aux.accept(this);
		}
	}

	@Override
	public void visit(DeclareNode n) {

		addNewVar((n.c).value, (n.n).varName);

	}

	@Override
	public void visit(StringNode n) {

	}

	@Override
	public void visit(ConstantNode n) {
		values.add(Integer.toString(n.value));
	}

	@Override
	public void visit(LvalNode n) {
		values.add(n.varName);
	}

	@Override
	public void visit(VariableNode n) {

	}

	// the print node is the only one that affects the output
	// in the interpreter
	@Override
	public void visit(PrintNode n) {

		if (n.type == 1) {

			output = output + n.str.text + "\n";

		} else if (n.type == 2) {

			output = output + n.cons.value + "\n";

		} else {

			output = output + getValue(n.var.varName) + "\n";
		}
	}

	@Override
	public void visit(RvalNode n) {
		values.add(n.varName);
	}

	@Override
	public void visit(AssignmentNode n) {
		int index = values.size() - 1;

		(n.list.get(0)).accept(this);
		(n.list.get(1)).accept(this);

		String param1 = values.get(index + 1);
		String param2 = values.get(index + 2);

		if (checkVar(param2))
			modifyValue(param1, getValue(param2));
		else
			modifyValue(param1, Integer.parseInt(param2));

	}

	@Override
	public void visit(MultiplicationNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(SumNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(OrNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));
	}

	@Override
	public void visit(ConditionNode n) {

	}

	@Override
	public void visit(IfNode n) {
		String condition = ((ConditionNode) n.list.get(0)).varName;

		if (getValue(condition) != 0) {

			Node aux = ((IfNode) n).list.get(1);
			aux.accept(this);

		} else if (((IfNode) n).type == 2) {

			Node aux = ((IfNode) n).list.get(2);
			aux.accept(this);
		}

	}

	@Override
	public void visit(WhileNode n) {

		String condition = ((ConditionNode) n.list.get(0)).varName;

		while (getValue(condition) != 0) {

			Node aux = ((WhileNode) n).list.get(1);
			aux.accept(this);

		}

	}

	@Override
	public void visit(BodyNode n) {

		for (int i = 0; i < n.list.size(); i++) {
			Node aux = n.list.get(i);
			aux.accept(this);
		}

	}

	@Override
	public void visit(DifferenceNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(ModuloNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(GreaterThanNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(EqualToNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(DivisionNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

	@Override
	public void visit(AndNode n) {

		int result = doOperation(n);
		values.add(Integer.toString(result));

	}

}
