package name.chakimar.slidepuzzle.util;

import java.util.ArrayList;
import java.util.List;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.logic.Command;

public class AnswerUtil {

	public static boolean isCorrectAnswer(Board board, List<Command> cmdList) {
		Board copyBoard = board.copy();
		for (Command cmd : cmdList) {
			copyBoard.execute(cmd);
		}
		if (copyBoard.isGameClear()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<Command> createCommandListFromString(String src) {
		List<Command> list = new ArrayList<Command>();
		for (int i=0;i<src.length();i++) {
			String cmdStr = String.valueOf(src.charAt(i));
			if ("L".equals(cmdStr)) {
				list.add(Command.createLeftCommand());
			} else if ("R".equals(cmdStr)) {
				list.add(Command.createRightCommand());
			} else if ("U".equals(cmdStr)) {
				list.add(Command.createUpCommand());
			} else if ("D".equals(cmdStr)) {
				list.add(Command.createDownCommand());
			}
		}
		return list;
	}
	
	public static String createCommandStringFromList(List<Command> cmdList) {
		StringBuilder sb = new StringBuilder();
		for (Command cmd : cmdList) {
			if (Command.LEFT.equals(cmd.getType())) {
				sb.append("L");
			} else if (Command.RIGHT.equals(cmd.getType())) {
				sb.append("R");
			} else if (Command.UP.equals(cmd.getType())) {
				sb.append("U");
			} else if (Command.DOWN.equals(cmd.getType())) {
				sb.append("D");
			}
		}
		return sb.toString();
	}

}
