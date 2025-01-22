package breakout;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class SplashScreen {
    public SplashScreen() {
    }

    //TODO: make font size a magic number
    public Scene showSplashScreen(Stage primaryStage, String title, String text) {
        primaryStage.setTitle(title);
        Text nextLevelText = new Text(text);
        nextLevelText.setFont(Font.font(24));
        nextLevelText.setTextAlignment(TextAlignment.CENTER);

        StackPane layout = new StackPane();
        layout.getChildren().add(nextLevelText);

        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    public void handleSplashScreenEvent(Scene myStage, Stage newStage, Timeline animation) {
        myStage.setOnKeyPressed(event -> {
            closeSplashScreenAndResumePlay(newStage, animation);
        });
        myStage.setOnMouseClicked(event -> {
            closeSplashScreenAndResumePlay(newStage, animation);
        });
        newStage.setOnCloseRequest((WindowEvent event) -> {
            closeSplashScreenAndResumePlay(newStage, animation);
        });
    }

    public void closeSplashScreenAndResumePlay(Stage stage, Timeline timeline) {
        stage.close();
        timeline.play();
    }
}


