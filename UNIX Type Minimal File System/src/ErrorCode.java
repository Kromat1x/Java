
public class ErrorCode {
	
	
	public static void displayError(int code, String command) {
		switch(code) {
		case 1: System.out.println("-" + code + ": " + command + ": Is a directory");
				break;
				
		case 2: System.out.println("-" + code + ": " + command + ": No such directory");
				break;
				
		case 3: System.out.println("-" + code + ": " + command + ": Not a directory");
				break;
				
		case 4: System.out.println("-" + code + ": " + command + ": No rights to read");
				break;
				
		case 5: System.out.println("-" + code + ": " + command + ": No rights to write");
				break;
				
		case 6: System.out.println("-" + code + ": " + command + ": No rights to execute");
				break;
				
		case 7: System.out.println("-" + code + ": " + command + ": File already exists");
				break;
				
		case 8: System.out.println("-" + code + ": " + command + ": User does not exist");
				break;
				
		case 9: System.out.println("-" + code + ": " + command + ": User already exists");
				break;
				
		case 10: System.out.println("-" + code + ": " + command + ": No rights to change user status");
				break;
				
		case 11: System.out.println("-" + code + ": " + command + ": No such file");
				break;
				
		case 12: System.out.println("-" + code + ": " + command + ": No such file or directory");
				break;
				
		case 13: System.out.println("-" + code + ": " + command + ": Cannot delete parent or current directory");
				break;
				
		case 14: System.out.println("-" + code + ": " + command + ": Non empty directory");
				break;
			
		}
	}
}
