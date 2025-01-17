package breakout;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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

    public Rectangle getPaddle() {
        return Paddle;
    }

}
