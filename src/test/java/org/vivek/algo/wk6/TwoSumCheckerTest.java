package org.vivek.algo.wk6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TwoSumCheckerTest {
    @Test
    public void testCountUniqueSum() throws Exception {
        assertEquals(5, new TwoSumChecker("2sum_test.txt").countUniqueSum());
    }
}
