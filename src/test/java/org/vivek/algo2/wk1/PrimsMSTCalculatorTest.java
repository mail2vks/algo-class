package org.vivek.algo2.wk1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimsMSTCalculatorTest {
    @Test
    public void testReturnMSTWeight() throws Exception {

        assertEquals(7, new PrimsMSTCalculator("prims-small-tree-1.txt").returnMSTWeight());

    }

    @Test
    public void testReturnMSTWeight2() throws Exception {

        assertEquals(-10, new PrimsMSTCalculator("prims-small-tree-2.txt").returnMSTWeight());
    }

    @Test
    public void testReturnMSTWeight3() throws Exception {

        assertEquals(-27534, new PrimsMSTCalculator("prims-small-tree-3.txt").returnMSTWeight());
    }

    @Test
    public void testReturnMSTWeight4() throws Exception {

        assertEquals(120971, new PrimsMSTCalculator("prims-medium-tree-1.txt").returnMSTWeight());
    }
}


