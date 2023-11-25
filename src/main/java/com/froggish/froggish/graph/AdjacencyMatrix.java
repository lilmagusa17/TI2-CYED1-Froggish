package com.froggish.froggish.graph;

import java.util.*;

public class AdjacencyMatrix<V> {
    private Map<V, Integer> vertexIndexMap;
    private List<V> vertices;
    private Edge<V>[][] adjacencyMatrix;

    public AdjacencyMatrix() {
        vertexIndexMap = new HashMap<>();
        vertices = new ArrayList<>();
        adjacencyMatrix = new Edge[0][0];
    }

    public void addVertex(V vertex) {
        if (!vertexIndexMap.containsKey(vertex)) {
            int newIndex = vertices.size();
            vertexIndexMap.put(vertex, newIndex);
            vertices.add(vertex);
            updateMatrix();
        }
    }

    public void addEdge(V from, V to) {
        if (vertexIndexMap.containsKey(from) && vertexIndexMap.containsKey(to)) {
            int indexFrom = vertexIndexMap.get(from);
            int indexTo = vertexIndexMap.get(to);
            int weight = new Random().nextInt(10) + 1;
            adjacencyMatrix[indexFrom][indexTo] = new Edge<>(to, weight);
        } else {
            System.out.println("Al menos uno de los vértices no está en el mapa.");
        }
    }

    public List<String> dfsWithNeighbors(V start) {
        boolean[] visited = new boolean[vertices.size()];
        List<String> result = new ArrayList<>();
        dfsRecursiveWithNeighbors(vertexIndexMap.get(start), visited, result);
        return result;
    }

    private void dfsRecursiveWithNeighbors(int start, boolean[] visited, List<String> result) {
        result.add(vertices.get(start).toString());
        visited[start] = true;

        for (int i = 0; i < vertices.size(); i++) {
            Edge<V> edge = adjacencyMatrix[start][i];
            if (edge != null) {
                int neighborIndex = vertexIndexMap.get(edge.getDestination());
                if (!visited[neighborIndex]) {
                    result.add(vertices.get(neighborIndex).toString());
                    dfsRecursiveWithNeighbors(neighborIndex, visited, result);
                }
            }
        }
    }

    public Map<V, Integer> dijkstra(V start) {
        int startIndex = vertexIndexMap.get(start);
        Map<V, Integer> distances = new HashMap<>();
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.getDistance()));

        // Initialize distances
        for (V vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        pq.offer(new Vertex<>(start, 0));

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            V currentVertex = current.getVertex();
            int currentDistance = current.getDistance();

            if (currentDistance > distances.get(currentVertex)) {
                continue;  // Skip if a shorter distance is already known
            }

            for (int i = 0; i < vertices.size(); i++) {
                Edge<V> edge = adjacencyMatrix[vertexIndexMap.get(currentVertex)][i];
                if (edge != null) {
                    int newDistance = currentDistance + edge.getWeight();
                    V neighbor = edge.getDestination();

                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        pq.offer(new Vertex<>(neighbor, newDistance));
                    }
                }
            }
        }

        return distances;
    }

    private void updateMatrix() {
        int size = vertices.size();
        Edge<V>[][] newMatrix = new Edge[size][size];
        for (int i = 0; i < size - 1; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, size - 1);
        }
        adjacencyMatrix = newMatrix;
    }
}
