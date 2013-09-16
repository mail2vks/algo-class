package org.vivek.algo2.wk1;

import com.google.common.collect.TreeMultimap;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

public class PrimsMSTCalculator {

    private static final Logger logger = Logger.getLogger(PrimsMSTCalculator.class.getName());
    private static TreeMultimap<Integer, PrimsEdge> graphAdjList;
    private PriorityQueue<PrimsEdge> heap;

    public PrimsMSTCalculator(String fileName) throws IOException {
        initialize(fileName);
    }

    private void loadFileToMemory(final String fileName) throws IOException {
        URL filePath = PrimsMSTCalculator.class.getClassLoader().getResource(fileName);
        if (filePath == null) {
            System.out.println("Could not locate " + fileName);
            return;
        }
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath.getFile()), Charset.defaultCharset());
        if (fileReader.ready()) {
            String[] strings = fileReader.readLine().split(" ");
            logger.info("Number of vertices : " + strings[0]);
            logger.info("Number of edges : " + strings[1]);
        }

        while (fileReader.ready()) {
            String line = fileReader.readLine();
            String[] strings = line.split(" ");
            graphAdjList.put(Integer.valueOf(strings[0]), new PrimsEdge(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]), Integer.valueOf(strings[2])));
            graphAdjList.put(Integer.valueOf(strings[1]), new PrimsEdge(Integer.valueOf(strings[1]), Integer.valueOf(strings[0]), Integer.valueOf(strings[2])));
        }
        fileReader.close();
        //logger.info(graphAdjList.toString());
        logger.info("Completed File Load: " + fileName);
    }

    private void initialize(String fileName) throws IOException {
        graphAdjList = TreeMultimap.create();
        loadFileToMemory(fileName);
    }

    public long returnMSTWeight() {
        // Pick a random node
        Random random = new Random();
        int currentVertex = random.nextInt(graphAdjList.keySet().size() + 1);
        if (currentVertex == 0)
            currentVertex = 1;

        long weight = 0L;
        Set<Integer> visitedVertices = new HashSet<>();
        visitedVertices.add(currentVertex);

        while (visitedVertices.size() != graphAdjList.keySet().size()) {

            // Rebuild heap
            heap = new PriorityQueue<>();
            Iterator<PrimsEdge> graphItr = graphAdjList.values().iterator();
            while (graphItr.hasNext()) {
                PrimsEdge edge = graphItr.next();
                if (visitedVertices.contains(edge.getStartVertex()) && !visitedVertices.contains(edge.getEndVertex())) {
                    heap.add(edge);
                }
            }

            PrimsEdge minWeightEdge = heap.poll();

            weight += minWeightEdge.getEdgeWeight();
            //logger.info("Edge considered is : " + minWeightEdge.toString() + ", taking weight to " + weight);
            visitedVertices.add(minWeightEdge.getStartVertex());
            visitedVertices.add(minWeightEdge.getEndVertex());
            //logger.info("Heap is " + heap.toString());
        }
        logger.info("Final weight is " + weight);
        return weight;
    }

    public static void main(String[] args) throws IOException {
        new PrimsMSTCalculator("prims-edges.txt").returnMSTWeight();
    }
}
