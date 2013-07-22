package org.vivek.algo.wk2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickSortTest {

	private QuickSort firstElementBased;
	private QuickSort lastElementBased;
	private QuickSort medianElementBased;

	@Before
	public void setUp() throws Exception {
		firstElementBased = new FirstElementBasedQuickSort();
		lastElementBased = new LastElementBasedQuickSort();
		medianElementBased = new MedianElementBasedQuickSort();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testSortFor10() throws Exception {

		String fileName = "10.txt";
		assertEquals(Long.valueOf(25), firstElementBased.sort(fileName));
		assertEquals(Long.valueOf(29), lastElementBased.sort(fileName));
		assertEquals(Long.valueOf(21), medianElementBased.sort(fileName));
	}

	@Test
	public void testSortFor100() throws Exception {

		String fileName = "100.txt";
		assertEquals(Long.valueOf(615), firstElementBased.sort(fileName));
		assertEquals(Long.valueOf(587), lastElementBased.sort(fileName));
		assertEquals(Long.valueOf(518), medianElementBased.sort(fileName));
	}

	@Test
	public void testSortFor1000() throws Exception {

		String fileName = "1000.txt";
		assertEquals(Long.valueOf(10297), firstElementBased.sort(fileName));
		assertEquals(Long.valueOf(10184), lastElementBased.sort(fileName));
		assertEquals(Long.valueOf(8921), medianElementBased.sort(fileName));
	}
}
