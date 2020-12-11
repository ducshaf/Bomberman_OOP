package main.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.BombermanGame;
import main.GameManagement;
import main.graphics.Layer;

import java.util.Random;

public class App {
    static Stage primaryStage = BombermanGame.getPrimaryStage();
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;

    static HBox chooseStage;

    public static final int CANVAS_WIDTH = 1366;
    public static final int CANVAS_HEIGHT = 768;

    static final Image MENU = new Image("./UI/Menu.jpg");


    public static Scene getScene() {
        return s;
    }

    public static void init() {
        root = new Group();
        s = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        c = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = c.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.drawImage(MENU,383,100);

        s.getStylesheets().add("./utils/style.css");

        levelSelection();

        Button singlePlayer = new Button("Start");
        singlePlayer.setId("1P");
        singlePlayer.setLayoutX(600);
        singlePlayer.setLayoutY(500);
        singlePlayer.setOnAction(actionEvent -> root.getChildren().add(chooseStage));

        Button exit = new Button("Exit");
        exit.setId("exit");
        exit.setLayoutX(600);
        exit.setLayoutY(600);
        exit.setOnAction(actionEvent -> System.exit(0));

        root.getChildren().addAll(c,singlePlayer, exit);
        primaryStage.setScene(s);
    }

    public static void levelSelection() {
        chooseStage = new HBox();

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.6);

        ColorAdjust colorAdjust_hover = new ColorAdjust();
        colorAdjust_hover.setBrightness(0);

        Canvas plain = new Canvas(CANVAS_WIDTH / 4.0, CANVAS_HEIGHT);
        plain.setEffect(colorAdjust);
        plain.setOnMouseReleased(mouseEvent -> {
            Layer.setStage(0);
            GameManagement.init();
            Scene scene = GameManagement.getScene();
            primaryStage.setScene(scene);
        });

        Canvas glacial = new Canvas(CANVAS_WIDTH / 4.0, CANVAS_HEIGHT);
        glacial.setEffect(colorAdjust);
        glacial.setOnMouseReleased(mouseEvent -> {
            Layer.setStage(1);
            GameManagement.init();
            Scene scene = GameManagement.getScene();
            primaryStage.setScene(scene);
        });

        Canvas random = new Canvas(CANVAS_WIDTH / 2.0, CANVAS_HEIGHT);
        random.setEffect(colorAdjust);
        random.setOnMouseReleased(mouseEvent -> {
            Random randomMap = new Random();
            int mapID = randomMap.nextInt(2);
            Layer.setStage(mapID);
            GameManagement.init();
            Scene scene = GameManagement.getScene();
            primaryStage.setScene(scene);
        });

        chooseStage.getChildren().addAll(plain, glacial, random);

        GraphicsContext gc1 = plain.getGraphicsContext2D();
        GraphicsContext gc2 = glacial.getGraphicsContext2D();
        GraphicsContext gc3 = random.getGraphicsContext2D();
        draw(gc1, gc2, gc3);

        chooseStage.getChildren().forEach((c) -> {
            c.setOnMouseExited(mouseEvent -> c.setEffect(colorAdjust));
            c.setOnMouseEntered(mouseEvent -> c.setEffect(colorAdjust_hover));
        });
    }

    public static void draw(GraphicsContext gc1, GraphicsContext gc2, GraphicsContext gc3) {
        gc1.drawImage(new Image("./UI/plain.png"), 1300, 100, CANVAS_WIDTH / 4.0, CANVAS_HEIGHT,
                0, 0, CANVAS_WIDTH / 4.0, CANVAS_HEIGHT);
        gc2.drawImage(new Image("./UI/glacial.png"),1200, 250, CANVAS_WIDTH / 4.0, CANVAS_HEIGHT,
                0, 0, CANVAS_WIDTH / 4.0, CANVAS_HEIGHT);
        gc3.drawImage(new Image("./UI/random.png"), 0, 0);
    }
}
