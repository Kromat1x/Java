
public class File extends Entity {
	
	String text;
	Directory parent;
	
	public File(String name, Directory parent, String owner) {
		super(name, owner);
		this.parent = parent;
		this.permissions[0] = 'f';
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
