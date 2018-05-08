
public class ChUser implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		if(!fileSys.usersList.contains(parameter)){
			ErrorCode.displayError(8, "chuser");
		} else {
			fileSys.currentUser = parameter;
			for(Entity aux : fileSys.root.list) {
				if(aux.name.equals("parameter")) {
					fileSys.currentDirectory = (Directory) aux;
				}
			}
		}
	}

}
