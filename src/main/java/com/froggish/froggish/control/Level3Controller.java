package com.froggish.froggish.control;

import com.froggish.froggish.graph.GraphInterface;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Level3Controller {

    @FXML
    public Canvas canvas;

    public GraphicsContext gc;

    public GraphInterface<Integer> graph;

    public Level3Controller() {
        this.gc = canvas.getGraphicsContext2D();

    }

    @FXML
    public void initialize() {
        //System.out.println("Third level view initialized");
    }

    public void paint() {
        System.out.println("Painting");




    }
}
