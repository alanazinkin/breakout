package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static breakout.Main.*;

public class GameDisplay {
    private Text myLivesText;
    private Text myLevelText;
    private Text myScoreText;
    public GameDisplay() {}

    public void createGameStatusText(Group root, String scoreText, String livesText, String levelText,
                                     String font, int fontSize) {
        myScoreText = createText(scoreText, SCOREX, SCOREY, font, fontSize);
        root.getChildren().add(myScoreText);
        myLivesText = createText(livesText, LIVESX, LIVESY, font, fontSize);
        root.getChildren().add(myLivesText);
        myLevelText = createText(levelText, LEVELX, LEVELY, font, fontSize);
        root.getChildren().add(myLevelText);
    }

    public javafx.scene.text.Text createText(String text, double xPosition, double yPosition, String font, int fontSize) {
        javafx.scene.text.Text myText = new javafx.scene.text.Text(xPosition, yPosition, text);
        myText.setFill(Color.HOTPINK);
        Font f = Font.font(font, FontWeight.BOLD, fontSize);
        myText.setFont(f);
        return myText;
    }

    public void updateGameStatusText(Game myGame, Life myLives, Level myLevel) {
        myScoreText.setText("Score: " + myGame.getScore());
        myLivesText.setText("Lives Left: " + myLives.getLives());
        myLevelText.setText("Current Level: " + myLevel.getLevel());
    }

}
