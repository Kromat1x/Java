
public class MkDir implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		State s = new State();
		Directory saveDir = fileSys.currentDirectory; //TODO Restituiesc directorul curent
		if(!(parameter.charAt(0) == '/')) {
			
		} else {
			fileSys.currentDirectory = fileSys.root;
		}
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
		
		if(len != 1)
			s = TreeCrossing.crossing(pathForMk, fileSys.currentDirectory, fileSys.currentUser);
		else {
			s.exitCode = 0;
			s.dir = fileSys.currentDirectory;
		}
		if (s.exitCode != 0) {
			ErrorCode.displayError(s.exitCode, "mkdir");
			fileSys.currentDirectory = saveDir;
			s.dir = saveDir;
			return;//this is added while i am sleepy
		} else {
			fileSys.currentDirectory = s.dir;
			for (Entity aux : fileSys.currentDirectory.list) {

				if (aux.name.equals(parts[len - 1])) {

					if (aux.permissions[0] == 'f') {
						s.exitCode = 3;
						ErrorCode.displayError(s.exitCode, "mkdir");
						break;
					} else if (aux.permissions[0] == 'd') {
						s.exitCode = 1;
						ErrorCode.displayError(s.exitCode, "mkdir");
						break;
					}
				}
			}
		}
		if (s.exitCode == 0) {
			if(fileSys.currentUser.equals("root")) {
				Directory aux = new Directory(parts[len - 1], fileSys.currentDirectory, fileSys.currentUser);
				fileSys.currentDirectory.list.add(aux);
			} else if (fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
				if (fileSys.currentDirectory.permissions[2] == 'w') {
					Directory aux = new Directory(parts[len - 1], fileSys.currentDirectory, fileSys.currentUser);
					fileSys.currentDirectory.list.add(aux);
				} else {
					ErrorCode.displayError(5, "mkdir");
				}
			} else if (!fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
				if(fileSys.currentDirectory.permissions[5] == 'w') {
					Directory aux = new Directory(parts[len - 1], fileSys.currentDirectory, fileSys.currentUser);
					fileSys.currentDirectory.list.add(aux);
				} else {
					ErrorCode.displayError(5, "mkdir");
				}
			}
		}
		fileSys.currentDirectory = saveDir;
		s.dir = saveDir;
	}
}
