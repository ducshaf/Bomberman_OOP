package main;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import main.entities.staticEntities.Grass;
import main.gameplay.inputHandler.InputManager;
import main.entities.Entity;
import main.gameplay.map.MapGenerator;
import main.graphics.Layer;
import main.gui.App;
import main.gui.UI;
import main.utils.Utils;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;


public class GameManagement {
    static Stage primaryStage = BombermanGame.getPrimaryStage();
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;

    private static AnimationTimer timer;
    private static long currentGameTime = 0;
    private static long startNanoTime;
    public static boolean isPaused = false;

    public static Vector<Entity> entities = new Vector<>();
    public static Vector<Entity> mobileEntities = new Vector<>();
    public static Vector<Entity> bombs = new Vector<>();

    public static boolean isBlind = false;
    public static boolean isFreeze = false;

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static Entity getPlayer() {
        return mobileEntities.get(0);
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
                    gc.clearRect(0, 0, Utils.WIDTH, Utils.HEIGHT);
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
        Layer.drawMobileLayer(bombs);
        Layer.drawOverlay();
        if (isBlind) Layer.blind();
        if (isFreeze) Layer.freezeTime();
    }

// update game logic, xử lí input từ bàn phím, update entities.
    public static void update() {
        for (Iterator<Entity> e = bombs.iterator(); e.hasNext();) {
            Entity bomb = e.next();
            bomb.update();
            if (!bomb.isAlive()) {
                e.remove();
            }
        }

        for (Iterator<Entity> e = mobileEntities.iterator(); e.hasNext();) {
            Entity mob = e.next();
            mob.update();
            if (!mob.isAlive()) {
                e.remove();
            }
        }

        for (ListIterator<Entity> e = entities.listIterator(); e.hasNext();) {
            Entity staticEntity = e.next();
            staticEntity.update();
            if (!staticEntity.isAlive()) {
                e.set(new Grass(staticEntity.getTileX(), staticEntity.getTileY(), null));
            }
        }
    }

    public static void init() {
        root = new Group();
        s = new Scene(root, Utils.WIDTH, Utils.HEIGHT);
        c = new Canvas(Utils.WIDTH, Utils.HEIGHT);
        isPaused = false;

        s.getStylesheets().add("./utils/style.css");

        root.getChildren().addAll(c, UI.menu);
        gc = c.getGraphicsContext2D();
        Layer.setGraphicsContext(gc);
        InputManager.keyboardHandle(s);
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
