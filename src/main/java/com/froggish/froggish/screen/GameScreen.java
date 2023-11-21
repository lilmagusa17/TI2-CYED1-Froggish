package com.froggish.froggish.screen;
import com.froggish.froggish.graph.GraphAdjacencyList;
import com.froggish.froggish.graph.GraphInterface;
import com.froggish.froggish.graph.Node;
import com.froggish.froggish.graph.Position;
import com.froggish.froggish.model.FrogPlayer;
import com.froggish.froggish.model.WaterLily;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
        this.frogPlayer=new FrogPlayer(this.canvas);

        this.graph = graph;

        this.waterLilies = new ArrayList<>();

    }

    public void paint(){

        graphicsContext.setFill(Color.THISTLE);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        drawGraph();
        frogPlayer.paint();//el avatar se pinta sobre el canvas donde esta el screen



    }

    public void setOnKeyPressed(KeyEvent event) {
        frogPlayer.setOnKeyPressed(event);
    }

    public void setOnKeyReleased(KeyEvent event){
        frogPlayer.setOnKeyReleased(event);
    }

    /*private void drawGraph() {
        ArrayList<Node<Position>> nodes = graph.getNodes();
        for (Node<Position> node : nodes) {
            drawNode(node.getValor());
            for (Node<Position> adjacentNode : node.getNodosAdy()) {
                drawPath(node.getValor(), adjacentNode.getValor());
            }
        }
    }*/

    private void drawGraph() {
        if (graph == null) {
            System.out.println("Graph is null");
            return;
        }

        ArrayList<Node<Position>> nodes = graph.getNodes();
        if (nodes == null) {
            System.out.println("Nodes list is null");
            return;
        }

        for (Node<Position> node : nodes) {
            if (node == null || node.getValor() == null) {
                System.out.println("Node or its value is null");
                continue;
            }

            drawNode(node.getValor());

            ArrayList<Node<Position>> adjacentNodes = node.getNodosAdy();
            if (adjacentNodes != null) {
                for (Node<Position> adjacentNode : adjacentNodes) {
                    if (adjacentNode != null && adjacentNode.getValor() != null) {
                        drawPath(node.getValor(), adjacentNode.getValor());
                    } else {
                        System.out.println("Adjacent node or its value is null");
                    }
                }
            }
        }
    }


    private void drawNode(Position position) {
        GraphicsContext gc = getGraphicsContext();

        // Load water lily sprite
        Image waterLily = new Image(getClass().getResourceAsStream("/com/froggish/froggish/img/elements/WaterLilySprite.png"), 60, 60, false, false);

        // Draw water lily sprite
        gc.drawImage(waterLily, position.getX() - 30 / 2, position.getY() - 30 / 2);
    }


    private GraphicsContext getGraphicsContext() {
        return this.canvas.getGraphicsContext2D();
    }

    private void drawPath(Position start, Position end) {
        GraphicsContext gc = getGraphicsContext();

        // Load water lily sprite for the path
        Image waterLilyPath = new Image(getClass().getResourceAsStream("/com/froggish/froggish/img/elements/WaterLilySprite.png"), 40, 40, false, false);

        // Draw water lily sprite for the path
        gc.drawImage(waterLilyPath, start.getX() - 30 / 2, start.getY() - 30 / 2);
        gc.drawImage(waterLilyPath, end.getX() - 30 / 2, end.getY() - 30 / 2);

        // Draw a line connecting the nodes
        gc.setStroke(Color.GRAY);
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

}
