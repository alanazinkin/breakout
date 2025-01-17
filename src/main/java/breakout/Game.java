package breakout;

import javafx.scene.control.Alert;

public class Game {
    int numLevels;

    public static final String LEVELFILE_PATH = "src/main/resources/";

    public Game(int numLevels) {
        this.numLevels = numLevels;
    }

    public void loseGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null); // No header
        alert.setContentText("Game Over!");
        alert.show();
    }

    public void winGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Won");
        alert.setHeaderText(null);
        alert.setContentText("You won!");
        alert.show();
    }

    public String[] makeLevelFileArray(int numLevels) {
        String[] myLevelFiles = new String[numLevels + 1];
        for (int i = 0; i < numLevels; i++) {
            String fileName = LEVELFILE_PATH + "lvl_" + i + ".txt";
            myLevelFiles[i] = fileName;
        }
        return myLevelFiles;
    }
}
