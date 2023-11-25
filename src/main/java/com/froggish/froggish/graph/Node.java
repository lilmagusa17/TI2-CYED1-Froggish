package com.froggish.froggish.graph;

import java.util.ArrayList;

public class Node<T> {
    private T valor;
    private ArrayList<Node<T>> nodosAdy;
    private int color;
    private Node<T> antecedemte;
    private int tiempo;
    private static final int UNVISITED = 0;
    private static final int VISITED = 1;
    private static final int PROCESSED = 2;

    public Node(T value, ArrayList<Node<T>> adjacentNodes) {
        this.valor = value;
        this.nodosAdy = adjacentNodes;
        color = UNVISITED;
    }
    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public ArrayList<Node<T>> getNodosAdy() {
        return nodosAdy;
    }

    public void setNodosAdy(ArrayList<Node<T>> nodosAdy) {
        this.nodosAdy = nodosAdy;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Node<T> getAntecedemte() {
        return antecedemte;
    }

    public void setAntecedemte(Node<T> antecedemte) {
        this.antecedemte = antecedemte;
    }

    public int getTiempo() {
        return tiempo;
    }
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}

