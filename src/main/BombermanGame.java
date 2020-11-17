package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BombermanGame extends Application {
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        GameManagement.init();
        Scene scene = GameManagement.getScene();
        stage.setScene(scene);
        stage.show();
    }
}
