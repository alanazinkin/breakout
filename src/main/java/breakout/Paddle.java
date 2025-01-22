package breakout;

import javafx.scene.shape.Rectangle;

import static breakout.Main.*;

public class Paddle {
    private Rectangle myPaddle;

    public Paddle(double x, double y, double width, double height) {
        myPaddle = new Rectangle(x, y, width, height);
    }

    public void keepInBounds(int screenWidth) {
        if (myPaddle.getX() <= 0) {
            myPaddle.setX(0);
        }
        if (myPaddle.getX()  >= screenWidth - myPaddle.getWidth()) {
            myPaddle.setX(screenWidth - myPaddle.getWidth());
        }
    }

    public void changePaddleWidth(int width) {
        myPaddle.setWidth(width);
    }

    public void resetPaddle() {
        myPaddle.setX(SCREEN_WIDTH / 2 - PADDLE_WIDTH/ 2);
        myPaddle.setY(SCREEN_HEIGHT / 2 + 100);
    }

    public Rectangle getMyPaddle() {
        return myPaddle;
    }

}
