package com.froggish.froggish.model;

import com.froggish.froggish.graph.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WaterLily {
    private Image brick;
    private Position position;
    private Canvas canvas;
    private GraphicsContext graphicsContext; //contexto grafico

    private int size;

    private boolean isActive;

    public WaterLily(Position position, Canvas canvas){

        this.canvas=canvas;
        this.graphicsContext=this.canvas.getGraphicsContext2D();
        this.position=position;
        this.size=60;
        this.isActive=true;

        this.brick =new Image(getClass().getResourceAsStream("/com/froggish/froggish/img/elements/WaterLilySprite.png"), 60, 60, false, false);
    }

    public void paint(){
        if(this.isActive){
            this.graphicsContext.drawImage(this.brick, this.position.getX(), this.position.getY());
        }
    }

    public int getSize(){
        return this.size;
    }

    public Position getPosition(){
        return this.position;
    }
}
