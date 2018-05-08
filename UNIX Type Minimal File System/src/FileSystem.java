import java.util.LinkedList;
import java.util.List;

public class FileSystem {
	Directory root;
	List<String> usersList;
	String currentUser;
	Directory currentDirectory;
	List<Directory> path;

	public FileSystem() {
		
		this.root = new Directory("/", null, "root"); //root has no parent
		this.usersList = new LinkedList<String>();
		this.path = new LinkedList<Directory>();
		this.currentUser = "root";
		this.currentDirectory = this.root;
		root.permissions[1] = 'r';
		root.permissions[2] = 'w';
		root.permissions[3] = 'x';
		root.permissions[4] = 'r';
		root.permissions[5] = '-';
		root.permissions[6] = 'x';
		path.add(root);
		usersList.add("root");
	}	
}
