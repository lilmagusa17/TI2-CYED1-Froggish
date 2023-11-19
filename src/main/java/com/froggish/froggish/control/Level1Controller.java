package com.froggish.froggish.control;

import com.froggish.froggish.screen.GameScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

public class Level1Controller implements Initializable {

    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private GameScreen screenA;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.graphicsContext=this.canvas.getGraphicsContext2D();
        this.screenA=new GameScreen(this.canvas);//aqui solucionamos toda la inyeccion de dependencia
        this.screenA.paint();//pintamos el screenA

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

                            Thread.sleep(200);

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

}
