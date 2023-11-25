package com.froggish.froggish.graph;

public class Vertex<V> {
    private V vertex;
    private int distance;

    public Vertex(V vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public V getVertex() {
        return vertex;
    }

    public int getDistance() {
        return distance;
    }
}
