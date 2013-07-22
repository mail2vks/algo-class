package org.vivek.algo.wk2;

import java.io.IOException;
import java.util.Arrays;

public class MedianElementBasedQuickSort extends QuickSort {

	public static void main(final String[] args) throws IOException {
		MedianElementBasedQuickSort medianElementBasedQuickSort = new MedianElementBasedQuickSort();
		medianElementBasedQuickSort.sort("quicksort.txt");
	}

	@Override
	protected Integer findPivotIndex(final Integer[] input, final Integer startIndex, final Integer endIndex) {
		Integer[] medianArray = new Integer[3];
		medianArray[0] = input[startIndex];
		medianArray[1] = input[endIndex - 1];

		Integer middleElement = ((endIndex - 1 - startIndex) / 2) + startIndex;
		medianArray[2] = input[middleElement];

		Arrays.sort(medianArray);
		//logger.info(Arrays.toString(medianArray));

		if (medianArray[1] == input[startIndex])
			return startIndex;
		else if (medianArray[1] == input[endIndex - 1])
			return endIndex - 1;

		return middleElement;
	}
}
