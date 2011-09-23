package name.chakimar.slidepuzzle;


import static org.junit.Assert.*;
import name.chakimar.slidepuzzle.logic.Command;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testCanMoveToLeft() {
		Board board = null;
		
		/*
		 * 120
		 * 345
		 * 678
		 */
		board = new Board("3,3,120345678");
		assertTrue(board.canMoveToLeft());
		
		/*
		 * 102
		 * 345
		 * 678
		 */
		board = new Board("3,3,102345678");
		assertTrue(board.canMoveToLeft());
		
		/*
		 * 018
		 * 234
		 * 567
		 */
		board = new Board("3,3,018234567");
		assertFalse(board.canMoveToLeft());
		
		/*
		 * =01
		 * 234
		 * 567
		 */
		board = new Board("3,3,=01234567");
		assertFalse(board.canMoveToLeft());
	}
	
	@Test
	public void testCanMoveToRight() {
		Board board = null;
		/*
		 * 081
		 * 234
		 * 567
		 */
		board = new Board("3,3,081234567");
		assertTrue(board.canMoveToRight());
		
		/*
		 * 102
		 * 345
		 * 678
		 */
		board = new Board("3,3,102345678");
		assertTrue(board.canMoveToRight());

		/*
		 * 210
		 * 345
		 * 678
		 */
		board = new Board("3,3,210345678");
		assertFalse(board.canMoveToRight());
		
		/*
		 * 0=1
		 * 234
		 * 567
		 */
		board = new Board("3,3,0=1234567");
		assertFalse(board.canMoveToRight());
	}
	
	@Test
	public void testCanMoveToUp() {
		Board board = null;
		/*
		 * 123
		 * 645
		 * 078
		 */
		board = new Board("3,3,123645078");
		assertTrue(board.canMoveToUp());
		
		/*
		 * 123
		 * 045
		 * 678
		 */
		board = new Board("3,3,123045678");
		assertTrue(board.canMoveToUp());

		/*
		 * 023
		 * 145
		 * 678
		 */
		board = new Board("3,3,023145678");
		assertFalse(board.canMoveToUp());
		
		/*
		 * =12
		 * 034
		 * 567
		 */
		board = new Board("3,3,=12034567");
		assertFalse(board.canMoveToUp());
	}
	
	@Test
	public void testCanMoveToDown() {
		Board board = null;

		/*
		 * 023
		 * 145
		 * 678
		 */
		board = new Board("3,3,023145678");
		assertTrue(board.canMoveToDown());
		
		/*
		 * 123
		 * 045
		 * 678
		 */
		board = new Board("3,3,123045678");
		assertTrue(board.canMoveToDown());

		/*
		 * 123
		 * 645
		 * 078
		 */
		board = new Board("3,3,123645078");
		assertFalse(board.canMoveToDown());
		
		/*
		 * 012
		 * =34
		 * 567
		 */
		board = new Board("3,3,012=34567");
		assertFalse(board.canMoveToDown());
	}

	@Test
	public void testGetPoint() {
		/*
		 * 123
		 * 045
		 * 678
		 */
		Board board = new Board("3,3,123045678");
		Point zero = board.getPoint("0");
		assertEquals(zero, new Point(0, 1));
		Point four = board.getPoint("4");
		assertEquals(four, new Point(1, 1));
		
	}
	
	@Test
	public void testExecute() {
		/*
		 * 123
		 * 045
		 * 678
		 * 
		 * 0を上に移動すると以下になる
		 * 
		 * 023
		 * 145
		 * 678
		 * 
		 */
		Board board = new Board("3,3,123045678");
		board.execute(Command.createUpCommand());
		Point zero = board.getPoint("0");
		Point one = board.getPoint("1");

		assertEquals(zero, new Point(0,0));
		assertEquals(one, new Point(0,1));
		
	}

	@Test
	public void testIsGameClear() {
		/*
		 * クリアしていない
		 * 123
		 * 045
		 * 678
		 */
		Board board = new Board("3,3,123045678");
		assertFalse(board.isGameClear());
		/*
		 * 0は右下角に着ているががクリアしていない
		 * 123
		 * 567
		 * 480
		 */
		board = new Board("3,3,123567480");
		assertFalse(board.isGameClear());
		/*
		 * クリアしている
		 */
		board = new Board("3,3,123456780");
		assertTrue(board.isGameClear());
		/*
		 * クリアしている（壁あり）
		 * 123
		 * 45=
		 * 780
		 */
		board = new Board("3,3,12345=780");
		assertTrue(board.isGameClear());
		

		/*
		 * これからクリアする
		 * 012
		 * 453
		 * 786
		 * 
		 * RRDD
		 */
		board = new Board("3,3,012453786");
		board.execute(Command.createRightCommand());
		board.execute(Command.createRightCommand());
		board.execute(Command.createDownCommand());
		board.execute(Command.createDownCommand());
		assertTrue(board.isGameClear());

		
	}
	
	@Test
	public void testIsLastDestination() {
		Board board = new Board("3,3,123456780");
		assertTrue(board.isLastDestination());
		board = new Board("3,3,120345678");
		assertFalse(board.isLastDestination());
	}
	
	@Test
	public void testPopulateMapToStringSortedByPoint() {
		Board board = new Board("3,3,12=456=80");
		String mapStr = null;
		try {
			mapStr = board.populateMapToStringSortedByPoint();
		} catch (NullPointerException e) {
			fail(e.getMessage());
		}
		assertEquals("12=456=80", mapStr);
		
		board = new Board("5,6,12=E4D9HIF8=GN576LOABMTPKQSR0J");
		try {
			mapStr = board.populateMapToStringSortedByPoint();
		} catch (NullPointerException e) {
			fail(e.getMessage());
		}
		assertEquals("12=E4D9HIF8=GN576LOABMTPKQSR0J", mapStr);
		
	}
}
