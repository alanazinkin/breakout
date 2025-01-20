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
    private int row;
    private boolean isInCooldown = false;
    private double cooldownTime;

    public Block(int myX, int myY, double length, double height, Color color, int type, int row) {
        myBlock = new Rectangle(myX, myY, length, height);
        myBlock.setFill(color);
        setType(type);
        setRow(row);
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
    public void setType(int type) {
        this.type = type;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public boolean isInCooldown() {
        return isInCooldown;
    }

    public void startCooldown(double time) {
        isInCooldown = true;
        cooldownTime = time;
    }

    public void updateCooldown(double elapsedTime) {
        if (isInCooldown) {
            cooldownTime -= elapsedTime;
            if (cooldownTime <= 0) {
                isInCooldown = false;
            }
        }
    }

}
