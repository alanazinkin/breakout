package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

import java.lang.reflect.Array;

public class Block extends Rectangle {
    private Rectangle myBlock;

    public Block(int myX, int myY, double length, double height, Color color) {
        myBlock = new Rectangle(myX, myY, length, height);
        myBlock.setFill(color);
    }

    public static void initBlocks(Group root, ArrayList<Block> myBlocks, int numBlocks, int blockWidth, int blockHeight) {
        int j = 0;
        for (int i = 0; i < numBlocks; i++){
            Block myBlock = new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, Color.BLUE);
            myBlocks.add(myBlock);
            root.getChildren().add(myBlock.getBlock());
            j ++;
        }
    }

    public Rectangle getBlock() {
        return myBlock;
    }
}
