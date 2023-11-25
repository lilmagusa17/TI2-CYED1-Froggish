package com.froggish.froggish.control;

import com.froggish.froggish.graph.GraphAdjacencyList;
import com.froggish.froggish.graph.Node;
import com.froggish.froggish.graph.Position;
import com.froggish.froggish.screen.GameScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Label label;

    private GraphicsContext graphicsContext;

    private GameScreen screenA;

    private GraphAdjacencyList<Position> graph;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.graph = createMatrixGraph();

        this.screenA = new GameScreen(this.canvas, graph);
        this.screenA.paint();

        this.canvas.requestFocus();

        // Get the list of vertices from the graph
        ArrayList<Node<Position>> vertices = this.graph.getVertices();

        // Set the initial position of the frog player to the first vertex
        if (!vertices.isEmpty()) {
            Position initialPosition = vertices.get(0).getValor();
            this.screenA.getFrogPlayer().getPosition().setX(initialPosition.getX());
            this.screenA.getFrogPlayer().getPosition().setY(initialPosition.getY());
        }

        this.label = new Label();



        //suscribe el canvas a las acciones del teclado
        //el screen le pasa el movimiento al avatar
        //hace que el avatar se mueva
        initActions();
        setFont();


        //hilo recibe un runnable y ese hilo por debajo llama al hilo de la interfaz grafica
        new Thread(
                ()->{
                    while (true) {//true para que siempre este corriendo, hacerlo atributo que cambie cuando se cierra la ventana
                        Platform.runLater(this::paint);

                        try {

                            Thread.sleep(120);

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    public void initActions(){
        this.canvas.setOnKeyPressed(event -> {
            // System.out.println("Key Pressed: " + event.getCode());
            this.screenA.setOnKeyPressed(event);
            this.screenA.update(); // Update the player's state immediately
        });

        this.canvas.setOnKeyReleased(event -> {
            // System.out.println("Key Released: " + event.getCode());
            this.screenA.setOnKeyReleased(event);
            this.screenA.update(); // Update the player's state immediately
        });
    }

    public void paint(){
        screenA.paint();
    }

    private GraphAdjacencyList<Position> createMatrixGraph() {
        GraphAdjacencyList<Position> graph = new GraphAdjacencyList<>();

        int numRows = 5;
        int numCols = 10;
        int nodeSpacing = 90;

        // Add nodes
        for (int row = 1; row <= numRows; row++) {
            for (int i = 1; i <= numCols; i++) {
                Position nodePosition = new Position(i * nodeSpacing, row * nodeSpacing);
                graph.addVertex(nodePosition);

                // Connect nodes horizontally
                if (i > 1) {
                    Position leftNode = new Position((i - 1) * nodeSpacing, row * nodeSpacing);
                    graph.addEdge(leftNode, nodePosition);
                }

                // Connect nodes vertically
                if (row > 1) {
                    Position topNode = new Position(i * nodeSpacing, (row - 1) * nodeSpacing);
                    graph.addEdge(topNode, nodePosition);
                }
            }
        }

        return graph;
    }

    public void setLabel(String text) {
        this.label.setText(text);
    }

    public void setFont(){

        InputStream is = label.getClass().getResourceAsStream("/fonts/body.ttf");

        if (is != null) {
            // Resto del código para cargar la fuente y aplicarla
            Font font = Font.loadFont(is, 12);

            // Set the font for the label
            label.setFont(font);

            /*gameName.setFont(myFont);
            implementationMenu.setFont(myFont);
            difficultyMenu.setFont(myFont);
            matrixButton.setFont(myFont);
            playButton.setFont(myFont);
            easyButton.setFont(myFont);
            difficultButton.setFont(myFont);
            listButton.setFont(myFont);*/

        } else {
            System.err.println("No se pudo cargar el InputStream de la fuente");
        }
    }

}
