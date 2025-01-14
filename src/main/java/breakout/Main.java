package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Alana Zinkin
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Example JavaFX Animation";
    public static final Color WHITE = new Color(1, 1, 1, 1);
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;


    // many resources may be in the same shared folder
    // note, leading slash means automatically start in "src/main/resources" folder
    // note, Java always uses forward slash, "/", (even for Windows)
    public static final int BOUNCER_SIZE = 20;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_WIDTH = 80;
    public static final int BLOCK_WIDTH = 80;
    public static final int BLOCK_HEIGHT = 40;
    public static final int NUM_BLOCKS = 10;
    public static final int BALL_XSPEED = 100;
    public static final int BALL_YSPEED = 60;
    public static final int PADDLE_SPEED = 60;

    // scene contains all the shapes and has several useful methods
    private Scene myScene;
    private Group root;
    private int myXDirection = 1;
    private int myYDirection = -1;

    /**
     * Initialize what will be displayed.
     */
    private Bouncer myBouncer;
    private Rectangle myPaddle;
    private ArrayList<Block> myBlocks = new ArrayList<>();


    @Override
    public void start (Stage stage) {
        Circle shape = new Circle(200, 200, 40);
        shape.setFill(Color.LIGHTSTEELBLUE);

        Group root = new Group();
        root.getChildren().add(shape);

        Scene myScene = setupScene(SIZE, SIZE, WHITE);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene (int width, int height, Paint background) {
        // make some shapes and set their properties
        myBouncer = new Bouncer(width / 2 - BOUNCER_SIZE / 2, height / 2 + 60, BOUNCER_SIZE, Color.BLACK,
                BALL_XSPEED, BALL_YSPEED, myXDirection, myYDirection);
        // x and y represent the top left corner, so center it in window

        myPaddle = new Rectangle(width / 2 - PADDLE_WIDTH/ 2, height / 2 + 100, PADDLE_WIDTH, PADDLE_HEIGHT);
        // create one top level collection to organize the things in the scene
        // order added to the group is the order in which they are drawn
        root = new Group(myBouncer.getBouncer(), myPaddle);
        Block.initBlocks(root, myBlocks, NUM_BLOCKS, BLOCK_WIDTH, BLOCK_HEIGHT);
        // could also add them dynamically later
        //root.getChildren().add(myMover);
        //root.getChildren().add(myGrower);
        // create a place to see the shapes
        myScene = new Scene(root, width, height, background);
        // respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        //REMOVE IF NEEDED: myScene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        return myScene;
    }

    // portions of code from bounce lab
    private void step(double elapsedTime) {
        // update "actors" attributes a little bit at a time and at a "constant" rate (no matter how many frames per second)
        myBouncer.move(elapsedTime);
        collisionCheck();
        myBouncer.paddleIntersect(myPaddle);
        // ensure paddle remains within screen bounds
        if (myPaddle.getX() <= 0) {
            myPaddle.setX(0);
        }
        if (myPaddle.getX() >= SIZE - PADDLE_WIDTH) {
            myPaddle.setX(SIZE - PADDLE_WIDTH);
        }
        // make ball bounce within screen
        myBouncer.bounce(elapsedTime, SIZE, BOUNCER_SIZE);

    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
        //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
        switch (code) {
            case RIGHT -> myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
            case LEFT -> myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
            case R -> myBouncer.getBouncer().setCenterX(0);
        }
        // TYPICAL way to do it, definitely more readable for longer actions
//        if (code == KeyCode.RIGHT) {
//            myMover.setX(myMover.getX() + MOVER_SPEED);
//        }
//        else if (code == KeyCode.LEFT) {
//            myMover.setX(myMover.getX() - MOVER_SPEED);
//        }
//        else if (code == KeyCode.UP) {
//            myMover.setY(myMover.getY() - MOVER_SPEED);
//        }
//        else if (code == KeyCode.DOWN) {
//            myMover.setY(myMover.getY() + MOVER_SPEED);
//        }
    }

    public void collisionCheck() {
        for (int i = 0; i < NUM_BLOCKS; i++) {
            Block block = myBlocks.get(i);
            if (block != null){
                Shape blockIntersection = Shape.intersect(myBouncer.getBouncer(), block.getBlock());
                if (blockIntersection.getBoundsInLocal().getWidth() != -1) {
                    myBouncer.setYDirection(myBouncer.getYDirection() * -1);
                    root.getChildren().remove(block.getBlock());
                    // temporary fix for removing blocks
                    myBlocks.set(i, null);
                }
            }

        }
    }

    public static void main (String[] args) {
        launch(args);
    }

}
