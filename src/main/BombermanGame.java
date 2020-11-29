package main;

import javafx.application.Application;
import javafx.stage.Stage;


public class BombermanGame extends Application {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        App.init();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
