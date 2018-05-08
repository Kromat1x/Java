
public class AddUser implements Command{
	
	@Override
	public void command(String parameter, FileSystem fileSys) {
		if(!fileSys.currentUser.equals("root")){
			ErrorCode.displayError(10, "adduser");
		} else if(fileSys.usersList.contains(parameter)){
			ErrorCode.displayError(9, "adduser");
		} else {
			fileSys.usersList.add(parameter);
			//System.out.println("numele userului = " + parameter);
			Directory userDirectory = new Directory(parameter, fileSys.root, parameter);
			//System.out.println("numele userlui dupa ce fac folderul = " + userDirectory.owner);
			fileSys.root.add(userDirectory);
		}	
	}
}
