package name.chakimar.slidepuzzle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Game game;

	@Before
	public void setUp() throws Exception {
		this.game = Game.getInstance();
		game.load(new File("data.txt"));
	}

	@After
	public void tearDown() throws Exception {
		game.backToTopBoard();
	}

	@Test
	public void testGetInstance() {
		Game game = Game.getInstance();
		Game game2 = Game.getInstance();
		assertNotNull(game);
		assertNotNull(game2);
		assertSame(game, game2);
	}

	@Test
	public void testLoadFile() {
		try {
			game.load(new File("nodata.txt"));
			fail();
		} catch (FileNotFoundException e) {
		}
	}

	@Test
	public void testNextBoard() {
		Board board1 = game.nextBoard();
		Board board2 = null;
		for (int i=1;i<5000;i++) {
			board2 = board1;
			board1 = game.nextBoard();
			assertFalse(board1.equals(board2));
		}
	}

	@Test
	public void testHasNextBoard() {
		for(int i=0;i<5000;i++) {
			assertTrue(game.hasNextBoard());
			game.nextBoard();
		}
		assertFalse(game.hasNextBoard());
	}

	@Test
	public void testBackToTopBoard() {
		Board firstBoard = game.nextBoard();
		
		while (game.hasNextBoard()) {
			assertFalse(firstBoard.equals(game.nextBoard()));
		}
		
		game.backToTopBoard();
		assertEquals(firstBoard, game.nextBoard());
	}

}
