package breakout;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScreenText {

    public ScreenText() {

    }
    public javafx.scene.text.Text createText(String text, double xPosition, double yPosition, String font, int fontSize) {
        javafx.scene.text.Text myText = new javafx.scene.text.Text(xPosition, yPosition, text);
        myText.setFill(Color.HOTPINK);
        Font f = Font.font(font, FontWeight.BOLD, fontSize);
        myText.setFont(f);
        return myText;
    }
}
