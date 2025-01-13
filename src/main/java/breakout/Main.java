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
    public static final int BALL_XSPEED = 50;
    public static final int BALL_YSPEED = 60;
    public static final int PADDLE_SPEED = 60;
    // scene contains all the shapes and has several useful methods
    private Scene myScene;
    private Group root;
    private int myXDirection = 1;
    private int myYDirection = 1;

    /**
     * Initialize what will be displayed.
     */
    private Bouncer myBouncer;
    private Rectangle myPaddle;
    private Block myFirstBlock;

    public class Bouncer {
        private Shape myBouncer;

        public Bouncer(Shape myShape) {
            myBouncer = myShape;
        }

        public Bouncer(double centerX, double centerY, double radius, Color color) {
            this.myBouncer = new Circle(centerX, centerY, radius, color);
        }

        public Bouncer(double length, double height, Color color) {
            this.myBouncer = new Rectangle(length, height, color);
        }

        public void setX(double x) {
            if (myBouncer instanceof Circle){
                ((Circle) myBouncer).setCenterX(x);
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            }

        }

        public void setY(double y) {
            if (myBouncer instanceof Circle){
                ((Circle) myBouncer).setCenterY(y);
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        public double getX() {
            if (myBouncer instanceof Circle){
                return ((Circle) myBouncer).getCenterX();
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            }

        }

        public double getY() {
            if (myBouncer instanceof Circle){
                return ((Circle) myBouncer).getCenterY();
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        public Shape getShape(){
            return this.myBouncer;
        }

    }

    public class Block {
        Shape blockShape;

        public Block(double length, double height, Color color) {
            this.blockShape = new Rectangle(length, height, color);
        }

        public Block(double radius, Color color) {
            this.blockShape = new Circle(radius, color);
        }

        public void setX(double x) {
            if (blockShape instanceof Rectangle){
                ((Rectangle) blockShape).setX(x);
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        public void setY(double y) {
            if (blockShape instanceof Rectangle){
                ((Rectangle) blockShape).setY(y);
            }
            else{
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }

        public Shape getShape(){
            return this.blockShape;
        }
    }

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
        myBouncer = new Bouncer(width / 2 - BOUNCER_SIZE / 2, height / 2 + 50, BOUNCER_SIZE, Color.BLACK);
        // x and y represent the top left corner, so center it in window
        myBouncer.setX(BOUNCER_SIZE);
        myPaddle = new Rectangle(width / 2 - PADDLE_WIDTH/ 2, height / 2 + 100, PADDLE_WIDTH, PADDLE_HEIGHT);
        myFirstBlock = new Block(BLOCK_WIDTH, BLOCK_HEIGHT, Color.BLUEVIOLET);
        myFirstBlock.setX(30);
        myFirstBlock.setY(50);

        // create one top level collection to organize the things in the scene
        // order added to the group is the order in which they are drawn
        root = new Group(myBouncer.getShape(), myPaddle, myFirstBlock.getShape());
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

    private void step(double elapsedTime) {
        // update "actors" attributes a little bit at a time and at a "constant" rate (no matter how many frames per second)
        myBouncer.setX(myBouncer.getX() + BALL_XSPEED * myXDirection * elapsedTime);
        myBouncer.setY(myBouncer.getY() + BALL_YSPEED * myYDirection * elapsedTime);
        Shape paddleIntersection = Shape.intersect(myBouncer.getShape(), myPaddle);
        Shape blockIntersection = Shape.intersect(myBouncer.getShape(), myFirstBlock.getShape());

        if (myBouncer.getX() >= SIZE || myBouncer.getX() <= 0) {
            myXDirection *= -1;
        }
        if (myBouncer.getY() >= SIZE || myBouncer.getY() <= 0) {
            myYDirection *= -1;
        }
        if (paddleIntersection.getBoundsInLocal().getWidth() != -1) {
            myYDirection *= -1;
        }
        if (blockIntersection.getBoundsInLocal().getWidth() != -1) {
            myYDirection *= -1;
            root.getChildren().remove(myFirstBlock.getShape());
        }

    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
        //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
        switch (code) {
            case RIGHT -> myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
            case LEFT -> myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
            case R -> myBouncer.setX(0);
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

    public static void main (String[] args) {
        launch(args);
    }

}
