package org.vivek.algo.wk4;

public class Node implements Comparable<Node> {

	private int nodeNumber;
	private int leader;
	private int finishIndex;
	private boolean explored;

	public Node(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public int getLeader() {
		return leader;
	}

	public void setLeader(int leader) {
		this.leader = leader;
	}

	public int getFinishIndex() {
		return finishIndex;
	}

	public void setFinishIndex(int finishIndex) {
		this.finishIndex = finishIndex;
	}

	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.nodeNumber, o.nodeNumber);
	}

	@Override
	public String toString() {
		return "Node{" +
				"nodeNumber=" + nodeNumber +
				", leader=" + leader +
				", finishIndex=" + finishIndex +
				", explored=" + explored +
				'}';
	}
}
