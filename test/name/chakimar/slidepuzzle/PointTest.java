package name.chakimar.slidepuzzle;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PointTest {

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
	public void testEquals() {
		
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 0);
		assertEquals(p1, p2);
		assertNotSame(p1, p2);
		
		Point p3 = new Point(1, 2);
		Point p4 = new Point(3, 4);
		assertFalse(p3.equals(p4));
	}
	
	@Test
	public void testHashCode() {

		Point p1 = new Point(0, 0);
		assertTrue(p1.hashCode() == 0);
		
		Point p2 = new Point(1,2);
		assertTrue(p2.hashCode() == 12);
	}
}
