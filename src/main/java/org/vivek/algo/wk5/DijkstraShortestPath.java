package org.vivek.algo.wk5;

import com.google.common.collect.SetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

public class DijkstraShortestPath {

    private static final Logger logger = Logger.getLogger(DijkstraShortestPath.class.getName());
    private static SetMultimap<Integer, Edge> graphAdjList;
    private boolean initialized = false;

    public DijkstraShortestPath(String fileName) throws IOException {
        initialize(fileName);
    }

    private static void loadFileToMemory(final String fileName) throws IOException {
        URL filePath = DijkstraShortestPath.class.getClassLoader().getResource(fileName);
        if (filePath == null) {
            System.out.println("Could not locate " + fileName);
            return;
        }
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath.getFile()), Charset.defaultCharset());
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] strings = line.split(" ");
            Integer key = Integer.valueOf(strings[0]);
            for (int index = 1; index < strings.length; index++) {
                String edge = strings[index];
                String[] edgeData = edge.split(",");
                graphAdjList.put(key, new Edge(key, Integer.valueOf(edgeData[0]), Integer.valueOf(edgeData[1])));
            }
        }
        fileReader.close();
        logger.info("Completed File Load: " + fileName);
    }

    public static void main(String args[]) throws IOException {
        DijkstraShortestPath instance = new DijkstraShortestPath("dijkstraData.txt");
        Map<Integer, Integer> computedPaths = instance.computeShortestPath(1);
        logger.info(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                computedPaths.get(7), computedPaths.get(37), computedPaths.get(59), computedPaths.get(82),
                computedPaths.get(99), computedPaths.get(115), computedPaths.get(133), computedPaths.get(165),
                computedPaths.get(188), computedPaths.get(197)));    //7,37,59,82,99,115,133,165,188,197
    }

    public Map<Integer, Integer> computeShortestPath(final Integer startVertex) {
        Set<Integer> visitedVertices = new HashSet<>();
        Map<Integer, Integer> distanceMap = new TreeMap<>();
        visitedVertices.add(startVertex);
        distanceMap.put(startVertex, 0);

        while (visitedVertices.size() != graphAdjList.keySet().size()) {
            // Look at all edges from node starting from those in visitedVertices
            Set<Edge> edgeSet = new HashSet<>();
            for (Integer vertex : visitedVertices) {
                edgeSet.addAll(graphAdjList.get(vertex));
            }

            // Select an edge from visited vertices to unvisited vertices
            // that has minimum weight
            Edge selectedEdge = null;
            Integer minDistance = Integer.MAX_VALUE;
            for (Edge edge : edgeSet) {
                if (visitedVertices.contains(edge.getStartVertex()) && !visitedVertices.contains(edge.getEndVertex())) {
                    Integer distance = distanceMap.get(edge.getStartVertex()) + edge.getEdgeWeight();
                    if (distance < minDistance) {
                        selectedEdge = edge;
                        minDistance = distance;
                    }
                }
            }
            visitedVertices.add(selectedEdge.getEndVertex());
            distanceMap.put(selectedEdge.getEndVertex(), minDistance);
        }
        return distanceMap;
    }

    private void initialize(String fileName) throws IOException {
        if (!initialized && StringUtils.isNotBlank(fileName)) {
            graphAdjList = TreeMultimap.create();
            loadFileToMemory(fileName);
            initialized = true;
        } else {
            logger.info("Failed to initialize");
        }
    }
}
