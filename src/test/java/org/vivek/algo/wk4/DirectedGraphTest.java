package org.vivek.algo.wk4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DirectedGraphTest {
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetFinalString() throws Exception {
		String test1Output = new DirectedGraph().getFinalString("SCC-1.txt");
		assertEquals("3,3,3,0,0", test1Output);

		String test2Output = new DirectedGraph().getFinalString("SCC-2.txt");
		assertEquals("3,3,2,0,0", test2Output);

		String test3Output = new DirectedGraph().getFinalString("SCC-3.txt");
		assertEquals("3,3,1,1,0", test3Output);

		String test4Output = new DirectedGraph().getFinalString("SCC-4.txt");
		assertEquals("7,1,0,0,0", test4Output);

		String test5Output = new DirectedGraph().getFinalString("SCC-5.txt");
		assertEquals("6,3,2,1,0", test5Output);
	}
}
