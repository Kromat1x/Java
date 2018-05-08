
public class Writetofile implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		State s = new State();
		String[] parts_aux = parameter.split(" ");
		String path;
		String pathForCross = "";
		int index = 0;
		if(parts_aux.length == 2){
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
		
		if (s.exitCode != 0) {
			ErrorCode.displayError(s.exitCode, "writetofile");
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
							
							//TODO
							
						} else if(fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
							
							if(fileSys.currentDirectory.permissions[2] == 'w') {
								
								//TODO
								
							} else {								
								s.exitCode = 5;
								ErrorCode.displayError(s.exitCode, "writetofile");
							}
							
						} else if(!fileSys.currentDirectory.owner.equals(fileSys.currentUser)) {
							
							if(fileSys.currentDirectory.permissions[5] == 'w') {
								
								//TODO
								
							} else {
								s.exitCode = 5;
								ErrorCode.displayError(s.exitCode, "writetofile");
							}
						}
						
					} else if (aux.permissions[0] == 'd') {
						s.exitCode = 1;
						ErrorCode.displayError(s.exitCode, "writetofile");
						break;
					}
				}
			}
			if(index == 0){
				ErrorCode.displayError(11, "writetofile");
			}
		}

	}

}
