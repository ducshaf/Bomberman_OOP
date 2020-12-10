package main.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import main.GameManagement;
import main.graphics.Layer;
import main.utils.Utils;

public class UI {
    public static Button menu;
    public static Pane pause;
    public static Pane gameOver;
    public static Pane victory;

    public static void init() {
        intButton();
        initPausePane();
        initGameOver();
        initVictory();
    }

    public static void intButton() {
        menu = new Button("☰");
        menu.setLayoutX(1260);
        menu.setLayoutY(40);
        menu.setId("menu");
        menu.setOnAction(actionEvent -> GameManagement.pause(0));
    }

    public static void initPausePane() {
        pause = new Pane();
        Canvas canvas = new Canvas(Utils.WIDTH, Utils.HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Layer.drawOptionUI(graphicsContext, "pause");

        pause.setId("pausePane");
        pause.setOnMouseReleased(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            if (x < 560 || y < 220 || x > 806 || y > 550) {
                GameManagement.resume();
            }
        });

        Button sound = new Button("\uD83D\uDD0A");
        sound.setId("soundButton");
        sound.setLayoutX(605);
        sound.setLayoutY(305);

        Button resume = new Button("Resume");
        resume.setLayoutX(605);
        resume.setLayoutY(355);
        resume.setOnAction(actionEvent -> {
            GameManagement.resume();
        });

        Button restart = new Button("Restart");
        restart.setLayoutX(605);
        restart.setLayoutY(405);
        restart.setOnAction(actionEvent -> {
            GameManagement.reset();
        });

        Button mainMenu = new Button("Main menu");
        mainMenu.setLayoutX(605);
        mainMenu.setLayoutY(455);
        mainMenu.setOnAction(actionEvent -> {
            GameManagement.exit();
        });

        pause.getChildren().addAll(canvas, sound, resume, restart, mainMenu);
    }

    public static void initGameOver() {
        gameOver = new Pane();
        Canvas canvas = new Canvas(Utils.WIDTH, Utils.HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Layer.drawOptionUI(graphicsContext, "gameOver");

        gameOver.setId("pausePane");

        Button restart = new Button("Restart");
        restart.setLayoutX(605);
        restart.setLayoutY(405);
        restart.setOnAction(actionEvent -> {
            GameManagement.reset();
        });

        Button mainMenu = new Button("Main menu");
        mainMenu.setLayoutX(605);
        mainMenu.setLayoutY(455);
        mainMenu.setOnAction(actionEvent -> {
            GameManagement.exit();
        });

        gameOver.getChildren().addAll(canvas, restart, mainMenu);
    }

    public static void initVictory() {
        victory = new Pane();
        Canvas canvas = new Canvas(Utils.WIDTH, Utils.HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Layer.drawOptionUI(graphicsContext, "victory");

        victory.setId("pausePane");

        Button restart = new Button("Choose Stage");
        restart.setLayoutX(605);
        restart.setLayoutY(405);
        restart.setOnAction(actionEvent -> {
            GameManagement.exit();
            App.root.getChildren().add(App.chooseStage);
        });

        Button mainMenu = new Button("Main menu");
        mainMenu.setLayoutX(605);
        mainMenu.setLayoutY(455);
        mainMenu.setOnAction(actionEvent -> {
            GameManagement.exit();
        });

        victory.getChildren().addAll(canvas, restart, mainMenu);
    }
}
