package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public class Node implements Visitable {

	String name;

	public Node(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void accept(Visitor v) {

	}
}
