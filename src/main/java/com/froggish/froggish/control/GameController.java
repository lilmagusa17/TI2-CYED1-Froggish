package com.froggish.froggish.control;

import com.froggish.froggish.graph.GraphAdjacencyList;
import com.froggish.froggish.graph.Position;
import com.froggish.froggish.screen.GameScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private GameScreen screenA;

    private GraphAdjacencyList<Position> graph;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.graph = createMatrixGraph();  // Initialize the graph

        this.screenA = new GameScreen(this.canvas, graph);
        this.screenA.paint();

        //suscribe el canvas a las acciones del teclado
        //el screen le pasa el movimiento al avatar
        //hace que el avatar se mueva
        initActions();


        //hilo recibe un runnable y ese hilo por debajo llama al hilo de la interfaz grafica
        new Thread(
                ()->{
                    while (true) {//true para que siempre este corriendo, hacerlo atributo que cambie cuando se cierra la ventana
                        Platform.runLater( () -> {
                            paint();
                            //this.screenA.paint();
                        });

                        try {

                            Thread.sleep(100);

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    public void initActions(){
        this.canvas.setOnKeyPressed(
                (event) -> {
                    System.out.println("Key Pressed: " + event.getCode());
                    this.screenA.setOnKeyPressed(event);
                }
        );

        this.canvas.setOnKeyReleased(
                (event) -> {
                    System.out.println("Key Released: " + event.getCode());
                    this.screenA.setOnKeyReleased(event);
                }
        );
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
                    graph.addEdge(nodePosition, leftNode);
                }

                // Connect nodes vertically
                if (row > 1) {
                    Position topNode = new Position(i * nodeSpacing, (row - 1) * nodeSpacing);
                    graph.addEdge(nodePosition, topNode);
                }
            }
        }

        return graph;
    }

}
