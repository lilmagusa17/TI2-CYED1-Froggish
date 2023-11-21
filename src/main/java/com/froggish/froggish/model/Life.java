package com.froggish.froggish.model;

import com.froggish.froggish.graph.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Life {
    private Image heart;
    private Position position;
    private Canvas header;
    private GraphicsContext graphicsContext; //contexto grafico

    public Life(Canvas header, Position position) {
        this.position = position;
        this.header = header;
        this.graphicsContext = this.header.getGraphicsContext2D();

        try {
            this.heart = new Image(getClass().getResourceAsStream("/com/bomberman/bomberman/img/btnIcons/heart1.png"), 50, 50, false, false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public void paint(){
        this.graphicsContext.drawImage(heart, this.position.getX(), this.position.getY());
    }

}
