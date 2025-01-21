package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static breakout.Main.*;

public class Powerup {
    int time;

    public Powerup(int time) {
        this.time = time;
    }

    // ChatGPT assisted in writing this code
    public void releasePaddlePowerup(Paddle myPaddle) {
        myPaddle.changePaddleWidth(PADDLE_WIDTH * 2);
        Timeline endPowerup = new Timeline(
                new KeyFrame(Duration.seconds(time), event -> myPaddle.changePaddleWidth(PADDLE_WIDTH))
        );
        endPowerup.setCycleCount(1);
        endPowerup.play();
    }

    public void handleNewBouncerOutOfBounds(Group root, Bouncer myNewBouncer) {

    }

    /*public void addBouncer() {
        return new Bouncer();
    }

     */

}
