package name.chakimar.slidepuzzle.logic;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.exception.NoCommand;

public class ZigzagStrategy1 implements Strategy {

	private boolean escapeFlag;
	private Command prevCmd;

	@Override
	public Command nextCommand(Board board) throws NoCommand {
		if (board.isLastDestination()) {
			throw new NoCommand("私の目的は達成した。");
		}
		
		Command cmd = null;
		
		if (escapeFlag) {
			cmd = prevCmd;
			escapeFlag = false;
			return cmd;
		}
		
		if (prevCmd.getType().equals(Command.DOWN)) {
			if (board.canMoveToRight()) {
				cmd = Command.createRightCommand();
			}
		} else if (prevCmd.getType().equals(Command.RIGHT)) {
			if (board.canMoveToDown()) {
				cmd = Command.createDownCommand(); 
			} 
		} else {
			escapeFlag = true;
			cmd = prevCmd;
		}
		
		if (cmd!=null) {
			prevCmd = cmd;
		}
		
		return cmd;
	}

}
