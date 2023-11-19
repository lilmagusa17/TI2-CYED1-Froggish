package com.froggish.froggish.model;

import com.froggish.froggish.graph.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Objects;

public class FrogPlayer{

    private final String PATH_IDLE = "";
    private final String PATH_JUMPD = "";
    private final String PATH_JUMPU = "";
    private final String PATH_JUMPR = "";
    private final String PATH_JUMPL = "";
    private final String PATH_DEAD = "";

    private Canvas canvas;
    private GraphicsContext graphicsContext; //contexto grafico

    private ArrayList<Image> idles; //estatico

    private ArrayList<Image> walksDown; //caminando
    private ArrayList<Image> walksUp; //caminando
    private ArrayList<Image> walksRight; //caminando
    private ArrayList<Image> walksLeft; //caminando

    private ArrayList<Image> jumpsDown; //saltando
    private ArrayList<Image> jumpsUp; //saltando
    private ArrayList<Image> jumpsRight; //saltando
    private ArrayList<Image> jumpsLeft; //saltando
    private ArrayList<Image> dead; //muerto


    //elementos espaciales
    private Position position; //posicion del avatar

    private State state; //state of the player (idle, walk, jump, dead)

    private int frame; //current frame

    private boolean rightPressed;
    private boolean leftPressed;
    private boolean upPressed;

    private boolean downPressed;

    public FrogPlayer(Canvas canvas) {

        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        this.state = State.IDLE;

        this.frame = 0; //cada vez que se corre, hay que cambiar el frame

        this.idles = new ArrayList<>();
        this.walksDown = new ArrayList<>();
        this.walksUp = new ArrayList<>();
        this.walksRight = new ArrayList<>();
        this.walksLeft = new ArrayList<>();
        this.jumpsDown = new ArrayList<>();
        this.jumpsUp = new ArrayList<>();
        this.jumpsRight = new ArrayList<>();
        this.jumpsLeft = new ArrayList<>();
        this.dead = new ArrayList<>();

        this.position = new Position(100, 100);
        //0,0-> esquina superior izquierda

        for (int i = 1; i <= 2; i++) { //idle

            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(PATH_IDLE + i + ".png")), 50, 50, false, false);
            this.idles.add(image);
        }
    }

    public void stop() {
        //calcular limites del muñeco
        if (rightPressed && position.getX() >= 550) {
            this.position.setX(550);
            this.rightPressed = false;
        }
        if (leftPressed && position.getX() <= 10) {
            this.position.setX(10);
            this.leftPressed = false;
        }
        if (upPressed && position.getY() <= 10) {
            this.position.setY(10);
            this.downPressed = false;
        }
        if (downPressed && position.getY() >= 340) {
            this.position.setY(340);
            this.upPressed = false;
        }
    }


    public void paint() {
        stop();
        onMove();
        System.out.println(this.position.getX() + " " + this.position.getY());
        switch (state) {
            case IDLE: // idle 2
                this.graphicsContext.drawImage(idles.get(frame % 2), this.position.getX(), this.position.getY());
                break;
            case JUMPD: // jump down 2
                this.graphicsContext.drawImage(jumpsDown.get(frame % 2), this.position.getX(), this.position.getY());
                break;
            case JUMPL: // jump left 2
                this.graphicsContext.drawImage(jumpsLeft.get(frame % 2), this.position.getX(), this.position.getY());
                break;
            case JUMPR: // jump right 2
                this.graphicsContext.drawImage(jumpsRight.get(frame % 2), this.position.getX(), this.position.getY());
                break;
            case JUMPU: // jump up 3
                this.graphicsContext.drawImage(jumpsUp.get(frame % 2), this.position.getX(), this.position.getY());
                break;
            case DEAD: // dead son 6
                this.graphicsContext.drawImage(dead.get(frame % 6), this.position.getX(), this.position.getY());
                break;
        }

        this.frame++;
    }

    public void setOnKeyPressed(KeyEvent event) { //recibe evento del teclado
        switch (event.getCode()) {
            case UP:
                this.state = State.JUMPU;
                this.upPressed = true;
                break;
            case DOWN:
                this.state = State.JUMPD;
                this.downPressed = true;
                break;

            case LEFT:
                this.state = State.JUMPL;
                this.leftPressed = true;
                break;

            case RIGHT:
                this.state = State.JUMPR;
                this.rightPressed = true;
                break;

        }
    }

    public void setOnKeyReleased(KeyEvent event) {
        switch (event.getCode()) {

            case UP:
                this.state = State.IDLE;
                this.upPressed = false;
                break;

            case DOWN:
                this.state = State.IDLE;
                this.downPressed = false;
                break;

            case LEFT:
                this.state = State.IDLE;
                this.leftPressed = false;
                break;

            case RIGHT:
                this.state = State.IDLE;
                this.rightPressed = false;
                break;
        }
    }

    public void onMove() {
        int step = 15;

        if (rightPressed && !leftPressed) {
            this.position.setX(this.position.getX() + step);
        } else if (leftPressed && !rightPressed) {
            this.position.setX(this.position.getX() - step);
        }

        if (upPressed && !downPressed) {
            this.position.setY(this.position.getY() - step);
        } else if (downPressed && !upPressed) {
            this.position.setY(this.position.getY() + step);
        }

    }

    public Position getPosition() {
        return position;
    }
}
