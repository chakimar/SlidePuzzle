package name.chakimar.slidepuzzle;

import java.util.ArrayList;
import java.util.List;

import name.chakimar.slidepuzzle.exception.GameOver;
import name.chakimar.slidepuzzle.exception.NoCommand;
import name.chakimar.slidepuzzle.logic.Command;
import name.chakimar.slidepuzzle.logic.Strategy;
import name.chakimar.slidepuzzle.util.AnswerUtil;

public class Player {

	/**
	 * 15パズルは最長80手らしい。
	 */
	private static final int MAX_HAND = 200;
	private List<Strategy> strategyList;
	
	public Player() {
		this.strategyList = new ArrayList<Strategy>();
	}
	
	public void addStrategy(Strategy strategy) {
		strategyList.add(strategy);
	}
	
	public int getStrategyCount() {
		return strategyList.size();
	}

	public List<Command> playGame(Board board) throws GameOver{
		List<Command> cmdList = null;
		for (Strategy strategy : strategyList) {
			cmdList = new ArrayList<Command>();
			Board copyBoard = board.copy();
			for (int i=0;i<MAX_HAND;i++) {
				Command cmd = null;
				try {
					cmd = strategy.nextCommand(copyBoard);
				} catch (NoCommand e) {
					break;
				}
				cmdList.add(cmd);
				copyBoard.execute(cmd);

				if (copyBoard.isGameClear()) {
					
					System.out.println(strategy + "で解けたよ");
					
					System.out.println(board);
					System.out.println(AnswerUtil.createCommandStringFromList(cmdList));
					System.out.println("**********************");
					return cmdList;
				}
			}
		}
		throw new GameOver();
	}

}
