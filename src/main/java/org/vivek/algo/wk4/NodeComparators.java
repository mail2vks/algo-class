package org.vivek.algo.wk4;

import java.util.Comparator;

public class NodeComparators {

	private static Comparator<Node> onFinishIndexDescending = new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return (-1) * Integer.compare(o1.getFinishIndex(), o2.getFinishIndex());
		}
	};

	public static Comparator getFinishIndexComparatorDescending() {
		return onFinishIndexDescending;
	}

}
