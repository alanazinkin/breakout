package breakout;

import javafx.scene.paint.Color;

public class Life {
    private int lives;

    Life(int lives) {
        this.lives = lives;
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
