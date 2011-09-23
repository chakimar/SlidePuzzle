package name.chakimar.slidepuzzle.logic;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.exception.NoCommand;

public interface Strategy {

	Command nextCommand(Board board) throws NoCommand;

}
