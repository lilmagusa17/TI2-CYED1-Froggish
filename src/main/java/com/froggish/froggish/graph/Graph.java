package com.froggish.froggish.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//TODO: Test missing
public class Graph<T> implements GraphInterface<T> {
    private ArrayList<Node<T>> listaAyacencia;
    int tiempo;

    public Graph() {
        listaAyacencia = new ArrayList<>();
    }

    @Override
    public void addVertex(T element) {
        listaAyacencia.add(new Node<>(element, new ArrayList<>()));
    }
    @Override
    public void addEdge(T elementA, T elementB) {
        for (Node<T> g: listaAyacencia) {
            if(g.getValor().equals(elementA)) {
                for (Node<T> f: listaAyacencia) {
                    if(f.getValor().equals(elementB)){
                        g.getNodosAdy().add(f);
                    }
                }

            }
        }
    }

    @Override
    public void removeEdge(T elementA, T elementB) {
        for (Node<T> g: listaAyacencia) {
            if(g.getValor().equals(elementA)) {
                for (Node<T> f: listaAyacencia) {
                    if(f.getValor().equals(elementB)){
                        g.getNodosAdy().remove(f);
                    }
                }

            }
        }
    }


    @Override
    public void removeVertex(T element) {
        for (Node<T> g: listaAyacencia) {
            if(g.getValor().equals(element)) {
                listaAyacencia.remove(g);
            }
        }
    }

    @Override
    public int DFS(){
        for (Node<T> graphNode: listaAyacencia) {
            graphNode.setAntecedemte(null);
            graphNode.setColor(0);
            graphNode.setTiempo(0);
        }
        int trees = 0;
        int time = 0;
        for (Node<T> graphNode: listaAyacencia) {
            if(graphNode.getColor() == 0) {
                trees++;
                DFSVisit(graphNode);
            }
        }
        return trees;
    }
    public void DFSVisit(Node<T> graphNode){
        tiempo++;
        graphNode.setTiempo(tiempo);
        graphNode.setColor(1);
        for (Node<T> adjacencyNode: graphNode.getNodosAdy()) {
            if(adjacencyNode.getColor() == 0) {
                adjacencyNode.setAntecedemte(graphNode);
                DFSVisit(adjacencyNode);
            }
        }
        graphNode.setColor(2);
        tiempo++;
        graphNode.setTiempo(tiempo);
    }

    public Node<T> searchNode(T element) {
        for (Node<T> g: listaAyacencia) {
            if(g.getValor().equals(element)){
                return g;
            }
        }
        return null;
    }
    @Override
    public boolean BFS(T element) {
        Node <T> graphNode = searchNode(element);
        for (Node<T> adjacentNode: listaAyacencia) {
            adjacentNode.setColor(0);
            adjacentNode.setTiempo(Integer.MAX_VALUE);
            adjacentNode.setAntecedemte(null);
        }
        graphNode.setColor(1);
        graphNode.setTiempo(0);
        graphNode.setAntecedemte(null);
        Queue<Node<T>> graphNodes = new LinkedList<>();
        graphNodes.add(graphNode);
        while (!graphNodes.isEmpty()){
            Node<T> u = graphNodes.poll();
            for (Node<T> dequeue: u.getNodosAdy()) {
                if(dequeue.getColor() == 0) {
                    dequeue.setColor(1);
                    dequeue.setTiempo(dequeue.getTiempo()+1);
                    dequeue.setAntecedemte(u);
                    graphNodes.add(dequeue);
                }
            }
            u.setColor(3);
        }
        for (Node<T> g: listaAyacencia) {
            if(g.getColor() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int[][] dijkstra(T element) {
        Node <T> graphNode = searchNode(element);
        for (Node<T> adjacentNode: listaAyacencia) {
            adjacentNode.setColor(0);
            adjacentNode.setTiempo(Integer.MAX_VALUE);
            adjacentNode.setAntecedemte(null);
        }
        graphNode.setColor(1);
        graphNode.setTiempo(0);
        graphNode.setAntecedemte(null);
        Queue<Node<T>> graphNodes = new LinkedList<>();
        graphNodes.add(graphNode);
        while (!graphNodes.isEmpty()){
            Node<T> u = graphNodes.poll();
            for (Node<T> dequeue: u.getNodosAdy()) {
                if(dequeue.getColor() == 0) {
                    dequeue.setColor(1);
                    dequeue.setTiempo(dequeue.getTiempo()+1);
                    dequeue.setAntecedemte(u);
                    graphNodes.add(dequeue);
                }
            }
            u.setColor(3);
        }
        for (Node<T> g: listaAyacencia) {
            if(g.getColor() == 0) {
                return null;
            }
        }
        int[][] matrix = new int[listaAyacencia.size()][listaAyacencia.size()];
        for (int i = 0; i < listaAyacencia.size(); i++) {
            for (int j = 0; j < listaAyacencia.size(); j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i < listaAyacencia.size(); i++) {
            Node<T> node = listaAyacencia.get(i);
            for (Node<T> adjacentNode : node.getNodosAdy()) {
                int j = listaAyacencia.indexOf(adjacentNode);
                matrix[i][j] = 1;
            }
        }
        return matrix;
    }

    //TODO: Should we add flyodWarshall? And Prim and Kruskal?
    @Override
    public int[][] floydWarshall() {

        int size = listaAyacencia.size();

        int[][] adjacencyMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            Node<T> node = listaAyacencia.get(i);

            for (Node<T> adjacentNode : node.getNodosAdy()) {
                int j = listaAyacencia.indexOf(adjacentNode);
                adjacencyMatrix[i][j] = 1;
            }
        }

        for (int k = 0; k < size; k++) {

            for (int i = 0; i < size; i++) {

                if (adjacencyMatrix[i][k] == 1) {

                    for (int j = 0; j < size; j++) {

                        if (adjacencyMatrix[k][j] == 1) {
                            adjacencyMatrix[i][j] = 1;
                        }
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    //prim
    public void prim(){

        for (Node<T> node: listaAyacencia) {

            node.setColor(0);
            node.setTiempo(Integer.MAX_VALUE);
            node.setAntecedemte(null);
        }

        Node<T> u = listaAyacencia.get(0);
        u.setTiempo(0);
        u.setAntecedemte(null);
        u.setColor(1);
        Queue<Node<T>> graphNodes = new LinkedList<>();
        graphNodes.add(u);

        while (!graphNodes.isEmpty()){
            Node<T> v = graphNodes.poll();

            for (Node<T> adjacentNode: v.getNodosAdy()) {

                if(adjacentNode.getColor() == 0) {
                    adjacentNode.setColor(1);
                    adjacentNode.setTiempo(adjacentNode.getTiempo()+1);
                    adjacentNode.setAntecedemte(v);
                    graphNodes.add(adjacentNode);
                }
            }

            v.setColor(3);
        }

    }

    //kruskal
    public void kruskal(){

        for (Node<T> node: listaAyacencia) {

            node.setColor(0);
            node.setTiempo(Integer.MAX_VALUE);
            node.setAntecedemte(null);
        }

        Node<T> u = listaAyacencia.get(0);
        u.setTiempo(0);
        u.setAntecedemte(null);
        u.setColor(1);
        Queue<Node<T>> graphNodes = new LinkedList<>();
        graphNodes.add(u);

        while (!graphNodes.isEmpty()){
            Node<T> v = graphNodes.poll();

            for (Node<T> adjacentNode: v.getNodosAdy()) {

                if(adjacentNode.getColor() == 0) {
                    adjacentNode.setColor(1);
                    adjacentNode.setTiempo(adjacentNode.getTiempo()+1);
                    adjacentNode.setAntecedemte(v);
                    graphNodes.add(adjacentNode);
                }
            }

            v.setColor(3);
        }

    }




    @Override
    public boolean isStronglyConnected() {
        for (Node<T> node: listaAyacencia) {
            if (!BFS(node.getValor())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String graph = "";
        for (Node<T> node: listaAyacencia) {
            graph += node.getValor() + ": ";
            for (Node<T> adjacentNode: node.getNodosAdy()) {
                graph += adjacentNode.getValor() + " ";
            }
            graph += "\n";
        }
        return graph;
    }

    public T toStringAsMatrix() {
        int size = listaAyacencia.size();

        int[][] adjacencyMatrix = new int[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }

        for (int i = 0; i < size; i++) {
            Node<T> node = listaAyacencia.get(i);
            for (Node<T> adjacentNode : node.getNodosAdy()) {
                int j = listaAyacencia.indexOf(adjacentNode);
                adjacencyMatrix[i][j] = 1;
            }
        }

        StringBuilder graph = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph.append(adjacencyMatrix[i][j]).append(" ");
            }
            graph.append("\n");
        }

        return (T) graph.toString();
    }
    


}

