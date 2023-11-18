package com.froggish.froggish.screen;

import com.froggish.froggish.frog.FrogPlayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Level1 {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private FrogPlayer bomberman;

    public Level1(Canvas canvas){//recibe canvas por inyeccion de dependencia (patron de dise;o, asociacion entre el Screen y Canvas)
        this.canvas=canvas;       //Canvas no se inicializa, solo se recibe, para lograr que todos hagan referencia al mismo Canvas
        this.graphicsContext=this.canvas.getGraphicsContext2D();
        this.bomberman=new FrogPlayer(this.canvas);

        //TODO aqui abajo iria todo eso que Nicolas tiene en su repo de IDistance y demas
    }

    public void paint(){

        graphicsContext.setFill(Color.LAVENDER);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        bomberman.paint();//el avatar se pinta sobre el canvas donde esta el screen

    }

    public void setOnKeyPressed(KeyEvent event) {
        bomberman.setOnKeyPressed(event);
    }

    public void setOnKeyReleased(KeyEvent event){
        bomberman.setOnKeyReleased(event);
    }
}
