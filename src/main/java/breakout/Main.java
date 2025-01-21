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

import java.util.HashSet;

import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.PURPLE;

/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Alana Zinkin
 */
public class Main extends Application {
    public static final String TITLE = "Example JavaFX Animation";
    public static final Color WHITE = new Color(1, 1, 1, 1);
    public static final int SIZE = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int NUMLEVELS = 4;
    public static final int BOUNCER_SIZE = 20;
    public static final int PADDLE_HEIGHT = 5;
    public static final int PADDLE_WIDTH = 80;
    public static final int BLOCK_WIDTH = 120;
    public static final int BLOCK_HEIGHT = 40;
    public static final int PADDLE_SPEED = 60;
    public static final double LIVESX = (double) SIZE / 20;
    public static final double LIVESY = (double) SIZE / (1.05);
    public static final int NUMLIVES = 3;
    public static final double LEVELX = (double) SIZE / 20;
    public static final double LEVELY = (double) SIZE / (1.12);
    public static final double SCOREX = (double) SIZE / 20;
    public static final double SCOREY = (double) SIZE / (1.2);
    public static final int FONT_SIZE = 20;
    public static final String TEXT_FONT = "Lucida Bright";
    public static final int LEVEL_SCORE = 10;
    public static final int LOSE_LIFE_SCORE = 2;
    public static final double COOLDOWN_TIME = 0.01;

    // scene contains all the shapes and has several useful methods
    private Scene myScene;
    private Group root;
    private Timeline animation;
    private int myXDirection = 1;
    private int myYDirection = -1;
    private double initialBouncerXSpeed = 150;
    private double initialBouncerYSpeed = 220;
    private Level myLevel = new Level(0);
    private Game myGame;
    private Score myScore;
    private int highScore;
    private GameDisplay myGameDisplay = new GameDisplay();
    private Bouncer myBouncer;
    private Paddle myPaddle;
    private Life myLives = new Life(NUMLIVES);
    private String[] myLevelFiles;
    private SplashScreen myLevelSplashScreen = new SplashScreen();
    private SplashScreen myGameRulesSplashScreen = new SplashScreen();
    private Stage stage;
    private Color[] colorMapping = new Color[]{WHITE, BLUE, PINK, PURPLE, GREENYELLOW, LIGHTCORAL, ORANGE};
    private HashSet<Bouncer> activeBouncers;
    private HashSet<Bouncer> toRemoveBouncers;



    @Override
    /**
     * Entry point of Breakout Game
     */
    public void start (Stage stage) {
        makeStage(stage);
        makeGameLoop();
        Stage gameStage = new Stage();
        Scene myGameScene = myGameRulesSplashScreen.showSplashScreen(gameStage, "Game Rules", "Game Rules:\n" +
                "Move the paddle left and right\n" + " to destroy all bricks without\n" + " letting the ball drop.\n " +
                "Good Luck!");
        myGameRulesSplashScreen.handleSplashScreenEvent(myGameScene, gameStage, animation);
        highScore = 0;
    }

    /**
     * Creates Game loop timeline which goes on indefinitely
     */
    private void makeGameLoop() {
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
    }

    private void makeStage(Stage stage) {
        this.stage = stage;
        root = new Group();
        myScene = setupScene();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    /**
     * Create the game's "scene": what shapes will be in the game and their starting properties
      */
    public Scene setupScene () {
        initializeGame();
        myScene = new Scene(root, SIZE, SIZE, WHITE);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    /**
     * Resets score, lives, level, game display, and adds relevant items to the scene when the game starts
     */
    private void initializeGame() {
        activeBouncers = new HashSet<>();
        toRemoveBouncers = new HashSet<>();
        myScore = new Score(0);
        myLives.setLives(NUMLIVES);
        myLevel.setLevel(0);
        myGameDisplay.createGameStatusText(root, "Score: " + myScore.getScore(), "Lives Left: " + myLives.getLives(),
                "Current Level: " + myLevel.getLevel(), TEXT_FONT, FONT_SIZE);
        myGame = new Game(NUMLEVELS);
        myLevelFiles = myGame.makeLevelFileArray(NUMLEVELS);
        addBouncerPaddleBlocks();
    }

    // portions of code from bounce lab
    private void step(double elapsedTime) {
        // update "actors" attributes a little bit at a time and at a "constant" rate (no matter how many frames per second)
        for (Bouncer bouncer : activeBouncers) {
            bouncer.move(elapsedTime);
            bouncer.bounce(SIZE, BOUNCER_SIZE);
            myGame.handleBallBouncesOut(root, activeBouncers, toRemoveBouncers, bouncer, myLives, myLevel, myGameDisplay, myScore, SIZE);
            bouncer.paddleIntersect(myPaddle.getPaddle(), myLevel);
            bouncer.keepWithinFrame(SIZE, BOUNCER_SIZE);
            checkForBlockCollision(elapsedTime, bouncer);
        }
        removeStaleBouncers();

        myPaddle.keepInBounds(SIZE);
        myGameDisplay.updateGameStatusText(myScore, myLives, myLevel);
        if (myLevel.allBlocksHit()) {
            goToNextLevel();
        }
        if (myLives.outOfLives(myGame, animation)){
            endGameAndStartNewOne();
        }
    }

    // Active/Stale bouncer code generated by chatGPT. Refactored and rewritten by Alana Zinkin.
    private void removeStaleBouncers() {
        if (!toRemoveBouncers.isEmpty()) {
            activeBouncers.removeAll(toRemoveBouncers);
            toRemoveBouncers.clear();
        }
    }

    private void endGameAndStartNewOne() {
        int endScore = myScore.getScore();
        myLevel.endLevel(root);
        myGame.endGame(animation, "Game Over Splash Screen", "Game Over!\n" + "Final Level: " +
                myLevel.getLevel() + "\nFinal Score: " + endScore + "\nHigh Score: " + highScore +
                "\nPress Any Key to Restart!");
        initializeGame();
    }

    private void goToNextLevel() {
        animation.pause();
        updateHighScore(LEVEL_SCORE);
        advanceLevel(animation);
    }

    private void advanceLevel(Timeline animation) {
        int finalScore = myScore.getScore();
        myLevel.endLevel(root);
        Stage levelStage = new Stage();
        if (myLevel.getLevel() == NUMLEVELS) {
            myGame.endGame(animation,"Game Won Splash Screen", "You Won!\n" +
                    "Lives Remaining: " + myLives.getLives() + "\nFinal Score: " + finalScore + "\nHigh Score: " + highScore +
                    "\nPress Any Key to Play Again!");
            initializeGame();
        }
        else {
            Scene levelScene = myLevelSplashScreen.showSplashScreen(levelStage, "New Level Splash Screen", "Level " + myLevel.getLevel() + " Complete!");
            levelStage.setScene(levelScene);
            addBouncerPaddleBlocks();
            myGameDisplay.createGameStatusText(root, "Score: " + myScore.getScore(), "Lives Left: " + myLives.getLives(),
                    "Current Level: " + myLevel.getLevel(), TEXT_FONT, FONT_SIZE);
            myLevelSplashScreen.handleSplashScreenEvent(levelScene, levelStage, animation);
        }
    }

    public void addBouncerPaddleBlocks() {
        myBouncer = new Bouncer(SIZE / 2 - BOUNCER_SIZE / 2, SIZE / 2 + 60, BOUNCER_SIZE, Color.BLACK,
                initialBouncerXSpeed, initialBouncerYSpeed, myXDirection, myYDirection);
        myPaddle = new Paddle(SIZE / 2 - PADDLE_WIDTH/ 2, SIZE / 2 + 100, PADDLE_WIDTH, PADDLE_HEIGHT);
        myLevel.initBlocks(myLevelFiles, colorMapping, BLOCK_WIDTH, BLOCK_HEIGHT);
        myLevel.addBlocksToScene(root);
        root.getChildren().add(myBouncer.getBouncer());
        root.getChildren().add(myPaddle.getPaddle());
        resetActiveBouncers();
    }

    public void resetActiveBouncers() {
        activeBouncers.clear();
        toRemoveBouncers.clear();
        activeBouncers.add(myBouncer);
    }

    private void handleKeyInput (KeyCode code) {
        // NOTE new Java syntax that some prefer (but watch out for the many special cases!)
        //   https://blog.jetbrains.com/idea/2019/02/java-12-and-intellij-idea/
        switch (code) {
            case RIGHT -> myPaddle.getPaddle().setX(myPaddle.getPaddle().getX() + PADDLE_SPEED);
            case LEFT -> myPaddle.getPaddle().setX(myPaddle.getPaddle().getX() - PADDLE_SPEED);
            case R -> myPaddle.resetPaddle();
            case BACK_SPACE -> myLives.incrementLives();
            case S -> goToNextLevel();
            case TAB -> myBouncer.setYDirection(myBouncer.getYDirection() * -1);
            case U -> myBouncer.setXDirection(myBouncer.getXDirection() * -1);
        }
    }

    public void checkForBlockCollision(double elapsedTime, Bouncer bouncer) {
        for (int i = 0; i < myLevel.getNumBlocks(); i++) {
            Block block = myLevel.getBlocksList().get(i);
            if (block != null){
                block.updateCooldown(elapsedTime);
                Shape blockIntersection = Shape.intersect(bouncer.getBouncer(), block.getBlock());
                if (blockIntersection.getBoundsInLocal().getWidth() != -1 && !block.isInCooldown()) {
                    block.startCooldown(COOLDOWN_TIME);
                    handleHitBlock(block, i, elapsedTime, bouncer);
                }
            }
        }
    }

    private void handleHitBlock(Block block, int i, double elapsedTime, Bouncer bouncer) {
        updateHighScore(block.getType());
        bouncer.setYDirection(bouncer.getYDirection() * -1);
        removeHitBlock(block, i, elapsedTime);
    }

    private void removeHitBlock(Block block, int i, double elapsedTime) {
        if (block.getType() == 1) {
            removeTheBlock(block, i);
        }
        else if (block.getType() == 3) {
            removeTheBlock(block, i);
            explodeBlock(i + 1, block, elapsedTime);
            explodeBlock(i - 1, block, elapsedTime);
        }
        else if (block.getType() == 4) {
            Powerup myPowerup = new Powerup(5);
            myPowerup.releasePaddlePowerup(myPaddle);
            removeTheBlock(block, i);
        }
        else if (block.getType() == 5) {
            Bouncer myNewBouncer = new Bouncer(SIZE / 2 - BOUNCER_SIZE / 2, SIZE / 2 + 60, BOUNCER_SIZE, HOTPINK,
                    initialBouncerXSpeed, initialBouncerYSpeed, myXDirection, myYDirection);
            activeBouncers.add(myNewBouncer);
            root.getChildren().add(myNewBouncer.getBouncer());
            removeTheBlock(block, i);
        }
        else if (block.getType() == 6) {
            Powerup myPowerup = new Powerup(5);
            myPowerup.releaseBouncerPowerup(activeBouncers, initialBouncerYSpeed);
            removeTheBlock(block, i);
        }
        else {
            block.setType(block.getType() - 1);
            block.setColor(colorMapping[block.getType()]);
        }
    }

    private void removeTheBlock(Block block, int i) {
        root.getChildren().remove(block.getBlock());
        myLevel.addHitBlocks(block);
        myLevel.getBlocksList().set(i, null);
    }

    private void explodeBlock(int i, Block block, double elapsedTime) {
        if (0 <= i && i < myLevel.getNumBlocks()) {
            Block adjacent = myLevel.getBlocksList().get(i);
            if (adjacent != null && adjacent.getRow() ==  block.getRow()) {
                //recursive
                removeHitBlock(adjacent, i, elapsedTime);
            }
        }
    }

    private void updateHighScore(int hitBrickScore) {
        myScore.increaseScore(hitBrickScore);
        highScore = Math.max(myScore.getScore(), highScore);
    }

    public static void main (String[] args) {
        launch(args);
    }
}