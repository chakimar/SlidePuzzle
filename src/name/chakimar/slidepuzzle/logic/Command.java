package name.chakimar.slidepuzzle.logic;

public class Command {

	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	
	public static Command createLeftCommand() {
		return new Command(LEFT);
	}

	public static Command createRightCommand() {
		return new Command(RIGHT);
	}
	
	public static Command createUpCommand() {
		return new Command(UP);
	}

	public static Command createDownCommand() {
		return new Command(DOWN);
	}
	
	private String type;	
	
	private Command(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		String str = "";
		if (LEFT.equals(type)) {
			str = "L";
		} else if (RIGHT.equals(type)) {
			str = "R";
		} else if (UP.equals(type)) {
			str = "U";
		} else if (DOWN.equals(type)) {
			str = "D";
		}
		return str;
	}

}
