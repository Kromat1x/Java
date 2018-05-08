
public class Rm implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		State s = new State();
		int recursive = 0;
		String[] parts_aux = parameter.split(" ");
		String path;
		String pathForCross = "";
		int index = 0;
		if(parts_aux.length == 2){
			recursive = 1;
			path = parts_aux[1];
		} else {
			path = parts_aux[0];
		}
		
		String[] parts = path.split("/");
		int len = parts.length;
		
		if(len == 1) {
			pathForCross = path;
		
		} else if(len > 0 ) {
			for (int i = 0; i < len - 2; i++) {
				pathForCross +=  parts[i] + "/";
			}
			pathForCross += parts[len - 2];
		}
		
		if(len != 1)
			s = TreeCrossing.crossing(pathForCross, fileSys.currentDirectory, fileSys.currentUser);
		else {
			s.exitCode = 0;
			s.dir = fileSys.currentDirectory;
		}
		
		if(recursive == 0) {
			if (s.exitCode != 0) {
				ErrorCode.displayError(s.exitCode, "rm");
				return;
			} else {
				fileSys.currentDirectory = s.dir;
				Entity aux;
				for (int i = 0; i < fileSys.currentDirectory.list.size(); i++) {
					aux = fileSys.currentDirectory.list.get(i);
					if (aux.name.equals(parts[len - 1])) {

						if (aux.permissions[0] == 'f') {
							index = 1;
							if(fileSys.currentUser.equals("root")){
								
								fileSys.currentDirectory.list.remove(i);
								
							} else if(fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
								
								if(fileSys.currentDirectory.permissions[2] == 'w') {
									
									fileSys.currentDirectory.list.remove(i);
									
								} else {								
									s.exitCode = 5;
									ErrorCode.displayError(s.exitCode, "rm");
								}
								
							} else if(!fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
								
								if(fileSys.currentDirectory.permissions[5] == 'w') {
									
								} else {
									s.exitCode = 5;
									ErrorCode.displayError(s.exitCode, "rm");
								}
							}
							
						} else if (aux.permissions[0] == 'd') {
							s.exitCode = 1;
							ErrorCode.displayError(s.exitCode, "rm");
							break;
						}
					}
				}
				if(index == 0){
					ErrorCode.displayError(11, "rm");
				}
			}
		} else if (recursive == 1) {
			if (s.exitCode != 0) {
				ErrorCode.displayError(s.exitCode, "rm");
				return;
			} else {
				fileSys.currentDirectory = s.dir;
				Directory parent = fileSys.currentDirectory;
				while(!parent.name.equals("/")) {
					
				}
			}
		}
	}
}
