package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

// bouncer class inspired from bounce lab
public class Bouncer extends Circle {
    private Circle myBouncer;
    private int size;
    private int myXSpeed;
    private int myYSpeed;
    private int myXDirection;
    private int myYDirection;

    final static int SPACE = 2;
    final static int EXTRA_SPACE = 1;


    public Bouncer(double centerX, double centerY, int size, Color color, int myXSpeed,
                   int myYSpeed, int myXDirection, int myYDirection) {
        myBouncer = new Circle(centerX, centerY, size, color);
        myBouncer.setCenterX(centerX);
        myBouncer.setCenterY(centerY);
        this.size = size;
        this.myXSpeed = myXSpeed;
        this.myYSpeed = myYSpeed;
        this.myXDirection = myXDirection;
        this.myYDirection = myYDirection;
    }

    public void move(double elapsedTime) {
        myBouncer.setCenterX(myBouncer.getCenterX() + this.myXSpeed * this.myXDirection * elapsedTime);
        myBouncer.setCenterY(myBouncer.getCenterY() + this.myYSpeed * this.myYDirection * elapsedTime);
    }

    public void bounce(int screenSize, int bouncerSize) {
        if (myBouncer.getCenterX() >= screenSize - bouncerSize || myBouncer.getCenterX() <= 0 + bouncerSize) {
            myXDirection *= -1;
        }
        if (myBouncer.getCenterY() >= screenSize - bouncerSize || myBouncer.getCenterY() <= 0 + bouncerSize) {
            myYDirection *= -1;
        }
    }

    public void paddleIntersect(Rectangle paddle, int size) {
        Shape paddleIntersection = Shape.intersect(myBouncer, paddle);
        if (paddleIntersection.getBoundsInLocal().getWidth() != -1) {
            myYDirection *= -1;
        }
    }

    public void keepWithinFrame(int screenSize, int bouncerSize) {
        if (myBouncer.getCenterY() >= screenSize - bouncerSize - EXTRA_SPACE) {
            myBouncer.setCenterX(screenSize / 2 - bouncerSize / 2);
            myBouncer.setCenterY(screenSize / 2 - bouncerSize / 2 + 60);
            myXDirection = 1;
            myYDirection = -1;
        }
    }

    public void resetBouncer(int screenSize, int bouncerSize) {
        myBouncer.setCenterX(screenSize / 2 - bouncerSize / 2);
        myBouncer.setCenterY(screenSize / 2 - bouncerSize / 2 + 60);
        myXDirection = 1;
        myYDirection = -1;
    }


    public boolean outTheBounds(int screenSize, int bouncerSize) {
        return (myBouncer.getCenterY() >= screenSize - bouncerSize - SPACE);
    }

    public void setXDirection(int direction) {
        myXDirection = direction;
    }

    public void setYDirection(int direction) {
        myYDirection = direction;
    }

    public int getXDirection() {
        return this.myXDirection;
    }

    public int getYDirection() {
        return this.myYDirection;
    }

    public Circle getBouncer() {
        return this.myBouncer;
    }

    public int getMySize() {
        return size;
    }

}
