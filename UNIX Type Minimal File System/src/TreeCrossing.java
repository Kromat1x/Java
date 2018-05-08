

public class TreeCrossing {

	public static State crossing(String path, Directory startingDir, String user) {
		State s = new State();
		s.exitCode = 0;
		s.dir = startingDir;
		int pathCounter = 0;
		int did_i_find = 0;
		String[] parts = path.split("/");
		int nrOfParts = parts.length;
		
		if(nrOfParts == 0) {
			s.dir = startingDir;
			return s;
			
		}
		
		
		if (parts[0].equals("")) {
			pathCounter = 1;
		}

		while (s.exitCode == 0 && pathCounter != nrOfParts) {

			if (parts[pathCounter].equals("..")) {
				if (!s.dir.name.equals("root")) {// daca dau cd.. pe root raman
													// tot in root ca nu are
													// parinte #feels #bad
					s.dir = s.dir.parent;
					pathCounter++;
					continue;
				} else {
					pathCounter++;
					continue;
				}

			} else if (parts[pathCounter].equals(".")) {
				pathCounter++;
				continue;

			} else {

				for (Entity aux : s.dir.list) {
					
					if (aux.name.equals(parts[pathCounter])) {

						did_i_find = 1;
						if (aux.permissions[0] == 'f') {
							s.exitCode = 3;
							break;

						} else if (aux.permissions[0] == 'd') {
							if(user.equals("root")) {
								s.dir = (Directory) aux;
								pathCounter++;
								break;
								
							} else if (aux.owner.equals(user)) {
								if (aux.permissions[3] == 'x') {
									s.dir = (Directory) aux;
									pathCounter++;
									break;
									// E bun, merg mai departe
								} else {
									s.exitCode = 6;
									break;
								}
							} else if (!aux.owner.equals(user)) {
								if (aux.permissions[6] == 'x') {
									s.dir = (Directory) aux;
									pathCounter++;
									break;
								} else {
									s.exitCode = 6;
									break;
								}
							}
						}
					}
				}
				if (did_i_find == 0) {
					s.exitCode = 2;
				}
			}
		}
		return s;

	}
}
