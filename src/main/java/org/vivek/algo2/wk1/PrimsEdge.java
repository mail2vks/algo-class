package org.vivek.algo2.wk1;


import org.vivek.algo.wk5.Edge;

public class PrimsEdge extends Edge {


    public PrimsEdge(Integer startVertex, Integer endVertex, Integer edgeWeight) {
        super(startVertex, endVertex, edgeWeight);
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.getEdgeWeight(), o.getEdgeWeight());
    }
}
