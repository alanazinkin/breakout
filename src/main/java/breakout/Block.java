package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block {
    private Rectangle myBlock;
    private int myType;
    private int myRow;
    private boolean isInCooldown = false;
    private double myCooldownTime;

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
        return myType;
    }
    public void setType(int type) {
        myType = type;
    }

    public void setRow(int row) {myRow = row;
    }

    public int getRow() {
        return myRow;
    }

    // cooldown idea from: https://plarium.com/en/glossary/cooldown/#:~:text=The%20cooldown%20definition%20in%20gaming,it%20fair%20and%20more%20strategic.
    public boolean isInCooldown() {
        return isInCooldown;
    }

    public void startCooldown(double time) {
        isInCooldown = true;
        myCooldownTime = time;
    }

    public void updateCooldown(double elapsedTime) {
        if (isInCooldown) {
            myCooldownTime -= elapsedTime;
            if (myCooldownTime <= 0) {
                isInCooldown = false;
            }
        }
    }


}
