package org.vivek.algo2.wk1;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreedySchedulingAlgorithm {

    private static Logger logger = Logger.getLogger(GreedySchedulingAlgorithm.class.getName());
    private Set<Schedule> sortedDateSet;

    public static void main(String[] args) throws IOException {

        System.out.println(new GreedySchedulingAlgorithm().returnMaximumWeightFromSchedule("jobs.txt", new ScheduleComparators().getSubBasedComparator()));
        System.out.println(new GreedySchedulingAlgorithm().returnMaximumWeightFromSchedule("jobs.txt", new ScheduleComparators().getRatioBasedComparator()));

    }

    public BigDecimal returnMaximumWeightFromSchedule(final String inputFileName, final Comparator<Schedule> greedyAlgo) throws IOException {

        initialize(inputFileName, greedyAlgo);

        return calculateOptimalWeight();
    }

    private BigDecimal calculateOptimalWeight() {

        BigDecimal currentLength = BigDecimal.ZERO;
        BigDecimal optimalWeight = BigDecimal.ZERO;

        //logger.log(Level.INFO, sortedDateSet.toString());
        for (Schedule schedule : sortedDateSet) {
            currentLength = currentLength.add(BigDecimal.valueOf(schedule.getLength()));
            optimalWeight = optimalWeight.add(currentLength.multiply(BigDecimal.valueOf(schedule.getWeight())));
        }
        return optimalWeight;
    }

    private void initialize(final String inputFileName, final Comparator<Schedule> comparator) throws IOException {
        URL filePath = GreedySchedulingAlgorithm.class.getClassLoader().getResource(inputFileName);
        if (filePath == null) {
            logger.log(Level.INFO, "Could not locate " + inputFileName);
            return;
        }
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath.getFile()), Charset.defaultCharset());

        /* Reads the count which is not used */
        logger.log(Level.INFO, "Total number of schedules " + fileReader.readLine());

        sortedDateSet = new TreeSet(comparator);

        while (fileReader.ready()) {
            sortedDateSet.add(new Schedule(StringUtils.split(fileReader.readLine(), ' ')));
        }
    }
}
