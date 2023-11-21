package com.froggish.froggish.screen;
import com.froggish.froggish.graph.GraphInterface;
import com.froggish.froggish.graph.Position;
import com.froggish.froggish.model.FrogPlayer;
import com.froggish.froggish.model.WaterLily;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameScreen{
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private FrogPlayer frogPlayer;

    private ArrayList<WaterLily> waterLilies;


    public GameScreen(Canvas canvas){//recibe canvas por inyeccion de dependencia (patron de dise;o, asociacion entre el Screen y Canvas)
        this.canvas=canvas;       //Canvas no se inicializa, solo se recibe, para lograr que todos hagan referencia al mismo Canvas
        this.graphicsContext=this.canvas.getGraphicsContext2D();
        this.frogPlayer=new FrogPlayer(this.canvas);

        this.waterLilies = new ArrayList<>();

    }

    public void paint(){

        graphicsContext.setFill(Color.THISTLE);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        frogPlayer.paint();//el avatar se pinta sobre el canvas donde esta el screen

    }

    public void setOnKeyPressed(KeyEvent event) {
        frogPlayer.setOnKeyPressed(event);
    }

    public void setOnKeyReleased(KeyEvent event){
        frogPlayer.setOnKeyReleased(event);
    }

    /*public void createAndVisualizeGraph() {
        int numRows = 10; // Adjust the number of rows as needed
        int numColumns = 15; // Adjust the number of columns as needed

        int startX = 50; // X-coordinate of the starting node
        int startY = 250; // Y-coordinate of the starting node

        int spacingX = 100; // Horizontal spacing between nodes
        int spacingY = 50; // Vertical spacing between nodes

        int homeX = startX + (numColumns - 1) * spacingX; // X-coordinate of the home node
        int homeY = startY; // Y-coordinate of the home node

        // Create and add nodes to the graph
        for (int col = 0; col < numColumns; col++) {
            int numNodesInColumn = (col == 0 || col == numColumns - 1) ? 3 : 4;

            for (int row = 0; row < numNodesInColumn; row++) {
                int x = startX + col * spacingX;
                int y = startY + row * spacingY;
                addWaterLilyNode(x, y); // Add the rock node to the graph
            }
        }

        // Add edges between nodes to represent connections
        connectNodesInColumns(startX, spacingX, numRows, numColumns);

        // Visualize the graph on the canvas
        visualizeGraph();

    }*/


    private void addWaterLilyNode() {
        //FIXME: change positions
        this.waterLilies.add(new WaterLily(new Position(0, 300), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));

        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));

        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));

        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));

        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 0), this.canvas));
        this.waterLilies.add(new WaterLily(new Position(0, 300), this.canvas));

    }

    // Method to connect nodes in columns to represent possible jumps
    private void connectNodesInColumns(int startX, int spacingX, int numRows, int numColumns) {
        // Iterate through each column
        for (int col = 0; col < numColumns - 1; col++) {
            int currentColumnX = startX + col * spacingX;
            int nextColumnX = currentColumnX + spacingX;

            // Connect nodes in the current column to nodes in the next column
            connectNodesInColumn(currentColumnX, nextColumnX, numRows);
        }
    }

    // Method to connect nodes in a single column
    private void connectNodesInColumn(int currentColumnX, int nextColumnX, int numRows) {
        // Connect each node in the current column to the corresponding nodes in the next column
        for (int row = 0; row < numRows; row++) {
            int currentX = currentColumnX;
            int currentY = 250 + row * 50;

            int nextX1 = nextColumnX;
            int nextY1 = 250 + row * 50;

            int nextX2 = nextColumnX;
            int nextY2 = 250 + (row + 1) * 50;

            WaterLily currentLily = getNodeAt(currentX, currentY);
            WaterLily nextLily1 = getNodeAt(nextX1, nextY1);
            WaterLily nextLily2 = getNodeAt(nextX2, nextY2);


        }


    }

    private WaterLily getNodeAt(int x, int y) {
        for (WaterLily waterLily : waterLilies) {
            if (waterLily.getPosition().getX() == x && waterLily.getPosition().getY() == y) {
                return waterLily;
            }
        }
        return null;
    }

    // Method to visualize the graph on the canvas
    private void visualizeGraph() {
        for (WaterLily waterLily : waterLilies) {
            waterLily.paint();

        }

    }

    private void drawGraph(GraphInterface<String> graph, int startX, int startY, int spacingX, int spacingY, int numRows, int numColumns) {
        // Create and add nodes to the graph
        for (int col = 0; col < numColumns; col++) {
            int numNodesInColumn = (col == 0 || col == numColumns - 1) ? 3 : 4;

            for (int row = 0; row < numNodesInColumn; row++) {
                int x = startX + col * spacingX;
                int y = startY + row * spacingY;
                graph.addVertex("(" + x + ", " + y + ")"); // Add the rock node to the graph
            }
        }

        // Add edges between nodes to represent connections
        connectNodesInColumns(startX, spacingX, numRows, numColumns);

        // Visualize the graph on the canvas
        visualizeGraph();
    }


}
