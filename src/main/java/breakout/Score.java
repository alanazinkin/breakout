package breakout;

public class Score {
    int score;

    public Score(int score) {
        this.score = score;
    }
    public void increaseScore(int value) {
        score += value;
    }

    public void decreaseScore(int value) {
        score -= value;
    }

    public int getScore() {
        return score;
    }
}
