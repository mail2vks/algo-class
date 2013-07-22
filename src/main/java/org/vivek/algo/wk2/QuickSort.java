package org.vivek.algo.wk2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public abstract class QuickSort {

    protected static final Logger logger = Logger.getLogger(QuickSort.class.getName());
    private static Long comparisons = 0L;

    private Integer[] readDataFileToArray(String fileName) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(QuickSort.class.getClassLoader().getResourceAsStream(fileName)));
        List<Integer> inputList = new ArrayList<>();
        while (fileReader.ready()) {
            Integer value = Integer.parseInt(fileReader.readLine());
            inputList.add(value);
        }
        fileReader.close();
        Integer[] data = new Integer[inputList.size()];
        return inputList.toArray(data);
    }

    protected abstract Integer findPivotIndex(Integer[] input, Integer startIndex, Integer endIndex);

    private Integer[] quickSort(final Integer[] input, final Integer startIndex, final Integer endIndex) {

        if (endIndex - startIndex <= 1)
            return input;

	    //logger.info(Arrays.toString(input));

        //logger.info(Arrays.toString(Arrays.copyOfRange(input, startIndex, endIndex)));

        Integer pivotIndexBeforePartition = this.findPivotIndex(input, startIndex, endIndex);
        Integer pivotIndexAfterPartition = partitionAroundPivot(input, pivotIndexBeforePartition, startIndex, endIndex);
	    //logger.info(Arrays.toString(input));
	    comparisons += (endIndex - startIndex - 1);

        if (pivotIndexAfterPartition > startIndex) {
            quickSort(input, startIndex, pivotIndexAfterPartition);
        }

        if (pivotIndexAfterPartition < endIndex) {
            quickSort(input, pivotIndexAfterPartition + 1, endIndex);
        }

	    //logger.info(Arrays.toString(input));

        return input;
    }

    private Integer partitionAroundPivot(final Integer[] input, final Integer pivotIndexBeforePartition, final Integer startIndex, final Integer endIndex) {

        Integer pivot = input[pivotIndexBeforePartition];
	    //logger.info("Pivot --> " + pivot);
        swap(input, startIndex, pivotIndexBeforePartition);

        Integer i = startIndex + 1;
        for (Integer j = i; j < endIndex; j++) {
            if (input[j] < pivot) {
                swap(input, j, i);
                i++;
            }
        }

        swap(input, startIndex, i - 1);

        return i - 1;
    }

    public Long sort(String fileName) throws IOException {
	    comparisons = 0L;
        Integer[] input = readDataFileToArray(fileName);
        Integer[] sortedArray = quickSort(input, 0, input.length);
        logger.info(Arrays.toString(sortedArray));
        logger.info("Total comparisons : " + comparisons);
	    return comparisons;
    }

    private void swap(final Integer[] input, final Integer from, final Integer to) {
        Integer temp = input[to];
        input[to] = input[from];
        input[from] = temp;
    }
}
