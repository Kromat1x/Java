import java.util.LinkedList;
import java.util.List;

public class Directory extends Entity {
	
	List<Entity> list;
	Directory parent;
	
	public Directory(String name, Directory parent, String owner) {
		super(name, owner);
		this.permissions[0] = 'd';
//		this.permissions[1] = 'r';
//		this.permissions[2] = 'w'; //TODO CHANGE THIS
//		this.permissions[3] = 'x'; //IT'S JUST FOR TESTING
//		this.permissions[4] = 'r';
//		this.permissions[5] = 'w';
//		this.permissions[6] = 'x';
		this.parent = parent;
		this.list = new LinkedList<Entity>();
	}
	
	public void add(Entity e){
		list.add(e);
	}

	public void remove(Entity e){
		list.remove(e);
	}
	
	public List<Entity> getListOfEntities(){
		return list;
	}

	public String toString(){
		return(name);
	}

}
