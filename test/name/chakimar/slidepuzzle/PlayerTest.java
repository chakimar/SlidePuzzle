package name.chakimar.slidepuzzle;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import name.chakimar.slidepuzzle.exception.GameOver;
import name.chakimar.slidepuzzle.logic.Command;
import name.chakimar.slidepuzzle.logic.LeftHandStrategy;
import name.chakimar.slidepuzzle.logic.RightHandStrategy;
import name.chakimar.slidepuzzle.logic.Strategy;
import name.chakimar.slidepuzzle.util.AnswerUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Player player;

	@Before
	public void setUp() throws Exception {
		this.player = new Player();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPlayGame() throws Exception {
		Strategy strategy = new LeftHandStrategy();
		player.addStrategy(strategy);
		/*
		 * クリアできない。
		 * 023
		 * 146
		 * 758
		 * 
		 * DRDR
		 * 
		 */
		Board board = new Board("3,3,023146758");
		List<Command> cmdList = null;
		try {
			cmdList = player.playGame(board);
			fail();
		} catch (GameOver e) {
		}
		
		/*
		 * クリアできる。
		 * 012
		 * 453
		 * 786
		 * 
		 * RRDD
		 */
		board = new Board("3,3,012453786");
		cmdList = player.playGame(board);
		assertTrue(cmdList.size() == 4);
		assertTrue(AnswerUtil.isCorrectAnswer(board, cmdList));
		
		/*
		 * クリアできる。
		 * 01=
		 * 425
		 * 786
		 * 
		 * RDRD
		 */
		board = new Board("3,3,01=425786");
		cmdList = player.playGame(board);
		assertTrue(cmdList.size() == 4);
		assertTrue(AnswerUtil.isCorrectAnswer(board, cmdList));

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
	}
	
	@Test
	public void testGetStrategyCount() {
		player.addStrategy(new LeftHandStrategy());
		player.addStrategy(new RightHandStrategy());
		assertSame(player.getStrategyCount(), 2);
	}
}
