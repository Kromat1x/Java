
public class DelUser implements Command{

	@Override
	public void command(String parameter, FileSystem fileSys) {
		
		if(!fileSys.currentUser.equals("root")){
			ErrorCode.displayError(10, "deluser");
		} else if(!fileSys.usersList.contains(parameter)){
			ErrorCode.displayError(8, "deluser");
		} else {
			fileSys.usersList.remove(parameter);
			for(Entity aux : fileSys.root.list) {
				if(aux.name.equals(parameter)) {
					aux.owner = fileSys.usersList.get(1);
				}
			}
		}
	}

}
