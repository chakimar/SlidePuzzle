package name.chakimar.slidepuzzle;

import java.io.File;

import name.chakimar.slidepuzzle.exception.GameOver;
import name.chakimar.slidepuzzle.logic.LeftHandStrategy;
import name.chakimar.slidepuzzle.logic.RightHandStrategy;

public class Main {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Game game = Game.getInstance();
		game.load(new File("data.txt"));
		Player player = new Player();
		player.addStrategy(new LeftHandStrategy());
		player.addStrategy(new RightHandStrategy());
		int clearBoardCount = 0;
		while (game.hasNextBoard()) {
			Board board = game.nextBoard();
			try {
				player.playGame(board);
			} catch (GameOver e) {
				continue;
			}
			clearBoardCount++;
		}
		System.out.println("クリアした数："+clearBoardCount);
	}

}
