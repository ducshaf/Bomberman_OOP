package main;

import com.sun.prism.Graphics;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import main.entities.Entity;
import main.gameplay.map.RandomMapGenerator;
import main.graphics.Layer;

import java.util.Vector;


public class GameManagement {
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;

    public static final int CANVAS_WIDTH = 1366;
    public static final int CANVAS_HEIGHT = 768;

    private static long currentGameTime = 0;
    private static long startNanoTime;

    public static Vector<Entity> entities = new Vector<Entity>();

    public static Scene getScene() {
        return s;
    }

    public static long getCurrentGameTime() {
        return currentGameTime;
    }

    /**
     * Cách giữ current time khi pause:
     * - Lấy system.nanoTime() khi pause
     * - Cộng hiệu của currentTime và nanoTime() bên trên vào startNanoTime.
     */

    public static void start() {
        startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ((l - startNanoTime) / 100000000 > currentGameTime) {            // đếm thời gian từ lúc đầu
                    ++currentGameTime;                                              // tăng mỗi 0.1s
                    System.out.println(currentGameTime);                            // thỉnh thoảng có hơi lag?
                }
                gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                update();
                render();
            }
        }.start();
    }

// Render layer ground -> entities -> overlay.
    private static void render() {
        Layer.drawGroundLayer(gc);
        for (Entity e : entities) {
            e.render(gc);
        }
        Layer.drawOverlay(gc);
    }

// update game logic, xử lí input từ bàn phím, update entities.
    public static void update() {
    }

    public static void init() {
        root = new Group();
        s = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        c = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();

        RandomMapGenerator.generateMap();

        // start game loop.
        start();
    }

}
