package org.vivek.algo.wk6;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class MedianCalculator {
    private static final Logger logger = Logger.getLogger(MedianCalculator.class.getName());
    private static PriorityQueue<Integer> lowHeap;
    private static PriorityQueue<Integer> highHeap;
    private static LinkedList<Integer> listInput;

    public MedianCalculator(String fileName) throws IOException {
        initialize(fileName);
    }

    private static void loadFileToMemory(final String fileName) throws IOException {
        URL filePath = MedianCalculator.class.getClassLoader().getResource(fileName);
        if (filePath == null) {
            System.out.println("Could not locate " + fileName);
            return;
        }
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath.getFile()), Charset.defaultCharset());
        while (fileReader.ready()) {
            listInput.add(Integer.valueOf(fileReader.readLine()));
        }
        fileReader.close();
        logger.info("Completed File Load: " + fileName);
    }

    public static void main(String args[]) throws IOException {
        logger.info(String.valueOf(new MedianCalculator("median.txt").calculateMedianSum() % 10000));
    }

    private void initialize(String fileName) throws IOException {
        lowHeap = new PriorityQueue<>(5000, Collections.reverseOrder());
        highHeap = new PriorityQueue<>(5000);
        listInput = new LinkedList<>();
        loadFileToMemory(fileName);
    }

    public int calculateMedianSum() {
        int medianSum = 0;
        for (Integer element : listInput) {
            if (lowHeap.peek() == null || element <= lowHeap.peek()) {
                lowHeap.add(element);
            } else if (highHeap.peek() == null || element >= highHeap.peek()) {
                highHeap.add(element);
            } else if (lowHeap.peek() < element && element < highHeap.peek()) {
                highHeap.add(element);
            }

            if (Math.abs(lowHeap.size() - highHeap.size()) == 2) {
                if (lowHeap.size() > highHeap.size()) {
                    highHeap.add(lowHeap.poll());
                } else if (lowHeap.size() < highHeap.size()) {
                    lowHeap.add(highHeap.poll());
                }
            }

            Integer median = 0;
            if (lowHeap.size() == highHeap.size()) {
                median = lowHeap.peek();
            } else if (lowHeap.size() > highHeap.size()) {
                median = lowHeap.peek();
            } else if (lowHeap.size() < highHeap.size()) {
                median = highHeap.peek();
            }
            //logger.info(String.format("Median for [%s] and [%s] is [%s]", lowHeap, highHeap, median));
            medianSum += median;
        }
        return medianSum;
    }
}
