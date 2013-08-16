package org.vivek.algo.wk6;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class TwoSumChecker {

    private static final Logger logger = Logger.getLogger(TwoSumChecker.class.getName());
    private static HashSet<Long> dataSet;

    public TwoSumChecker(String fileName) throws IOException {
        initialize(fileName);
    }

    private static void loadFileToMemory(final String fileName) throws IOException {
        URL filePath = TwoSumChecker.class.getClassLoader().getResource(fileName);
        if (filePath == null) {
            System.out.println("Could not locate " + fileName);
            return;
        }
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath.getFile()), Charset.defaultCharset());
        while (fileReader.ready()) {
            dataSet.add(Long.valueOf(fileReader.readLine()));
        }
        fileReader.close();
        logger.info("Completed File Load: " + fileName);
    }

    public static void main(String args[]) throws IOException {
        logger.info(String.valueOf(new TwoSumChecker("2sum.txt").countUniqueSum()));
    }

    private void initialize(String fileName) throws IOException {
        dataSet = new HashSet<>();
        loadFileToMemory(fileName);
    }

    public int countUniqueSum() {
        int minRange = -10000;
        int maxRange = 10000;
        Set<Long> uniqueSumSet = new HashSet<>();
        for (Long element : dataSet) {
            for (long t = minRange; t <= maxRange; t++) {
                if ((t != 2 * element) && dataSet.contains(t - element)) {
                    //logger.info(String.format("(%s + %s = %s)", element, t - element, t));
                    uniqueSumSet.add(t);
                }
            }
        }
        return uniqueSumSet.size();
    }
}
