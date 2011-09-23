package name.chakimar.slidepuzzle.logic;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.exception.NoCommand;

public class LeftHandStrategy implements Strategy {

	private boolean escapeFlag =false;
	
	@Override
	public Command nextCommand(Board board) throws NoCommand {
		if (board.isLastDestination()) {
			throw new NoCommand("私の目的は達成した。");
		}
		
		Command cmd = null;
		
		if (escapeFlag) {
			cmd = Command.createDownCommand();
			escapeFlag = false;
			return cmd;
		}
		
		if (board.canMoveToRight()) {
			cmd = Command.createRightCommand();
		} else if (board.canMoveToDown()) {
			cmd = Command.createDownCommand(); 
		} else {
			cmd = Command.createLeftCommand();
			escapeFlag = true;
		}
		
		return cmd;
	}

	@Override
	public String toString() {
		return "LeftHandStrategy";
	}

}
