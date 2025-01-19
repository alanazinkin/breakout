package breakout;

import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Life {
    private int lives;

    public Life (int lives) {
        this.lives = lives;
    }

    public Text createLivesText(double xPosition, double yPosition, String font, int fontSize) {
        Text livesText = new Text(xPosition, yPosition, "Lives Left: " + lives);
        System.out.println(livesText.getText());
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

    public boolean outOfLives(Game myGame, Timeline timeline) {
        if (lives <= 0) {
            //timeline.pause();
            //myGame.loseGame(timeline);
            return true;
        }
        return false;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
