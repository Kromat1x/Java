
public class Cd implements Command {

	@Override
	public void command(String parameter, FileSystem fileSys) {
		State s = new State();
		if(parameter.charAt(0) == '/'){
			fileSys.currentDirectory = fileSys.root;
			s = TreeCrossing.crossing(parameter, fileSys.currentDirectory, fileSys.currentUser);
			if(s.exitCode != 0){
				ErrorCode.displayError(s.exitCode, "cd");		
			} else {
				fileSys.currentDirectory = s.dir;
			}
		} else {
			s = TreeCrossing.crossing(parameter, fileSys.currentDirectory, fileSys.currentUser);
			if(s.exitCode !=0){
				ErrorCode.displayError(s.exitCode, "cd");
			} else {
				fileSys.currentDirectory = s.dir;
			}
		}
	}
}
