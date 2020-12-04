package main;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import main.entities.Entity;
import main.gameplay.map.MapGenerator;
import main.graphics.Layer;
import main.gui.App;
import main.gui.UI;
import main.utils.Utils;

import java.util.ArrayList;
import java.util.Vector;


public class GameManagement {
    static Stage primaryStage = BombermanGame.getPrimaryStage();
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;

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

    public static Entity getStaticEntityAt(double x, double y) {
        int tileX = (int) Utils.getPreciseDouble((x/1.6 - 32*2)/32);
        int tileY = (int) Utils.getPreciseDouble((y/1.6 - 32*3)/32);
        return entities.get((tileY)*15 + (tileX));
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
        Layer.drawMobileLayer(mobileEntities);
        Layer.drawOverlay();
    }

// update game logic, xử lí input từ bàn phím, update entities.
    public static void update() {
        for (Entity e : mobileEntities) {
            e.update();
        }
    }

    public static void init() {
        root = new Group();
        s = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        c = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        isPaused = false;

        s.getStylesheets().add("./utils/style.css");

        root.getChildren().addAll(c, UI.menu);
        gc = c.getGraphicsContext2D();
        Layer.setGraphicsContext(gc);

        // generate a random map.
        MapGenerator.inputMap();
        // start game loop.
        start();
    }

    public static void pause() {
        isPaused = true;
        root.getChildren().add(UI.pause);
    }

    public static void resume() {
        root.getChildren().remove(UI.pause);
        isPaused = false;
    }

    public static void reset() {
        root.getChildren().remove(UI.pause);
        entities.clear();
        mobileEntities.clear();
        MapGenerator.addEntities();
        timer.stop();
        isPaused = false;
        start();
    }

    public static void exit() {
        timer.stop();
        entities.clear();
        mobileEntities.clear();
        App.init();
        Scene scene = App.getScene();
        primaryStage.setScene(scene);
    }
}
