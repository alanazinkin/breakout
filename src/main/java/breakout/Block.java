package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

import java.lang.reflect.Array;

public class Block {
    private Rectangle myBlock;

    public Block(int myX, int myY, double length, double height, Color color) {
        myBlock = new Rectangle(myX, myY, length, height);
        myBlock.setFill(color);
    }

    public String toString() {
        return myBlock.toString();
    }

    public Rectangle getBlock() {
        return myBlock;
    }
}
