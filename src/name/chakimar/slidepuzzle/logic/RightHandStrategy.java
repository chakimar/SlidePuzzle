package name.chakimar.slidepuzzle.logic;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.exception.NoCommand;

public class RightHandStrategy implements Strategy {

	private boolean escapeFlag;

	@Override
	public Command nextCommand(Board board) throws NoCommand {
		if (board.isLastDestination()) {
			throw new NoCommand("私の目的は達成した。");
		}
		
		Command cmd = null;
		
		if (escapeFlag) {
			cmd = Command.createRightCommand();
			escapeFlag = false;
			return cmd;
		}
		
		if (board.canMoveToDown()) {
			cmd = Command.createDownCommand();
		} else if (board.canMoveToRight()) {
			cmd = Command.createRightCommand(); 
		} else {
			cmd = Command.createUpCommand();
			escapeFlag = true;
		}
		
		return cmd;
	}

	@Override
	public String toString() {
		return "RightHandStrategy";
	}

}
