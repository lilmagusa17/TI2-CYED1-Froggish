package com.froggish.froggish.model;

import com.froggish.froggish.graph.GraphAdjacencyList;
import com.froggish.froggish.graph.Node;
import com.froggish.froggish.graph.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FrogPlayer{

    private final String PATH_IDLE = "/com/froggish/froggish/img/frog/idle/i";
    private final String PATH_JUMPR = "/com/froggish/froggish/img/frog/jump/jr";
    private final String PATH_CROAK = "/com/froggish/froggish/img/frog/croak/c";
    private final String PATH_DEAD = "/com/froggish/froggish/img/frog/death/d";
    private int size;

    private Canvas canvas;
    private GraphicsContext graphicsContext; //contexto grafico

    private ArrayList<Image> idles; //estatico

    private ArrayList<Image> jumpsRight; //saltando
    private ArrayList<Image> croaks; //croando
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
    private int energy;

    private GraphAdjacencyList<Position> graph;

    public FrogPlayer(Canvas canvas, Position startPosition) {

        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        this.state = State.IDLE;

        this.frame = 0; //cada vez que se corre, hay que cambiar el frame

        this.idles = new ArrayList<>();

        this.jumpsRight = new ArrayList<>();

        this.dead = new ArrayList<>();

        this.croaks = new ArrayList<>();

        this.position = startPosition;


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

        for (int i = 1; i <= 8; i++) { //   croak 8
            Image image = new Image(getClass().getResourceAsStream(PATH_CROAK + i + ".png"), 30, 30, false, false);
            this.dead.add(image);
        }

        this.energy = 100;

        this.size = 30;

    }

    public void stop() {
        int canvasWidth = 1000;  // Set the width of your canvas
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

        if(isHome()){
            this.state = State.CROAK;
            this.energy = 100;
        }

    }


    public void paint() {
        onMove();
        stop();
        System.out.println(this.position.getX() + " " + this.position.getY());
        if(checkWaterLilyCollision(waterLily)){
            System.out.println("colision");
            this.state = State.CROAK;
            //this.energy = 100;
        }

        System.out.println("no colision");
        switch (state) {
            case IDLE: // idle 8
                this.graphicsContext.drawImage(idles.get(frame % 8), this.position.getX(), this.position.getY());
                break;
            case JUMPR: // jump right 7
                this.graphicsContext.drawImage(jumpsRight.get(frame % 7), this.position.getX(), this.position.getY());
                break;

            case DEAD: // dead son 8
                this.graphicsContext.drawImage(dead.get(frame % 8), this.position.getX(), this.position.getY());
                break;

            case CROAK: // croak son 8
                this.graphicsContext.drawImage(croaks.get(frame % 8), this.position.getX(), this.position.getY());
                break;
        }

        this.frame++;
    }

    public void setOnKeyPressed(KeyEvent event) { //recibe evento del teclado
        switch (event.getCode()) {

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

/*   public void onMove() {
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
    }*/

    public void onMove() {
        int step = 5;

        if (rightPressed && canMoveRight()) {
            this.position.setX(this.position.getX() + step);
        } else if (leftPressed && canMoveLeft()) {
            this.position.setX(this.position.getX() - step);
        }

        if (upPressed && !downPressed && canMoveUp()) {
            this.position.setY(this.position.getY() - step);
        } else if (downPressed && !upPressed && canMoveDown()) {
            this.position.setY(this.position.getY() + step);
        }
    }

    private boolean canMoveRight() {
        // Get the current node
        Node<Position> currentNode = getCurrentNode();

        // Check if there is a node to the right
        Node<Position> rightNode = getAdjacentNode(currentNode, Direction.RIGHT);
        return rightNode != null;
    }

    private boolean canMoveLeft() {
        // Get the current node
        Node<Position> currentNode = getCurrentNode();

        // Check if there is a node to the left
        Node<Position> leftNode = getAdjacentNode(currentNode, Direction.LEFT);
        return leftNode != null;
    }

    private boolean canMoveUp() {
        // Get the current node
        Node<Position> currentNode = getCurrentNode();

        // Check if there is a node above
        Node<Position> upNode = getAdjacentNode(currentNode, Direction.UP);
        return upNode != null;
    }

    private boolean canMoveDown() {
        // Get the current node
        Node<Position> currentNode = getCurrentNode();

        // Check if there is a node below
        Node<Position> downNode = getAdjacentNode(currentNode, Direction.DOWN);
        return downNode != null;
    }

    private Node<Position> getCurrentNode() {
        // Get the current position of the frog player
        Position currentPosition = this.position;

        // Find the corresponding node in the graph
        return findNodeByPosition(currentPosition);
    }

    private Node<Position> getAdjacentNode(Node<Position> node, Direction direction) {
        // Get the list of adjacent nodes
        ArrayList<Node<Position>> adjacentNodes = node.getNodosAdy();

        // Loop through the adjacent nodes and find the one in the specified direction
        for (Node<Position> adjacentNode : adjacentNodes) {
            Position adjacentPosition = adjacentNode.getValor();
            switch (direction) {
                case RIGHT:
                    if (adjacentPosition.getX() > node.getValor().getX()) {
                        return adjacentNode;
                    }
                    break;
                case LEFT:
                    if (adjacentPosition.getX() < node.getValor().getX()) {
                        return adjacentNode;
                    }
                    break;
                case UP:
                    if (adjacentPosition.getY() < node.getValor().getY()) {
                        return adjacentNode;
                    }
                    break;
                case DOWN:
                    if (adjacentPosition.getY() > node.getValor().getY()) {
                        return adjacentNode;
                    }
                    break;
            }
        }
        return null; // No adjacent node in the specified direction
    }

    private Node<Position> findNodeByPosition(Position position) {
        // Get the list of nodes in the graph

        ArrayList<Node<Position>> nodes = graph.getNodes();

        // Loop through the nodes and find the one with the specified position
        for (Node<Position> node : nodes) {
            if (node.getValor().equals(position)) {
                return node;
            }
        }
        return null; // Node not found
    }



    private boolean intersects(WaterLily wl) {
        if (wl == null) {
            return false;  // No collision if waterLily is null
        }

        // Check if the bounding boxes of this and waterLily intersect
        return this.position.getX() < wl.getPosition().getX() + wl.getSize() &&
                this.position.getX() + this.getSize() > wl.getPosition().getX() &&
                this.position.getY() < wl.getPosition().getY() + wl.getSize() &&
                this.position.getY() + this.getSize() > wl.getPosition().getY();
    }

    public boolean checkWaterLilyCollision(WaterLily waterLily) {
        return intersects(waterLily);
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
        if(energy == 0){
            this.state=State.DEAD;


        }else{

            position.setX(this.position.getX());
            position.setY(this.position.getY());
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    energy--;
                }
            }, 800);

        }

        System.out.println("lives: " + this.energy);
    }

    public boolean isHome(){
        return this.position.getX() >= 880 && this.position.getY() == 70;
    }

    public int getSize(){
        return this.size;
    }

    public void setWaterLily(WaterLily waterLily) {
        this.waterLily = waterLily;
    }


}
