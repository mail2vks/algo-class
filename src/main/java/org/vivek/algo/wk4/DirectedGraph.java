package org.vivek.algo.wk4;

import com.google.common.collect.SetMultimap;
import com.google.common.collect.TreeMultimap;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class DirectedGraph {

	private static SetMultimap<Integer, Integer> graphAdjList, reversedGraphAjdList;
	private static Logger logger = Logger.getLogger(DirectedGraph.class.getName());
	private static LinkedList<Integer> sccSizeList;
	private static TreeMap<Integer, Node> nodes;
	private static int finishIndex = 0;
	private static Integer leaderIndex = null;

	private static void loadFileToMemory(final String fileName) throws IOException {
		URL filePath = DirectedGraph.class.getClassLoader().getResource(fileName);
		if (filePath == null) {
			System.out.println("Could not locate " + fileName);
			System.out.println("If using main method of this class Unzip SCC.zip to SCC.txt under src/main/resources");
			return;
		}
		BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath.getFile()), Charset.defaultCharset());
		while (fileReader.ready()) {
			String line = fileReader.readLine();
			String[] strings = line.split(" ");
			Integer key = Integer.valueOf(strings[0]);
			Integer value = Integer.valueOf(strings[1]);
			graphAdjList.put(key, value);
			reversedGraphAjdList.put(value, key);
			nodes.put(key, new Node(key));
			nodes.put(value, new Node(value));
		}
		fileReader.close();
		logger.info("Completed File Load: " + fileName);
	}

	private static List<Integer> getTopSCCSizes(final String fileName) {
		reset();
		try {
			loadFileToMemory(fileName);
			sccSizeList.addAll(computeSCC());

		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(sccSizeList, Collections.reverseOrder());
		return sccSizeList.subList(0, 5);
	}

	private static Collection<Integer> computeSCC() {
		computeFinishTimeOnReversedGraph();
		resetAllNodesToUnExplored();
		computeLeadersOnOriginalGraph();
		return computeResults();
	}

	private static void resetAllNodesToUnExplored() {
		for (Node node : nodes.values()) {
			node.setExplored(false);
		}
	}

	private static Collection<Integer> computeResults() {
		Map<Integer, Integer> valueMap = new HashMap();
		for (Node node : nodes.values()) {
			Integer curValue = valueMap.get(node.getLeader()) != null ? valueMap.get(node.getLeader()) : 0;
			valueMap.put(node.getLeader(), ++curValue);
		}
		return valueMap.values();
	}

	private static void computeLeadersOnOriginalGraph() {
		List nodeList = new ArrayList(nodes.values());
		Collections.sort(nodeList, NodeComparators.getFinishIndexComparatorDecending());
		//logger.info("Node List:" + nodeList);
		dfsLoop(graphAdjList, nodeList);
		//logger.info("Node Map:" + nodes);
	}

	private static void computeFinishTimeOnReversedGraph() {
		List nodeList = new ArrayList(nodes.values());
		Collections.sort(nodeList, Collections.reverseOrder());
		//logger.info("Node List:" + nodeList);
		dfsLoop(reversedGraphAjdList, nodeList);
		//logger.info("Node Map:" + nodes);
	}

	private static void dfsLoop(final SetMultimap<Integer, Integer> graph, final List<Node> nodes) {
		//logger.info(graph.toString());
		finishIndex = 0;
		leaderIndex = null;
		for (Node node : nodes) {
			if (!node.isExplored()) {
				leaderIndex = node.getNodeNumber();
				DFS(graph, node);
			}
		}
	}

	private static void DFS(final SetMultimap<Integer, Integer> graph, final Node node) {
		node.setExplored(true);
		node.setLeader(leaderIndex);
		Set<Integer> edgesFromNode = graph.get(node.getNodeNumber());
		for (Integer edgeToVertex : edgesFromNode) {
			Node edgeToVertexNode = nodes.get(edgeToVertex);
			if (!edgeToVertexNode.isExplored()) {
				DFS(graph, edgeToVertexNode);
			}
		}
		node.setFinishIndex(finishIndex++);
	}

	private static void reset() {
		graphAdjList = TreeMultimap.create();
		reversedGraphAjdList = TreeMultimap.create();
		nodes = new TreeMap<>();
		sccSizeList = new LinkedList<>();
		for (int i = 0; i < 5; i++) {
			sccSizeList.add(0);
		}
	}

	// Run with -Xss8m -XX:+UseSerialGC
	// Unzip SCC.zip to SCC.txt under src/main/resources
	public static void main(String args[]) {
		new DirectedGraph().getFinalString("SCC.txt");
	}

	public String getFinalString(String fileName) {
		logger.info("Starting search for Strongly connected components");
		List<Integer> result = getTopSCCSizes(fileName);
		StringBuilder builder = new StringBuilder();
		for (Integer integer : result) {
			builder.append(integer);
			builder.append(',');
		}
		builder.deleteCharAt(builder.lastIndexOf(","));
		logger.info("Final Result: " + builder.toString());
		return builder.toString();
	}
}


