package breakout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Level {
    ArrayList<Block> myBlocks;
    ArrayList<Block> myHitBlocks = new ArrayList<>();
    int myLevel;

    Level(int myLevel) {
        this.myLevel = myLevel;
    }


    public void initBlocks(String[] myLevelFiles, Color[] colorMapping, int blockWidth, int blockHeight) {
        String levelFile = myLevelFiles[myLevel];
        File myLayoutFile = new File(levelFile);
        myBlocks = new ArrayList<>();
        readLevelFile(myLayoutFile, colorMapping, blockWidth, blockHeight);
    }

    // for future projects, this method should be broken down and should leverage inheritance
    public void readLevelFile(File myLayoutFile, Color[] colorMapping, int blockWidth, int blockHeight) {
        try {
            Scanner s = new Scanner(myLayoutFile);
            int j = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] split = line.split(" ");
                for (int i = 0; i < split.length; i++) {
                    switch(split[i]) {
                        case "0": continue;
                        case "1": myBlocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[1], 1, j));
                            break;
                        case "2": myBlocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[2], 2, j));
                            break;
                        case "3": myBlocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[3], 3, j));
                            break;
                        case "4": myBlocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[4], 4, j));
                            break;
                        case "5": myBlocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[5], 5, j));
                            break;
                        case "6": myBlocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[6], 6, j));
                            break;
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
    public void addBlocksToScene(Group root) {
        for (Block myBlock : myBlocks) {
            root.getChildren().add(myBlock.getBlock());
        }
    }

    public void endLevel(Group root) {
        root.getChildren().clear();
        clearBlocksList();
        clearHitBlocksList();
        myLevel++;
    }

    public void clearBlocksList() {
        this.myBlocks.clear();
    }

    public void clearHitBlocksList() {
        myHitBlocks.clear();
    }

    public boolean allBlocksHit() {
        return (myHitBlocks.size() == myBlocks.size());
    }

    public void setLevel(int myLevel) {
        this.myLevel = myLevel;
    }

    public int getNumBlocks() {
        return this.myBlocks.size();
    }

    public List<Block> getBlocksList() {
        return this.myBlocks;
    }

    public void addHitBlocks(Block hitBlock) {
        myHitBlocks.add(hitBlock);
    }

    public int getLevel() {
        return this.myLevel;
    }


}
