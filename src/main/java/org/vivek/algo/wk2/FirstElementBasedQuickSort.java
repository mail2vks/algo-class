package org.vivek.algo.wk2;

import java.io.IOException;

public class FirstElementBasedQuickSort extends QuickSort {

	public static void main(final String[] args) throws IOException {
		FirstElementBasedQuickSort firstElementBasedQuickSort = new FirstElementBasedQuickSort();
		firstElementBasedQuickSort.sort("quicksort.txt");
	}

	@Override
	protected Integer findPivotIndex(final Integer[] input, final Integer startIndex, final Integer endIndex) {
		return startIndex;
	}
}
