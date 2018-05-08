
public class Test {

	public static void main(String[] args) {
		
		FileSystem fileSys = new FileSystem();
		CommandFactory cmdFactory = new CommandFactory();
		Command add = cmdFactory.getCommand("adduser");
		Command del = cmdFactory.getCommand("deluser");
		Command ch = cmdFactory.getCommand("chuser");
		Command cd = cmdFactory.getCommand("cd");
		Command ls = cmdFactory.getCommand("ls");
		Command mkdir = cmdFactory.getCommand("mkdir");
		Command touch = cmdFactory.getCommand("touch");
		
		add.command("zalmoxes", fileSys);
		mkdir.command("/home/andrei", fileSys);
		mkdir.command("/home", fileSys);
		mkdir.command("/home/andrei", fileSys);
		cd.command("home", fileSys);
		mkdir.command("zalmoxes", fileSys);
		cd.command("zalmoxes", fileSys);
		mkdir.command("getodacia", fileSys);
//		String parameter = "-r home/fa/faf/ae/e/w/we/w";
//		String pathForMk = "";
//		String[] parts = parameter.split(" ");
//		int len = parts.length;
		
//		if(len == 1) {
//			pathForMk = parameter;
//		
//		} else if(len > 0 ) {
//			for (int i = 0; i < len - 2; i++) {
//				pathForMk +=  parts[i] + "/";
//			}
//			pathForMk += parts[len - 2];
//		}
//		System.out.println(pathForMk);
		//System.out.println(parts.length);
	}

}
