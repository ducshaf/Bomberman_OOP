package main;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import main.entities.Entity;
import main.gameplay.map.MapGenerator;
import main.graphics.Layer;

import java.util.ArrayList;
import java.util.Vector;


public class GameManagement {
    static Stage primaryStage = BombermanGame.getPrimaryStage();
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;
    static Pane pausePane;

    public static final int CANVAS_WIDTH = 1366;
    public static final int CANVAS_HEIGHT = 768;

    private static AnimationTimer timer;
    private static long currentGameTime = 0;
    private static long startNanoTime;
    public static boolean isPaused = false;

    public static Vector<Entity> entities = new Vector<>();
    public static Vector<Entity> mobileEntities = new Vector<>();
    public static ArrayList<Entity> bombs = new ArrayList<>();

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static Scene getScene() {
        return s;
    }

    public static long getCurrentGameTime() {
        return currentGameTime;
    }

    public static void start() {
        currentGameTime = 0;
        startNanoTime = System.nanoTime();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ((l - startNanoTime) / (1000000000/60) > currentGameTime) {
                    if (isPaused) {
                        startNanoTime += (1000000000/60);
                    } else {
                        ++currentGameTime;
                        System.out.println(currentGameTime);
                    }
                }
                if (!isPaused){
                    gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                    update();
                    render();
                }
            }
        };
        timer.start();
    }

// Render layer ground -> entities -> overlay.
    private static void render() {
        Layer.drawGroundLayer();
        Layer.drawMobileLayer(entities);
        Layer.drawOverlay();
    }

// update game logic, xử lí input từ bàn phím, update entities.
    public static void update() {
    }

    public static void init() {
        root = new Group();
        s = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        c = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        isPaused = false;

        s.getStylesheets().add("./utils/style.css");

        Button menu = new Button("☰");
        menu.setLayoutX(1260);
        menu.setLayoutY(40);
        menu.setId("menu");
        menu.setOnAction(actionEvent -> pause());

        root.getChildren().addAll(c,menu);
        gc = c.getGraphicsContext2D();
        Layer.setGraphicsContext(gc);

        // generate a random map.
        MapGenerator.generateMap();
        // start game loop.
        start();
    }

    public static void pause() {
        isPaused = true;
        if (pausePane == null) {
            initPausePane();
        }
        root.getChildren().add(pausePane);
    }

    public static void initPausePane() {
        pausePane = new Pane();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Layer.drawOptionUI(graphicsContext);

        pausePane.setId("pausePane");
        pausePane.setOnMouseReleased(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            if (x < 560 || y < 220 || x > 806 || y > 550) {
                root.getChildren().remove(pausePane);
                isPaused = false;
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
            root.getChildren().remove(pausePane);
            isPaused = false;
        });

        Button restart = new Button("Restart");
        restart.setLayoutX(605);
        restart.setLayoutY(405);
        restart.setOnAction(actionEvent -> {
            root.getChildren().remove(pausePane);
            entities.clear();
            MapGenerator.addEntities();
            timer.stop();
            isPaused = false;
            start();
        });

        Button mainMenu = new Button("Main menu");
        mainMenu.setLayoutX(605);
        mainMenu.setLayoutY(455);
        mainMenu.setOnAction(actionEvent -> {
            entities.clear();
            App.init();
            Scene scene = App.getScene();
            primaryStage.setScene(scene);
        });

        pausePane.getChildren().addAll(canvas, sound, resume, restart, mainMenu);
    }
}
