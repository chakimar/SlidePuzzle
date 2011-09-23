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

public class RightHandStrategyTest {

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
		player.addStrategy(new RightHandStrategy());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStrategy() throws GameOver {
		Board board = null;
		List<Command> cmdList = null;

		/*
		 * クリアできない。
		 * 013
		 * 425
		 * 7=6
		 * 
		 */
		board = new Board("3,3,0134257=6");
		try {
			cmdList = player.playGame(board);
			fail();
		} catch (GameOver e) {
		}
		
		/*
		 * クリアできる。
		 * 023
		 * 145
		 * 7=6
		 * 
		 * DDURRD
		 */
		board = new Board("3,3,0231457=6");
		cmdList = player.playGame(board);
		assertSame(cmdList.size(), 6);
		assertTrue(AnswerUtil.isCorrectAnswer(board, cmdList));
		
	}
	
	@Test
	public void testClearBoardCount() {
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
