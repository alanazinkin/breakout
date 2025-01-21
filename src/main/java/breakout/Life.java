package breakout;

import javafx.animation.Timeline;

public class Life {
    private int lives;

    public Life (int lives) {
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

    public boolean outOfLives() {
        return (lives <= 0);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
