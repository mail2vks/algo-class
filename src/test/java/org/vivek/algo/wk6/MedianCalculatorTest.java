package org.vivek.algo.wk6;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedianCalculatorTest {
    @Test
    public void testCalculateMedian() throws Exception {
        assertEquals(23, new MedianCalculator("median_test.txt").calculateMedianSum());
    }
}
