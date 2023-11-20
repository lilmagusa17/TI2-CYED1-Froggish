package com.froggish.froggish.model;

import com.froggish.froggish.graph.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class WaterLily {

    private Image lily;
    private Position position;
    private Canvas canvas;
    private GraphicsContext graphicsContext; //contexto grafico

    private List<WaterLily> adjacentNodes;

    private int size;

    private boolean isActive;

    public WaterLily(Position position, Canvas canvas){

        this.canvas=canvas;
        this.graphicsContext=this.canvas.getGraphicsContext2D();
        this.position=position;
        this.size=60;
        this.isActive=true;

        this.lily =new Image(getClass().getResourceAsStream("/com/froggish/froggish/img/elements/WaterLilySprite.png"), 60, 60, false, false);

        this.adjacentNodes = new ArrayList<>();
    }

    public void paint(){
        if(this.isActive){
            this.graphicsContext.drawImage(this.lily, this.position.getX(), this.position.getY());
        }
    }

    public int getSize(){
        return this.size;
    }

    public Position getPosition(){
        return this.position;
    }

    public void addAdjacentNode(WaterLily node) {
        this.adjacentNodes.add(node);
    }

    public List<WaterLily> getAdjacentNodes() {
        return this.adjacentNodes;
    }
}
