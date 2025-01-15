package breakout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;

public class Level {
    ArrayList<Block> blocks;
    int myLevel;

    Level(int myLevel) {
        this.myLevel = myLevel;
    }


    public void initBlocks(int blockWidth, int blockHeight, Color blockColor) {
        String levelFile = "src/main/resources/lvl_01.txt";
        File myLayoutFile = new File(levelFile);
        blocks = new ArrayList<Block>();

        try {
            Scanner s = new Scanner(myLayoutFile);
            int j = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] split = line.split(" ");
                for (int i = 0; i < split.length; i++) {
                    switch(split[i]) {
                        case "0": continue;
                        case "1": blocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, blockColor));
                    }
                }
                j += 1;
            }
        }
        catch (Exception e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public void startLevel(Group root) {
        for (Block myBlock : this.blocks) {
            System.out.println(myBlock.toString());
            root.getChildren().add(myBlock.getBlock());
        }
    }

    public void endLevel(Group root) {
        root.getChildren().clear();
    }

    public void setLevel(int myLevel) {
        this.myLevel = myLevel;
    }

    public int getNumBlocks() {
        return this.blocks.size();
    }

    public List<Block> getBlocksList() {
        return this.blocks;
    }

    public int getLevel() {
        return this.myLevel;
    }

}
