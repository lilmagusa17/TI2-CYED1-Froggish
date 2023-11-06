package com.froggish.froggish.graph;

public interface GraphInterface<T>{
    public void addVertex(T element);
    public void addEdge(T elementA, T elementB);
    public boolean isStronglyConnected();
    public int DFS();
    public boolean BFS(T element);
    public void removeEdge(T elementA, T elementB);
    public void removeVertex(T element);
    public int[][] dijkstra(T element);
    public int[][] floydWarshall();

}
