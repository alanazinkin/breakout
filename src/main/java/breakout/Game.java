package breakout;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static breakout.Main.LOSE_LIFE_SCORE;

public class Game {
    int numLevels;

    public static final String LEVELFILE_PATH = "src/main/resources/";

    public Game(int numLevels) {
        this.numLevels = numLevels;
    }

    public void endGame(Timeline animation, String title, String text) {
        Stage gameOverStage = new Stage();
        SplashScreen gameOverSplashScreen = new SplashScreen();
        animation.pause();
        Scene levelScene = gameOverSplashScreen.showSplashScreen(gameOverStage, title, text);
        gameOverSplashScreen.handleSplashScreenEvent(levelScene, gameOverStage, animation);
    }

    public void handleBallBouncesOut(Bouncer myBouncer, Life myLives, Level myLevel, GameDisplay myGameDisplay, Score myScore, int screenSize) {
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
