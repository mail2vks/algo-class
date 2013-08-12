package org.vivek.algo.wk5;

import com.google.common.collect.ComparisonChain;

/**
 * Created with IntelliJ IDEA.
 * User: vivek_singh
 * Date: 8/12/13
 * Time: 8:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Edge implements Comparable<Edge> {

    private Integer startVertex;
    private Integer endVertex;
    private Integer edgeWeight;

    public Edge(Integer startVertex, Integer endVertex, Integer edgeWeight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeWeight = edgeWeight;
    }

    public Integer getEndVertex() {
        return endVertex;
    }

    public Integer getStartVertex() {
        return startVertex;
    }

    public Integer getEdgeWeight() {

        return edgeWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (!edgeWeight.equals(edge.edgeWeight)) return false;
        if (!endVertex.equals(edge.endVertex)) return false;
        if (!startVertex.equals(edge.startVertex)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startVertex.hashCode();
        result = 31 * result + endVertex.hashCode();
        result = 31 * result + edgeWeight.hashCode();
        return result;
    }

    @Override
    public int compareTo(Edge o) {
        return ComparisonChain.start().compare(startVertex, o.startVertex).compare(endVertex, o.endVertex).compare(edgeWeight, o.getEdgeWeight()).result();
    }

    @Override
    public String toString() {
        return "Edge{" +
                "startVertex=" + startVertex +
                ", endVertex=" + endVertex +
                ", edgeWeight=" + edgeWeight +
                '}';
    }
}
