package org.vivek.algo2.wk1;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GreedySchedulingAlgorithmTest {

    @Rule
    public TestName testName = new TestName();

    @Before
    public void before() {
        System.out.println("\nRunning Test : " + testName.getMethodName());
    }

    @After
    public void after() {
        System.out.println("\nCompleted Test : " + testName.getMethodName());
    }

    @Test
    public void test10SchedulesWeightBySubtraction() throws IOException {
        GreedySchedulingAlgorithm instance = new GreedySchedulingAlgorithm();
        assertEquals(BigDecimal.valueOf(40135), instance.returnMaximumWeightFromSchedule("10-schedules.txt", new ScheduleComparators().getSubBasedComparator()));
    }

    @Test
    public void test100SchedulesWeightBySubtraction() throws IOException {
        GreedySchedulingAlgorithm instance = new GreedySchedulingAlgorithm();
        assertEquals(BigDecimal.valueOf(7226993), instance.returnMaximumWeightFromSchedule("100-schedules.txt", new ScheduleComparators().getSubBasedComparator()));
    }

    @Test
    public void test10SchedulesWeightByRatio() throws IOException {
        GreedySchedulingAlgorithm instance = new GreedySchedulingAlgorithm();
        assertEquals(BigDecimal.valueOf(38638), instance.returnMaximumWeightFromSchedule("10-schedules.txt", new ScheduleComparators().getRatioBasedComparator()));
    }

    @Test
    public void test100SchedulesWeightByRatio() throws IOException {
        GreedySchedulingAlgorithm instance = new GreedySchedulingAlgorithm();
        assertEquals(BigDecimal.valueOf(7078040), instance.returnMaximumWeightFromSchedule("100-schedules.txt", new ScheduleComparators().getRatioBasedComparator()));
    }

    @Test
    public void test1000SchedulesWeightBySubtraction() throws IOException {
        GreedySchedulingAlgorithm instance = new GreedySchedulingAlgorithm();
        assertEquals(BigDecimal.valueOf(706524469), instance.returnMaximumWeightFromSchedule("1000-schedules.txt", new ScheduleComparators().getSubBasedComparator()));
    }

    @Test
    public void test1000SchedulesWeightByRatio() throws IOException {
        GreedySchedulingAlgorithm instance = new GreedySchedulingAlgorithm();
        assertEquals(BigDecimal.valueOf(688694325), instance.returnMaximumWeightFromSchedule("1000-schedules.txt", new ScheduleComparators().getRatioBasedComparator()));
    }
}
