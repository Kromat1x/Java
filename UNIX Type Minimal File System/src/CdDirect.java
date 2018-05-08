
public class CdDirect implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		String[] parts = parameter.split("/");
		Entity aux;
		if(parameter.charAt(0) == '/'){
			fileSys.currentDirectory = fileSys.root;
			int nrOfParts = parts.length;
			int x = 0;
			for(int i = 0; i < nrOfParts; i++){
				int listSize = fileSys.currentDirectory.list.size();
				for(int j = 0; j < listSize; j++){
					aux = fileSys.currentDirectory.list.get(j);
					if(aux.name.equals(parts[i])){
						if(aux.owner.equals(fileSys.currentUser)){
							if(aux.permissions[3] == 'x'){
								if(aux.permissions[0] == 'd'){
									fileSys.currentDirectory = (Directory) aux;
									x = 1;
									break;
								} else 
									System.out.println("-3: <cd>: Not a directory");
							} else 
								System.out.println("-6: <cd>: No rights to execute");							
						} else if (!aux.owner.equals(fileSys.currentUser)){
							if(aux.permissions[6] == 'x'){
								if(aux.permissions[0] == 'd'){
									fileSys.currentDirectory = (Directory) aux;
									x = 1;
									break;
								} else
									System.out.println("-3: <cd>: Not a directory");
							} else 
								System.out.println("-6: <cd>: No rights to execute");
						}
					}
				}
				if(x == 0)
					System.out.println("-2: <cd>: No such directory");
			}
		}	
	}
}
