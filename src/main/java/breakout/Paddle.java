package breakout;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import static breakout.Main.*;

public class Paddle {
    private Rectangle Paddle;

    public Paddle(double x, double y, double width, double height) {
        Paddle = new Rectangle(x, y, width, height);
    }

    public void keepInBounds(int size) {
        if (Paddle.getX() <= 0) {
            Paddle.setX(0);
        }
        if (Paddle.getX()  >= size - Paddle.getWidth()) {
            Paddle.setX(size - Paddle.getWidth());
        }
    }

    public void changePaddleWidth(int width) {
        Paddle.setWidth(width);
    }

    public void resetPaddle() {
        Paddle.setX(SIZE / 2 - PADDLE_WIDTH/ 2);
        Paddle.setY(SIZE / 2 + 100);
    }

    public Paddle addPaddle(Group root) {
        Paddle myPaddle = new Paddle(SIZE / 2 - PADDLE_WIDTH/ 2, SIZE / 2 + 100, PADDLE_WIDTH, PADDLE_HEIGHT);
        root.getChildren().add(myPaddle.getPaddle());
        return myPaddle;
    }

    public Rectangle getPaddle() {
        return Paddle;
    }

}
