package breakout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Level {
    ArrayList<Block> blocks;
    ArrayList<Block> hitBlocks = new ArrayList<>();
    int myLevel;

    Level(int myLevel) {
        this.myLevel = myLevel;
    }


    public void initBlocks(String[] myLevelFiles, Color[] colorMapping, int blockWidth, int blockHeight) {
        String levelFile = myLevelFiles[myLevel];
        File myLayoutFile = new File(levelFile);
        blocks = new ArrayList<Block>();
        readLevelFile(myLayoutFile, colorMapping, blockWidth, blockHeight);
    }

    public String getLevelFile(String[] myLevelFiles, int level, String levelFile) {
        return (myLevelFiles[level]);
    }

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
                        case "1": blocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[1], 1, j));
                            break;
                        case "2": blocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[2], 2, j));
                            break;
                        case "3": blocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[3], 3, j));
                            break;
                        case "4": blocks.add(new Block(i * blockWidth, j * blockHeight, blockWidth, blockHeight, colorMapping[4], 4, j));
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
        for (Block myBlock : blocks) {
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
        this.blocks.clear();
    }

    public void clearHitBlocksList() {
        this.hitBlocks.clear();
    }

    public boolean allBlocksHit() {
        return (hitBlocks.size() == blocks.size());
    }

    public Text createLevelText(double xPosition, double yPosition, String font, int fontSize) {
        Text levelText = new Text(xPosition, yPosition, "Current Level: " + myLevel);
        levelText.setFill(Color.HOTPINK);
        Font f = Font.font(font, FontWeight.BOLD, fontSize);
        levelText.setFont(f);
        return levelText;
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

    public void addHitBlocks(Block hitBlock) {
        this.hitBlocks.add(hitBlock);
    }

    public int getLevel() {
        return this.myLevel;
    }

    public int getNumHitBlocks() {
        return this.hitBlocks.size();
    }

}
