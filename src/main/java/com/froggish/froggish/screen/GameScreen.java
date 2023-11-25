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

    public void setOnKeyReleased(KeyEvent event) {
        frogPlayer.setOnKeyReleased(event);
    }

    public void update() {
        frogPlayer.onMove();
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



}
