package name.chakimar.slidepuzzle.logic;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.File;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.Game;
import name.chakimar.slidepuzzle.Player;
import name.chakimar.slidepuzzle.exception.GameOver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StrategyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Game.getInstance().load(new File("data.txt"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Game game;
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		this.game = Game.getInstance();
		this.player = new Player();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testMultiStrategy() {
		player.addStrategy(new LeftHandStrategy());
		player.addStrategy(new RightHandStrategy());
		Board board = null;
		/*
		 * 左手の法則でクリアできる。
		 * 013
		 * 42=
		 * 758
		 * 
		 * RRLDDR
		 */
		board = new Board("3,3,01342=758");
		try {
			player.playGame(board);
		} catch (GameOver e) {
			fail();
		}

		/*
		 * 右手の法則でクリアできる。
		 * 023
		 * 145
		 * 7=6
		 * 
		 * DDURRD
		 */
		board = new Board("3,3,0231457=6");
		try {
			player.playGame(board);
		} catch (GameOver e) {
			fail();
		}
	}
	
	@Test
	public void testLeftAndRightHandStrategy() {
		player.addStrategy(new RightHandStrategy());
		player.addStrategy(new LeftHandStrategy());

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
		assertSame(clearBoardCount, 0);
	}

}
