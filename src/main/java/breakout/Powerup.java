package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.HashSet;

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

    public void releaseBouncerPowerup(HashSet<Bouncer> myActiveBouncers, double initialYspeed) {
        speedUpBouncers(myActiveBouncers);
        Timeline endPowerup = new Timeline(
                new KeyFrame(Duration.seconds(time), event -> slowDownBouncers(myActiveBouncers, initialYspeed))
        );
        endPowerup.setCycleCount(1);
        endPowerup.play();
    }

    public void speedUpBouncers(HashSet<Bouncer> myActiveBouncers) {
        for (Bouncer bouncer : myActiveBouncers) {
            bouncer.setYSpeed((bouncer.getYSpeed() * 1.5));
        }
    }

    public void slowDownBouncers(HashSet<Bouncer> myActiveBouncers, double initialYSpeed) {
        for (Bouncer bouncer : myActiveBouncers) {
            bouncer.setYSpeed(initialYSpeed);
        }
    }

    /*public void addBouncer() {
        return new Bouncer();
    }

     */

}
