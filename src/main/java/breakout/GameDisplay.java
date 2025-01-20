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

    public void createGameStatusText(Group root, String scoreText, String livesText, String levelText,
                                     String font, int fontSize) {
        ScreenText myText = new ScreenText();
        this.scoreText = myText.createText(scoreText, SCOREX, SCOREY, font, fontSize);
        root.getChildren().add(this.scoreText);
        this.livesText = myText.createText(livesText, LIVESX, LIVESY, font, fontSize);
        root.getChildren().add(this.livesText);
        this.levelText = myText.createText(levelText, LEVELX, LEVELY, font, fontSize);
        root.getChildren().add(this.levelText);
    }

    public void updateGameStatusText(Score myScore, Life myLives, Level myLevel) {
        scoreText.setText("Score: " + myScore.getScore());
        livesText.setText("Lives Left: " + myLives.getLives());
        levelText.setText("Current Level: " + myLevel.getLevel());
    }

}
