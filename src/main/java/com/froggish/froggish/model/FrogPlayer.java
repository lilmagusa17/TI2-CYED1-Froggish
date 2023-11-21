package com.froggish.froggish.model;

import com.froggish.froggish.graph.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FrogPlayer{

    private final String PATH_IDLE = "/com/froggish/froggish/img/frog/idle/i";
    private final String PATH_JUMPD = "";
    private final String PATH_JUMPU = "";
    private final String PATH_JUMPR = "/com/froggish/froggish/img/frog/jump/jr";
    private final String PATH_JUMPL = "";
    private final String PATH_DEAD = "/com/froggish/froggish/img/frog/death/d";
    private int size;

    private Canvas canvas;
    private GraphicsContext graphicsContext; //contexto grafico

    private ArrayList<Image> idles; //estatico

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
    private WaterLily waterLily;
    private ArrayList<Life> energy;

    public FrogPlayer(Canvas canvas) {

        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        this.state = State.IDLE;

        this.frame = 0; //cada vez que se corre, hay que cambiar el frame

        this.idles = new ArrayList<>();
        /*this.jumpsDown = new ArrayList<>();
        this.jumpsUp = new ArrayList<>();*/
        this.jumpsRight = new ArrayList<>();
        //this.jumpsLeft = new ArrayList<>();
        this.dead = new ArrayList<>();

        this.position = new Position(70, 240);
        //0,0-> esquina superior izquierda

        for (int i = 1; i <= 8; i++) { //idle 8
            Image image = new Image(getClass().getResourceAsStream(PATH_IDLE + i + ".png"), 30, 30, false, false);
            this.idles.add(image);
        }

        for (int i = 1; i <= 7; i++) { //jump 7
            Image image = new Image(getClass().getResourceAsStream(PATH_JUMPR + i + ".png"), 30, 30, false, false);
            this.jumpsRight.add(image);
        }

        for (int i = 1; i <= 8; i++) { //   dead 8
            Image image = new Image(getClass().getResourceAsStream(PATH_DEAD + i + ".png"), 30, 30, false, false);
            this.dead.add(image);
        }
    }

    public void stop() {
        int canvasWidth = 900;  // Set the width of your canvas
        int canvasHeight = 600; // Set the height of your canvas

        // Calculate limits for the frog player
        if (rightPressed && position.getX() + 30 >= canvasWidth) {
            this.position.setX(canvasWidth - 30);
            this.rightPressed = false;
        }

        if (upPressed && position.getY() <= 0) {
            this.position.setY(0);
            this.upPressed = false;
        }
        if (downPressed && position.getY() + 30 >= canvasHeight) {
            this.position.setY(canvasHeight - 30);
            this.downPressed = false;
        }
    }


    public void paint() {
        stop();
        onMove();
        System.out.println(this.position.getX() + " " + this.position.getY());
        switch (state) {
            case IDLE: // idle 8
                this.graphicsContext.drawImage(idles.get(frame % 8), this.position.getX(), this.position.getY());
                break;
            /*case JUMPD: // jump down 2
                this.graphicsContext.drawImage(jumpsDown.get(frame % 2), this.position.getX(), this.position.getY());
                break;
            case JUMPL: // jump left 2
                this.graphicsContext.drawImage(jumpsLeft.get(frame % 2), this.position.getX(), this.position.getY());
                break;*/
            case JUMPR: // jump right 7
                this.graphicsContext.drawImage(jumpsRight.get(frame % 7), this.position.getX(), this.position.getY());
                break;
           /* case JUMPU: // jump up 3
                this.graphicsContext.drawImage(jumpsUp.get(frame % 2), this.position.getX(), this.position.getY());
                break;*/
            case DEAD: // dead son 8
                this.graphicsContext.drawImage(dead.get(frame % 8), this.position.getX(), this.position.getY());
                break;
        }

        this.frame++;
    }

    public void setOnKeyPressed(KeyEvent event) { //recibe evento del teclado
        switch (event.getCode()) {
            //FIXME should we add jumps for all the directions?
            case UP:
                this.state = State.JUMPR;
                this.upPressed = true;
                break;
            case DOWN:
                this.state = State.JUMPR;
                this.downPressed = true;
                break;

            case RIGHT:
                this.state = State.JUMPR;
                this.rightPressed = true;
                break;

            case LEFT:
                this.state = State.JUMPR;
                this.leftPressed = true;
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

            case RIGHT:
                this.state = State.IDLE;
                this.rightPressed = false;
                break;

            case LEFT:
                this.state = State.IDLE;
                this.leftPressed = false;
                break;

        }
    }

    public void onMove() {
        int step = 5;

        if (rightPressed) {
            this.position.setX(this.position.getX() + step);
        } else if (leftPressed) {  // Add condition for left movement
            this.position.setX(this.position.getX() - step);
        }

        if (upPressed && !downPressed) {
            this.position.setY(this.position.getY() - step);
        } else if (downPressed && !upPressed) {
            this.position.setY(this.position.getY() + step);
        }

    }

    private boolean intersects(WaterLily wl) {
        // Check if the bounding boxes of this and brick intersect
        return this.position.getX() < wl.getPosition().getX() + wl.getSize() &&
                this.position.getX() + this.getSize() > wl.getPosition().getX() &&
                this.position.getY() < wl.getPosition().getY() + wl.getSize() &&
                this.position.getY() + this.getSize() > wl.getPosition().getY();
    }


    public Position getPosition() {
        return position;
    }

    public Position getPositionWaterLily(){
        return this.waterLily.getPosition();
    }

    /**
     * Decreases the lives of the player (use when player is killed)
     */
    public void flop(){
        if(this.energy.size()==1){
            this.state=State.DEAD;


        }else{
            //FIXME: The frog has to stay in the same position when it loses energy
            position.setX(20);
            position.setY(250);
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //FIXME how many lives should we have? Like 100?
                    energy.remove(2);
                }
            }, 800);

        }

        System.out.println("lives: " + this.energy.size());
    }

    public boolean isHome(){
        //FIXME: The coordinates should match the home coordinates -> the las node of the graph
        return this.position.getX()>=290 && this.position.getX()<=470 && this.position.getY()>=480;
    }

    public int getSize(){
        return this.size;
    }


}
