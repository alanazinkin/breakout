package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

import java.lang.reflect.Array;

import static javafx.scene.paint.Color.*;

public class Block {
    private Rectangle myBlock;
    private int type;

    public Block(int myX, int myY, double length, double height, Color color, int type) {
        myBlock = new Rectangle(myX, myY, length, height);
        myBlock.setFill(color);
        this.type = setType(type);
    }

    public String toString() {
        return myBlock.toString();
    }

    public Rectangle getBlock() {
        return myBlock;
    }

    public void setColor(Color color) {
        myBlock.setFill(color);
    }

    public int getType() {
        return type;
    }
    public int setType(int type) {
        this.type = type;
        return type;
    }
}
