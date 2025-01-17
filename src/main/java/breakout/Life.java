package breakout;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Life {
    private int lives;

    public Life (int lives) {
        this.lives = lives;
    }

    public Text createLivesText(int xPosition, int yPosition, String font, int fontSize) {
        Text livesText = new Text(xPosition, yPosition, "Lives Left: " + lives);
        livesText.setFill(Color.HOTPINK);
        Font f = Font.font(font, FontWeight.BOLD, fontSize);
        livesText.setFont(f);
        return livesText;
    }



    public void decrementLives() {
        lives --;
    }

    public void incrementLives() {
        lives ++;
    }

    public void decreaseLives(int amount) {
        lives -= amount;
    }

    public void increaseLives(int amount) {
        lives += amount;
    }

    public int getLives() {
        return lives;
    }
}
