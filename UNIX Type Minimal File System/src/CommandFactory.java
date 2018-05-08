
public class CommandFactory {
	public Command getCommand(String commandType) {
		if (commandType == null) {
			return null;
		}
		if (commandType.equals("adduser")) {
			return new AddUser();
		} else if (commandType.equals("deluser")) {
			return new DelUser();
		} else if (commandType.equals("chuser")) {
			return new ChUser();
		} else if (commandType.equals("cd")) {
			return new Cd();
		} else if (commandType.equals("mkdir")) {
			return new MkDir();
		} else if (commandType.equals("touch")) {
			return new Touch();
		} else if (commandType.equals("ls")) {
			return new Ls();
		} else if (commandType.equals("rm")) {
			return new Rm();
		}

		return null;
	}
}
