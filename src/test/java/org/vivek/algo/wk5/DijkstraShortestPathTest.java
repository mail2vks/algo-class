package org.vivek.algo.wk5;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DijkstraShortestPathTest {
    @Test
    public void testComputeShortestPath() throws Exception {

        assertEquals(Integer.valueOf(26), new DijkstraShortestPath("dijkstraMediumTree.txt").computeShortestPath(13).get(7));
    }
}
