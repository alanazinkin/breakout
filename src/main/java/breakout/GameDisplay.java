package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GameDisplay {
    List<Text> myText = new ArrayList<>();
    private Text livesText;
    private Text levelText;

    public GameDisplay() {}

    public void createGameStatusText(Life myLives, Level myLevel, int startingLives, int livesX, int livesY, int levelX, int levelY,
                                     String font, int fontSize) {
        // Create lives and level text
        myLives.setLives(startingLives);
        livesText = myLives.createLivesText(livesX, livesY, "Lucida Bright", fontSize);
        levelText = myLevel.createLevelText(levelX, levelY, "Lucida Bright", fontSize);
        myText.add(livesText);
        myText.add(levelText);
    }

    public void updateGameStatusTextForNewLevel(Level myLevel) {
        setLevelText("Current Level: " + myLevel.getLevel());
        int index = myText.indexOf(levelText);
        myText.set(index, levelText);
    }

    public void displayGameStatusTextElements(Group root, List<Text> myText) {
        for (Text text : myText) {
            root.getChildren().add(text);
        }
    }

    public List<Text> getMyText() {
        return myText;
    }

    public void setLivesText(String myLivesText) {
        livesText.setText(myLivesText);
    }

    public void setLevelText(String myLevelText) {
        levelText.setText(myLevelText);
    }

}
