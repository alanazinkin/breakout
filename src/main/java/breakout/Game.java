package breakout;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashSet;

import static breakout.Main.BOUNCER_SIZE;

public class Game {
    int myNumLevels;
    int myScore;
    int myLives;

    public static final String LEVELFILE_PATH = "src/main/resources/";

    public Game(int numLevels, int score, int lives) {
        myNumLevels = numLevels;
        myScore = score;
        myLives = lives;
    }

    public void endGame(Timeline animation, String title, String text) {
        Stage gameOverStage = new Stage();
        SplashScreen gameOverSplashScreen = new SplashScreen();
        animation.pause();
        Scene levelScene = gameOverSplashScreen.showSplashScreen(gameOverStage, title, text);
        gameOverSplashScreen.handleSplashScreenEvent(levelScene, gameOverStage, animation);
    }

    public void handleBallBouncesOut(Group root, HashSet<Bouncer> activeBouncers, HashSet<Bouncer> toRemove, Bouncer myBouncer, Level myLevel, GameDisplay myGameDisplay, Game myGame, int screenHeight) {
        if (myBouncer.outTheBounds(screenHeight, BOUNCER_SIZE)){
            if (activeBouncers.size() <= 1) {
                myBouncer.resetBouncer(screenHeight, myBouncer.getMySize());
                myGame.decrementLives();
                myGameDisplay.updateGameStatusText(myGame, myLevel);
            }
            else {
                root.getChildren().remove(myBouncer.getBouncer());
                toRemove.add(myBouncer);
            }
        }
    }

    public String[] makeLevelFileArray(int numLevels) {
        String[] myLevelFiles = new String[numLevels];
        for (int i = 0; i < numLevels; i++) {
            String fileName = LEVELFILE_PATH + "lvl_" + i + ".txt";
            myLevelFiles[i] = fileName;
        }
        return myLevelFiles;
    }

    public void increaseScore(int value) {
        myScore += value;
    }

    public int getScore() {
        return myScore;
    }

    public void decrementLives() {
        myLives --;
    }

    public void incrementLives() {
        myLives ++;
    }

    public boolean outOfLives() {
        return (myLives <= 0);
    }

    public int getLives() {
        return myLives;
    }

    public void setLives(int lives) {
        myLives = lives;
    }
}
