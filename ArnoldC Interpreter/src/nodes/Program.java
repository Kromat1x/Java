package nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class Program {
	MainNode main;
	List<Variable> list;

	/**
	 * The constructor that initializes the program
	 */
	public Program() {
		this.main = new MainNode();
		this.list = new ArrayList<Variable>();
	}

	/**
	 * Adding a variable the the list of variables
	 * 
	 * @param v
	 *            the new variable
	 */
	public void addVar(Variable v) {
		this.list.add(v);
	}

	/**
	 * Creating and adding a new variable to the list of variables
	 * 
	 * @param value
	 *            the value of the new variable
	 * @param name
	 *            the name of the new variable
	 */
	public void addNewVar(int value, String name) {
		Variable v = new Variable(value, name);
		this.list.add(v);
	}

	/**
	 * Check if a variable exists in the program
	 * 
	 * @param x
	 *            name of the variable
	 * @return true if it exists, false if it doesn't
	 */
	public boolean checkVar(String x) {
		for (Variable aux : this.list) {
			if (aux.name.equals(x))
				return true;
		}
		return false;
	}

	/**
	 * Get the value of a certain variable in the program
	 * 
	 * @param x
	 *            name of the variable
	 * @return the value demanded
	 */
	public int getValue(String x) {
		for (Variable aux : this.list) {
			if (aux.name.equals(x))
				return aux.value;
		}
		return 0;
	}
}
