package name.chakimar.slidepuzzle.logic;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.Game;
import name.chakimar.slidepuzzle.Player;
import name.chakimar.slidepuzzle.exception.GameOver;
import name.chakimar.slidepuzzle.util.AnswerUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeftHandStrategyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Game.getInstance().load(new File("data.txt"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Game game;

	@Before
	public void setUp() throws Exception {
		this.game = Game.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStrategy() throws GameOver {
		Player player = new Player();
		Strategy strategy = new LeftHandStrategy();
		player.addStrategy(strategy);
		Board board = null;
		List<Command> cmdList = null;

		/*
		 * クリアできない。
		 * 013
		 * 42=
		 * 786
		 * 
		 */
		board = new Board("3,3,01342=5786");
		try {
			cmdList = player.playGame(board);
			fail();
		} catch (GameOver e) {
		}
		
		/*
		 * クリアできる。
		 * 013
		 * 42=
		 * 758
		 * 
		 * RRLDDR
		 */
		board = new Board("3,3,01342=758");
		cmdList = player.playGame(board);
		assertSame(cmdList.size(), 6);
		assertTrue(AnswerUtil.isCorrectAnswer(board, cmdList));
		
	}
	
	@Test
	public void testClearBoardCount() {
		Player player = new Player();
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
