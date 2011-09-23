package name.chakimar.slidepuzzle.logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandTest {

	@Test
	public void testCreateLeftCommand() {
		assertEquals(Command.createLeftCommand().getType(), Command.LEFT);
	}

	@Test
	public void testCreateRightCommand() {
		assertEquals(Command.createRightCommand().getType(), Command.RIGHT);
	}

	@Test
	public void testCreateUpCommand() {
		assertEquals(Command.createUpCommand().getType(), Command.UP);
	}

	@Test
	public void testCreateDownCommand() {
		assertEquals(Command.createDownCommand().getType(), Command.DOWN);
	}

}
