
public class Entity {
	String name;
	//String path;
	String owner;
	char[] permissions;
	
	public Entity(String name, String owner){
		this.name = name;
		this.owner = owner;
		this.permissions = new char[7];
		for(int i = 0; i < 7; i++){
			permissions[i] = '-';
		}
		permissions[1] = 'r';
		permissions[2] = 'w';
		permissions[3] = 'x';
	}
}
