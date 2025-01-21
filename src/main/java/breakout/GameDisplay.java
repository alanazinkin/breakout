package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;

import static breakout.Main.*;

public class GameDisplay {
    private Text myLivesText;
    private Text myLevelText;
    private Text myScoreText;
    public GameDisplay() {}

    public void createGameStatusText(Group root, String scoreText, String livesText, String levelText,
                                     String font, int fontSize) {
        ScreenText myText = new ScreenText();
        myScoreText = myText.createText(scoreText, SCOREX, SCOREY, font, fontSize);
        root.getChildren().add(myScoreText);
        myLivesText = myText.createText(livesText, LIVESX, LIVESY, font, fontSize);
        root.getChildren().add(myLivesText);
        myLevelText = myText.createText(levelText, LEVELX, LEVELY, font, fontSize);
        root.getChildren().add(myLevelText);
    }

    public void updateGameStatusText(Game myGame, Life myLives, Level myLevel) {
        myScoreText.setText("Score: " + myGame.getScore());
        myLivesText.setText("Lives Left: " + myLives.getLives());
        myLevelText.setText("Current Level: " + myLevel.getLevel());
    }

}
