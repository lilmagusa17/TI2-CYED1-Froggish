package com.froggish.froggish.screen;
import com.froggish.froggish.graph.GraphAdjacencyList;
import com.froggish.froggish.graph.Node;
import com.froggish.froggish.graph.Position;
import com.froggish.froggish.model.FrogPlayer;
import com.froggish.froggish.model.WaterLily;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameScreen{
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private FrogPlayer frogPlayer;

    private GraphAdjacencyList<Position> graph;

    private ArrayList<WaterLily> waterLilies;


    public GameScreen(Canvas canvas, GraphAdjacencyList<Position> graph){//recibe canvas por inyeccion de dependencia (patron de dise;o, asociacion entre el Screen y Canvas)
        this.canvas=canvas;       //Canvas no se inicializa, solo se recibe, para lograr que todos hagan referencia al mismo Canvas
        this.graphicsContext=this.canvas.getGraphicsContext2D();
        //this.frogPlayer=new FrogPlayer(this.canvas);

        Node<Position> startNode = graph.getNodes().get(0);

        // Create a starting position based on the first node's position
        Position startPosition = startNode.getValor();

        // Create the FrogPlayer with the starting position
        this.frogPlayer = new FrogPlayer(canvas, startPosition);

        this.graph = graph;

        this.waterLilies = new ArrayList<>();

    }

    public void paint(){

        graphicsContext.setFill(Color.THISTLE);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        drawGraph();



        frogPlayer.paint();//el avatar se pinta sobre el canvas donde esta el screen

    }

    /*public void setOnKeyPressed(KeyEvent event) {
        frogPlayer.setOnKeyPressed(event);
    }*/

    public void setOnKeyPressed(KeyEvent event) {
        frogPlayer.setOnKeyPressed(event);
        checkAdjacentNodes(event.getCode());
    }

    public void setOnKeyReleased(KeyEvent event) {
        frogPlayer.setOnKeyReleased(event);
    }

    public void update() {
        frogPlayer.onMove();
        checkFrogWaterLilyCollision();
    }

    private void drawGraph() {
        ArrayList<Node<Position>> nodes = graph.getNodes();
        drawNodes(nodes);
    }

    private void drawNodes(ArrayList<Node<Position>> nodes) {
        GraphicsContext gc = getGraphicsContext();

        Image waterLily = new Image(getClass().getResourceAsStream("/com/froggish/froggish/img/elements/WaterLilySprite.png"), 40, 40, false, false);

        for (Node<Position> node : nodes) {
            Position nodePosition = node.getValor();
            gc.drawImage(waterLily, nodePosition.getX() - 12, nodePosition.getY() - 12);
        }
    }

    private GraphicsContext getGraphicsContext() {
        return this.canvas.getGraphicsContext2D();
    }

    public void checkFrogWaterLilyCollision() {
        for (WaterLily waterLily : waterLilies) {
            if (frogPlayer.checkWaterLilyCollision(waterLily)) {
                // Handle collision logic (e.g., increase score, remove water lily, etc.)
            }
        }
    }

    public void checkAdjacentNodes(KeyCode key) {
        ArrayList<Node<Position>> vertices = graph.getVertices();
        Node<Position> currentPlayerNode = graph.searchNode(frogPlayer.getPosition());

        if (currentPlayerNode != null) {
            switch (key) {
                case UP:
                    // Check the node above
                    Node<Position> nextNodeUp = findNodeInColumn(currentPlayerNode, vertices, -1);
                    if (nextNodeUp != null) {
                        // Handle the movement or any other action
                        // For example, you can update the frog player's position
                        frogPlayer.getPosition().setY(nextNodeUp.getValor().getY());
                    }
                    break;

                case DOWN:
                    // Check the node below
                    Node<Position> nextNodeDown = findNodeInColumn(currentPlayerNode, vertices, 1);
                    if (nextNodeDown != null) {
                        // Handle the movement or any other action
                        frogPlayer.getPosition().setY(nextNodeDown.getValor().getY());
                    }
                    break;

                case RIGHT:
                    // Check the node to the right
                    Node<Position> nextNodeRight = findNodeInRow(currentPlayerNode, vertices, 1);
                    if (nextNodeRight != null) {
                        // Handle the movement or any other action
                        frogPlayer.getPosition().setX(nextNodeRight.getValor().getX());
                    }
                    break;

                case LEFT:
                    // Check the node to the left
                    Node<Position> nextNodeLeft = findNodeInRow(currentPlayerNode, vertices, -1);
                    if (nextNodeLeft != null) {
                        // Handle the movement or any other action
                        frogPlayer.getPosition().setX(nextNodeLeft.getValor().getX());
                    }
                    break;
            }
        }
    }

    private Node<Position> findNodeInColumn(Node<Position> currentNode, ArrayList<Node<Position>> vertices, int offset) {
        Position currentPosition = currentNode.getValor();
        for (Node<Position> vertex : vertices) {
            if (vertex.getValor().getX() == currentPosition.getX() && vertex.getValor().getY() == currentPosition.getY() + offset) {
                return vertex;
            }
        }
        return null;
    }

    private Node<Position> findNodeInRow(Node<Position> currentNode, ArrayList<Node<Position>> vertices, int offset) {
        Position currentPosition = currentNode.getValor();
        for (Node<Position> vertex : vertices) {
            if (vertex.getValor().getY() == currentPosition.getY() && vertex.getValor().getX() == currentPosition.getX() + offset) {
                return vertex;
            }
        }
        return null;
    }

    public void drawAvailablePaths(Node<Position> currentNode) {
        GraphicsContext gc = getGraphicsContext();
        gc.setStroke(Color.GREEN);

        Position currentPosition = currentNode.getValor();

        for (Node<Position> adjacentNode : currentNode.getNodosAdy()) {
            Position adjacentPosition = adjacentNode.getValor();

            // Draw arrows or lines from current node to adjacent nodes
            gc.strokeLine(currentPosition.getX(), currentPosition.getY(), adjacentPosition.getX(), adjacentPosition.getY());
        }
    }


    public FrogPlayer getFrogPlayer() {
        return this.frogPlayer;
    }

    public void setFrogPlayer(FrogPlayer frogPlayer) {
        this.frogPlayer = frogPlayer;
    }

    public void addWaterLily(WaterLily waterLily) {
        this.waterLilies.add(waterLily);
    }

    public void removeWaterLily(WaterLily waterLily) {
        this.waterLilies.remove(waterLily);
    }

    public void clearWaterLilies() {
        this.waterLilies.clear();
    }

    public ArrayList<WaterLily> getWaterLilies() {
        return this.waterLilies;
    }

    public void setWaterLilies(ArrayList<WaterLily> waterLilies) {
        this.waterLilies = waterLilies;
    }

    public GraphAdjacencyList<Position> getGraph() {
        return this.graph;
    }

    public void setGraph(GraphAdjacencyList<Position> graph) {
        this.graph = graph;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }




}
