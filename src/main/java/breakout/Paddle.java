package breakout;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static breakout.Main.PADDLE_WIDTH;
import static breakout.Main.SIZE;

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

    public void expandPaddleLength(int width) {
        Paddle.setWidth(width);
    }

    public void resetPaddle() {
        Paddle.setX(SIZE / 2 - PADDLE_WIDTH/ 2);
        Paddle.setY(SIZE / 2 + 100);
    }

    public Rectangle getPaddle() {
        return Paddle;
    }

}
