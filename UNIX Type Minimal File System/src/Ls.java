

public class Ls implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		State s = new State();
		Directory saveDir = fileSys.currentDirectory; //TODO Restituiesc directorul curent
		String[] parts = parameter.split("/");
		String pathForMk = "";
		Directory aux;
		String perms;
		int did_i_find = 0;
		int len = parts.length;
		String p = new String();
		
		if(len == 1) {
			pathForMk = parameter;
			p = parts[len - 1];
		
		} else if(len > 0 ) {
			for (int i = 0; i < len - 2; i++) {
				pathForMk +=  parts[i] + "/";
			}
			pathForMk += parts[len - 2];
			p = parts[len - 1];
		} else if(len == 0 ) {
			p = parameter;
		}
		
		if(parameter.charAt(0) == '/'){
			fileSys.currentDirectory = fileSys.root;
		}
		
		s = TreeCrossing.crossing(pathForMk, fileSys.currentDirectory, fileSys.currentUser);
		if(s.exitCode == 0) {
			if(p.equals(".")){
				aux = s.dir;
				if(aux.owner.equals("root")) {
					for(int i = 0; i < aux.list.size(); i++) {
						perms = new String(aux.list.get(i).permissions);
						
						System.out.println(aux.list.get(i).name + " " + perms + " " + aux.list.get(i).owner);
					}
				} else if(fileSys.currentUser.equals(aux.owner)) {
					if(aux.permissions[1] == 'r') {
						for(int i = 0; i < aux.list.size(); i++) {
							perms = new String(aux.list.get(i).permissions);
							
							System.out.println(aux.list.get(i).name + " " + perms + " " + aux.list.get(i).owner);
						}
					}
				}
			} else if(p.equals("..")) {
				aux = fileSys.currentDirectory.parent;
				if(aux.owner.equals("root")) {
					for(int i = 0; i < aux.list.size(); i++) {
						perms = new String(aux.list.get(i).permissions);
						System.out.println(aux.list.get(i).name + " " + perms + " " + aux.list.get(i).owner);
					}
				} else if(fileSys.currentUser.equals(aux.owner)) {
					if(aux.permissions[1] == 'r') {
						for(int i = 0; i < aux.list.size(); i++) {
							perms = new String(aux.list.get(i).permissions);
							System.out.println(aux.list.get(i).name + " " + perms + " " + aux.list.get(i).owner);
						}
					}
				}
			} else {
				aux = s.dir;
				for(int i = 0; i < aux.list.size(); i++) {
					if(aux.list.get(i).name.equals(p)) {
						did_i_find = 1;
						//auxEnt = aux.list.get(i);
						perms = new String(aux.list.get(i).permissions);
						if(aux.list.get(i).permissions[0] == 'f') {
							if(aux.list.get(i).owner.equals("root")) {
								System.out.println(aux.list.get(i).name + "  "+ perms + " " + aux.list.get(i).owner);
							} else if(fileSys.currentUser.equals(aux.list.get(i).owner)) {
								if(aux.list.get(i).permissions[1] == 'r')
									System.out.println(aux.list.get(i).name + "  "+ perms + " " + aux.list.get(i).owner);
								else {
									ErrorCode.displayError(4, "ls");
									break;
								}	
							} else if(!fileSys.currentUser.equals(aux.list.get(i).owner)) {
								if(aux.list.get(i).permissions[4] == 'r')
									System.out.println(aux.list.get(i).name + " " + perms + " " + aux.list.get(i).owner);
								else {
									ErrorCode.displayError(4, "ls");
									break;
								}
							}
						} else if(aux.list.get(i).permissions[0] == 'd') {
							if(aux.list.get(i).owner.equals("root")) {
								Directory aux_2 = (Directory) aux.list.get(i);
								for(Entity iter : aux_2.list) {
									perms = new String(iter.permissions);
									System.out.println(iter.name + " " + perms + " " + iter.owner);
								}
							} else if(fileSys.currentUser.equals(aux.list.get(i).owner)) {
								if(aux.list.get(i).permissions[1] == 'r') {
									Directory aux_2 = (Directory) aux.list.get(i);
									for(Entity iter : aux_2.list) {
										perms = new String(iter.permissions);
										System.out.println(iter.name + " " + perms + " " + iter.owner);
									}
								} else {
									ErrorCode.displayError(4, "ls");
									break;
								}
							} else if(!fileSys.currentUser.equals(aux.list.get(i).owner)) {
								if(aux.list.get(i).permissions[4] == 'r') {
									Directory aux_2 = (Directory) aux.list.get(i);
									for(Entity iter : aux_2.list) {
										perms = new String(iter.permissions);
										System.out.println(iter.name + " " + perms + " " + iter.owner);
									}
								} else {
									ErrorCode.displayError(4, "ls");
									break;
								}
							}
						}
					}
				}
				if(did_i_find == 0)
					ErrorCode.displayError(12, "ls");
			}
		}
		fileSys.currentDirectory = saveDir;
		s.dir = saveDir;
	}
}
