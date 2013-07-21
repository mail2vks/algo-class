package org.vivek.algo.wk1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class InversionCounter {

	private static Logger logger = Logger.getLogger(InversionCounter.class.getName());
	private static long count = 0;

	public static void main(String args[]) throws IOException {

		Integer[] data = readDataFileToArray();

		getNumberOfInversions(data);

		logger.info("Computed number of inversions as " + count);
	}

	private static Integer[] getNumberOfInversions(final Integer[] data) {
		int size = data.length;
		if (size == 1)
			return data;

		Integer[] leftSplit = Arrays.copyOfRange(data, 0, size / 2);
		Integer[] rightSplit = Arrays.copyOfRange(data, size / 2, size);

		leftSplit = getNumberOfInversions(leftSplit);
		rightSplit = getNumberOfInversions(rightSplit);
		Integer[] sortedData = getNumberOfSplitInversions(leftSplit, rightSplit, size);

		return sortedData;
	}

	private static Integer[] getNumberOfSplitInversions(Integer[] leftSplit, Integer[] rightSplit, int size) {

		Integer[] data = new Integer[size];
		int leftLimit = leftSplit.length;
		int rightLimit = rightSplit.length;
		int i = 0, j = 0, k = 0;
		while (i < leftLimit || j < rightLimit) {
			if (i < leftLimit && j < rightLimit) {
				if (leftSplit[i] <= rightSplit[j]) {
					data[k++] = leftSplit[i++];
				} else if (leftSplit[i] > rightSplit[j]) {
					data[k++] = rightSplit[j++];
					count += (leftLimit - i);
				}
			} else if (i == leftLimit) {
				while (j < rightLimit) {
					data[k++] = rightSplit[j++];
				}
			} else if (j == rightLimit) {
				while (i < leftLimit) {
					data[k++] = leftSplit[i++];
				}
			}
		}

		return data;
	}

	private static Integer[] readDataFileToArray() throws IOException {
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(InversionCounter.class.getClassLoader().getResourceAsStream("inversion-data.txt")));
		List<Integer> inputList = new ArrayList<Integer>();
		while (fileReader.ready()) {
			Integer value = Integer.parseInt(fileReader.readLine());
			inputList.add(value);
		}
		fileReader.close();
		Integer[] data = new Integer[inputList.size()];
		return inputList.toArray(data);
	}
}
