
public class Touch implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		State s = new State();
		Directory saveDir = fileSys.currentDirectory; //TODO Restituiesc directorul curent
		String[] parts = parameter.split("/");
		String pathForMk = "";
		int len = parts.length;
		
		if(len == 1) {
			pathForMk = parameter;
		
		} else if(len > 0 ) {
			for (int i = 0; i < len - 2; i++) {
				pathForMk +=  parts[i] + "/";
			}
			pathForMk += parts[len - 2];
		}
		
		if(parameter.charAt(0) == '/'){
			fileSys.currentDirectory = fileSys.root;
		}
		if(len != 1)
			s = TreeCrossing.crossing(pathForMk, fileSys.currentDirectory, fileSys.currentUser);
		else {
			s.exitCode = 0;
			s.dir = fileSys.currentDirectory;
		}
		if (s.exitCode != 0) {
			ErrorCode.displayError(s.exitCode, "touch");
			fileSys.currentDirectory = saveDir;
			s.dir = saveDir;//this is added while i am sleepy
			return;
		} else {
			fileSys.currentDirectory = s.dir;
			for (Entity aux : fileSys.currentDirectory.list) {

				if (aux.name.equals(parts[len - 1])) {

					if (aux.permissions[0] == 'f') {
						s.exitCode = 7;
						ErrorCode.displayError(s.exitCode, "touch");
						break;
					} else if (aux.permissions[0] == 'd') {
						s.exitCode = 1;
						ErrorCode.displayError(s.exitCode, "touch");
						break;
					}
				}
			}
		}
		if (s.exitCode == 0) {
			if(fileSys.currentUser.equals("root")){
				File aux = new File(parts[len - 1], fileSys.currentDirectory, fileSys.currentUser);
				fileSys.currentDirectory.list.add(aux);
			} else if (fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
				if (fileSys.currentDirectory.permissions[2] == 'w') {
					File aux = new File(parts[len - 1], fileSys.currentDirectory, fileSys.currentUser);
					fileSys.currentDirectory.list.add(aux);
				} else {
					ErrorCode.displayError(5, "touch");
				}
			} else if (!fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
				if(fileSys.currentDirectory.permissions[5] == 'w') {
					File aux = new File(parts[len - 1], fileSys.currentDirectory, fileSys.currentUser);
					fileSys.currentDirectory.list.add(aux);
				} else {
					ErrorCode.displayError(5, "touch");
				}
			}
		}
		fileSys.currentDirectory = saveDir;
		s.dir = saveDir;
	}
}
