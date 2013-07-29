package org.vivek.algo.wk3;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class UnDirectedGraphTest {
	@Test
	public void testGetMinCuts() throws Exception {

		Set<Integer> resultSet = new TreeSet<>();
		for (int i = 0; i < 10; i++) {
			resultSet.add(new UnDirectedGraph("kargerMinCutTest1.txt").getMinCuts());
		}
		System.out.println(resultSet);
		assertEquals(2, (int) resultSet.toArray()[0]);
	}
}
