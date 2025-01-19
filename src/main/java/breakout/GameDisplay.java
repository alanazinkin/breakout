package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static breakout.Main.*;

public class GameDisplay {
    private Text livesText;
    private Text levelText;
    public GameDisplay() {}

    public void createGameStatusText(Group root, Life myLives, Level myLevel,
                                     String font, int fontSize) {
        // Create lives and level text
        livesText = myLives.createLivesText(LIVESX, LIVESY, font, fontSize);
        root.getChildren().add(livesText);
        levelText = myLevel.createLevelText(LEVELX, LEVELY, font, fontSize);
        root.getChildren().add(levelText);
    }

    public void updateGameStatusText(Life myLives, Level myLevel) {
        livesText.setText("Lives Left: " + myLives.getLives());
        levelText.setText("Current Level: " + myLevel.getLevel());
    }

}
