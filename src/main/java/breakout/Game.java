package breakout;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import static breakout.Main.LOSE_LIFE_SCORE;

public class Game {
    int numLevels;

    public static final String LEVELFILE_PATH = "src/main/resources/";

    public Game(int numLevels) {
        this.numLevels = numLevels;
    }

    public void loseGame(Timeline animation, Level myLevel, int highScore) {
        Stage gameOverStage = new Stage();
        SplashScreen gameOverSplashScreen = new SplashScreen();
        animation.pause();
        Scene levelScene = gameOverSplashScreen.showSplashScreen(gameOverStage, "Game Over Splash Screen", "Game Over!\n" +
                "Final Level: " + myLevel.getLevel() + "\nHigh Score: " + highScore + "\nPress Any Key to Restart!");
        gameOverSplashScreen.handleSplashScreenEvent(levelScene, gameOverStage, animation);
    }

    public void winGame(Timeline animation, Life myLives, int highScore) {
        Stage gameWonStage = new Stage();
        SplashScreen gameWonSplashScreen = new SplashScreen();
        animation.pause();
        Scene levelScene = gameWonSplashScreen.showSplashScreen(gameWonStage, "Game Won Splash Screen", "You Won!\n" +
                "Lives Remaining: " + myLives.getLives() + "\nHigh Score: " + highScore + "\nPress Any Key to Play Again!");
        gameWonSplashScreen.handleSplashScreenEvent(levelScene, gameWonStage, animation);
    }

    public void ballBouncesOut(Bouncer myBouncer, Life myLives, Level myLevel, GameDisplay myGameDisplay, Score myScore, int screenSize) {
        if (myBouncer.outTheBounds(screenSize, myBouncer.getMySize())) {
            myBouncer.resetBouncer(screenSize, myBouncer.getMySize());
            myLives.decrementLives();
            myScore.decreaseScore(LOSE_LIFE_SCORE);
            myGameDisplay.updateGameStatusText(myScore, myLives, myLevel);
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
}
