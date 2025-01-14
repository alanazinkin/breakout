package breakout;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Paddle {
    private Rectangle Paddle;
    private int width;
    private int height;

    public Paddle(double x, double y, double width, double height) {
        Paddle = new Rectangle(x, y, width, height);
    }

    public void keepInBounds(int size) {
        if (Paddle.getX() <= 0) {
            Paddle.setX(0);
        }
        if (Paddle.getX() >= size - width) {
            Paddle.setX(size - height);
        }
    }

    public Rectangle getPaddle() {
        return Paddle;
    }
}
