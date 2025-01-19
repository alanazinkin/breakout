package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static breakout.Main.*;

public class GameDisplay {
    private Text livesText;
    private Text levelText;
    private Text scoreText;
    public GameDisplay() {}

    public void createGameStatusText(Group root, Life myLives, Level myLevel, Score myScore,
                                     String font, int fontSize) {
        ScreenText myText = new ScreenText();
        scoreText = myText.createText("Score: " + myScore.getScore(), SCOREX, SCOREY, font, fontSize);
        root.getChildren().add(scoreText);
        livesText = myText.createText("Lives Left: " + myLives.getLives(), LIVESX, LIVESY, font, fontSize);
        root.getChildren().add(livesText);
        levelText = myText.createText("Current Level: " + myLevel.getLevel(), LEVELX, LEVELY, font, fontSize);
        root.getChildren().add(levelText);
    }

    public void updateGameStatusText(Score myScore, Life myLives, Level myLevel) {
        scoreText.setText("Score: " + myScore.getScore());
        livesText.setText("Lives Left: " + myLives.getLives());
        levelText.setText("Current Level: " + myLevel.getLevel());
    }

}
