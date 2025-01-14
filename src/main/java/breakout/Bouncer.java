package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

// bouncer class inspired from bounce lab
public class Bouncer extends Circle {
    private Circle myBouncer;
    private double radius;
    private int myXSpeed;
    private int myYSpeed;
    private int myXDirection;
    private int myYDirection;


    public Bouncer(double centerX, double centerY, double radius, Color color, int myXSpeed,
                   int myYSpeed, int myXDirection, int myYDirection) {
        myBouncer = new Circle(centerX, centerY, radius, color);
        myBouncer.setCenterX(centerX);
        myBouncer.setCenterY(centerY);
        this.radius = radius;
        this.myXSpeed = myXSpeed;
        this.myYSpeed = myYSpeed;
        this.myXDirection = myXDirection;
        this.myYDirection = myYDirection;
    }

    public void move(double elapsedTime) {
        myBouncer.setCenterX(myBouncer.getCenterX() + this.myXSpeed * this.myXDirection * elapsedTime);
        myBouncer.setCenterY(myBouncer.getCenterY() + this.myYSpeed * this.myYDirection * elapsedTime);
    }

    public void bounce(double elapsedTime, int screenSize, int bouncerSize) {
        if (myBouncer.getCenterX() >= screenSize - bouncerSize || myBouncer.getCenterX() <= 0 + bouncerSize) {
            myXDirection *= -1;
        }
        if (myBouncer.getCenterY() >= screenSize - bouncerSize || myBouncer.getCenterY() <= 0 + bouncerSize) {
            myYDirection *= -1;
        }
    }

    public void paddleIntersect(Rectangle paddle) {
        Shape paddleIntersection = Shape.intersect(myBouncer, paddle);
        if (paddleIntersection.getBoundsInLocal().getWidth() != -1) {
            myYDirection *= -1;
        }
    }

    public void setYDirection(int direction) {
        myYDirection = direction;
    }

    public int getYDirection() {
        return this.myYDirection;
    }


    public Circle getBouncer() {
        return this.myBouncer;
    }

}
