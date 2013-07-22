package org.vivek.algo.wk2;

import java.io.IOException;

public class LastElementBasedQuickSort extends QuickSort {

	public static void main(final String[] args) throws IOException {
		LastElementBasedQuickSort lastElementBasedQuickSort = new LastElementBasedQuickSort();
		lastElementBasedQuickSort.sort("quicksort.txt");
	}

	@Override
	protected Integer findPivotIndex(final Integer[] input, final Integer startIndex, final Integer endIndex) {
		return endIndex - 1;
	}
}
