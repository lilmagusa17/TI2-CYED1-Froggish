package com.froggish.froggish.graph;

public class Edge<V> {
    private V destination;
    private int weight;

    public Edge(V destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public V getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
