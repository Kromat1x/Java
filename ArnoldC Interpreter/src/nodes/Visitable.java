package nodes;

/**
 * 
 * @author Marius Bina 324CD <bina.marius@gmail.com>
 *
 */
public interface Visitable {
	public void accept(Visitor v);
}
