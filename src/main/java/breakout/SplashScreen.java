package breakout;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class SplashScreen {
    public SplashScreen() {
    }

    //TODO: make font size a magic number
    public Scene showSplashScreen(Stage primaryStage, Level myLevel) {
        primaryStage.setTitle("New Level Splash Screen");
        int currentLevel = myLevel.getLevel() - 1;
        Text nextLevelText = new Text("Level " + currentLevel + " Complete!");
        nextLevelText.setFont(Font.font(24));


        StackPane layout = new StackPane();
        layout.getChildren().add(nextLevelText);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }


    public void closeSplashScreenAndResumePlay(Stage stage, Timeline timeline) {
        stage.close();
        timeline.play();
    }


    public void handleSplashScreenEvent(Scene levelScene, Stage newLevelStage, Timeline animation) {
        levelScene.setOnKeyPressed(event -> {
            closeSplashScreenAndResumePlay(newLevelStage, animation);
        });
        levelScene.setOnMouseClicked(event -> {
            closeSplashScreenAndResumePlay(newLevelStage, animation);
        });
        newLevelStage.setOnCloseRequest((WindowEvent event) -> {
            closeSplashScreenAndResumePlay(newLevelStage, animation);
        });
    }
}


