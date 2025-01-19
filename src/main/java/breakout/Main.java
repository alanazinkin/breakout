package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
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
    public static final int SIZE = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;


    // many resources may be in the same shared folder
    // note, leading slash means automatically start in "src/main/resources" folder
    // note, Java always uses forward slash, "/", (even for Windows)
    private static final int NUMLEVELS = 2;
    public static final int BOUNCER_SIZE = 20;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_WIDTH = 80;
    public static final int BLOCK_WIDTH = 80;
    public static final int BLOCK_HEIGHT = 40;
    public static final int BALL_XSPEED = 120;
    public static final int BALL_YSPEED = 160;
    public static final int PADDLE_SPEED = 60;
    public static final double LIVESX = (double) SIZE / 20;
    public static final double LIVESY = (double) SIZE / (1.05);
    public static final int NUMLIVES = 3;
    public static final double LEVELX = (double) SIZE / 20;
    public static final double LEVELY = (double) SIZE / (1.12);
    public static final int FONT_SIZE = 20;
    public static final String TEXT_FONT = "Lucida Bright";
    public static final int SPLASHSCREEN_WIDTH = 300;
    public static final int SPLASHSCREEN_HEIGHT = 250;


    // scene contains all the shapes and has several useful methods
    private Scene myScene;
    private Group root;
    private int myXDirection = 1;
    private int myYDirection = -1;
    private Level myLevel = new Level(0);
    /**
     * Initialize what will be displayed.
     */
    private Game myGame;
    private GameDisplay myGameDisplay = new GameDisplay();
    private Bouncer myBouncer;
    private Paddle myPaddle;
    private Life myLives = new Life(NUMLIVES);
    private String[] myLevelFiles;
    private SplashScreen myLevelSplashScreen = new SplashScreen();
    private SplashScreen myGameRulesSplashScreen = new SplashScreen();
    private Stage stage;



    @Override
    public void start (Stage stage) {
        this.stage = stage;
        root = new Group();
        myScene = setupScene();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY, animation)));
        Stage gameStage = new Stage();
        Scene myGameScene = myGameRulesSplashScreen.showSplashScreen(gameStage, "Game Rules", "Game Rules:\n" +
                "Move the paddle left and right\n" +
                " to destroy all bricks without\n" +
                " letting the ball drop.\n " +
                "Good Luck!");
        myGameRulesSplashScreen.handleSplashScreenEvent(myGameScene, gameStage, animation);
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    public Scene setupScene () {
        startGame();
        myScene = new Scene(root, SIZE, SIZE, WHITE);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private void startGame() {
        myLives.setLives(NUMLIVES);
        myLevel.setLevel(0);
        myGameDisplay.createGameStatusText(root, myLives, myLevel, TEXT_FONT, FONT_SIZE);
        myGame = new Game(NUMLEVELS);
        myLevelFiles = myGame.makeLevelFileArray(NUMLEVELS);
        addRelevantItemsToScene();
    }

    // portions of code from bounce lab
    private void step(double elapsedTime, Timeline animation) {
        // update "actors" attributes a little bit at a time and at a "constant" rate (no matter how many frames per second)
        myBouncer.move(elapsedTime);
        myGame.ballBouncesOut(myBouncer, myLives, myLevel, myGameDisplay, SIZE);
        myPaddle.keepInBounds(SIZE);
        myBouncer.paddleIntersect(myPaddle.getPaddle(), BOUNCER_SIZE);
        myBouncer.bounce(SIZE, BOUNCER_SIZE);
        myBouncer.keepWithinFrame(SIZE, BOUNCER_SIZE);
        bouncerBrickCollisionCheck();

        // check if all blocks have been hit
        //TODO: wrap in method
        if (myLevel.allBlocksHit()) {
            animation.pause();
            advanceLevel(animation);
        }
        myGameDisplay.updateGameStatusText(myLives, myLevel);
        // check if out of lives?
        myLives.outOfLives(myGame, animation);
    }

    private void advanceLevel(Timeline animation) {
        myLevel.endLevel(root);
        Stage levelStage = new Stage();
        if (myLevel.getLevel()==NUMLEVELS) {
            startGame();
            Scene levelScene = myLevelSplashScreen.showSplashScreen(levelStage, "Restart Splash Screen", "You Won! Restart!");
            myLevelSplashScreen.handleSplashScreenEvent(levelScene, levelStage, animation);
        }
        else {
            Scene levelScene = myLevelSplashScreen.showSplashScreen(levelStage, "New Level Splash Screen", "Level " + myLevel.getLevel() + " Complete!");
            levelStage.setScene(levelScene);
            addRelevantItemsToScene();
            myGameDisplay.createGameStatusText(root, myLives, myLevel, TEXT_FONT, FONT_SIZE);
            myLevelSplashScreen.handleSplashScreenEvent(levelScene, levelStage, animation);
        }
    }

    // What to do each time a key is pressed
    private void handleKeyInput (KeyCode code) {
        // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
        //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
        switch (code) {
            case RIGHT -> myPaddle.getPaddle().setX(myPaddle.getPaddle().getX() + PADDLE_SPEED);
            case LEFT -> myPaddle.getPaddle().setX(myPaddle.getPaddle().getX() - PADDLE_SPEED);
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

    public void bouncerBrickCollisionCheck() {
        for (int i = 0; i < myLevel.getNumBlocks(); i++) {
            Block block = myLevel.getBlocksList().get(i);
            if (block != null){
                Shape blockIntersection = Shape.intersect(myBouncer.getBouncer(), block.getBlock());
                if (blockIntersection.getBoundsInLocal().getWidth() != -1) {
                    myBouncer.setYDirection(myBouncer.getYDirection() * -1);
                    root.getChildren().remove(block.getBlock());
                    myLevel.addHitBlocks(block);
                    // temporary fix for removing blocks
                    myLevel.getBlocksList().set(i, null);
                }
            }

        }
    }

    public void addRelevantItemsToScene() {
        // make some shapes and set their properties
        myBouncer = new Bouncer(SIZE / 2 - BOUNCER_SIZE / 2, SIZE / 2 + 60, BOUNCER_SIZE, Color.BLACK,
                BALL_XSPEED, BALL_YSPEED, myXDirection, myYDirection);
        // x and y represent the top left corner, so center it in window
        myPaddle = new Paddle(SIZE / 2 - PADDLE_WIDTH/ 2, SIZE / 2 + 100, PADDLE_WIDTH, PADDLE_HEIGHT);
        // create one top level collection to organize the things in the scene
        // order added to the group is the order in which they are drawn
        myLevel.initBlocks(myLevelFiles, BLOCK_WIDTH, BLOCK_HEIGHT, Color.BLUE);
        myLevel.addBlocksToScene(root);
        root.getChildren().add(myBouncer.getBouncer());
        root.getChildren().add(myPaddle.getPaddle());
        // add Level and Life game display
    }

    public static void main (String[] args) {
        launch(args);

    }

}
