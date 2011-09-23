package name.chakimar.slidepuzzle.util;


import static org.junit.Assert.*;

import java.util.List;

import name.chakimar.slidepuzzle.Board;
import name.chakimar.slidepuzzle.logic.Command;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnswerUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testCreateCommandListFromString() {
		String src = "LRUD";
		List<Command> cmdList = AnswerUtil.createCommandListFromString(src);
		assertEquals(cmdList.get(0).getType(), Command.LEFT);
		assertEquals(cmdList.get(1).getType(), Command.RIGHT);
		assertEquals(cmdList.get(2).getType(), Command.UP);
		assertEquals(cmdList.get(3).getType(), Command.DOWN);
	}
	
	@Test
	public void testIsCorrectAnswer() {
		/*
		 * 023
		 * 146
		 * 758
		 * 
		 * DRDR
		 * 
		 */
		Board board = new Board("3,3,023146758");
		String src = "DRDR";
		List<Command> cmdList = AnswerUtil.createCommandListFromString(src);
		assertTrue(AnswerUtil.isCorrectAnswer(board, cmdList));
		
		src = "DDDD";
		cmdList = AnswerUtil.createCommandListFromString(src);
		assertFalse(AnswerUtil.isCorrectAnswer(board, cmdList));
	}

}
