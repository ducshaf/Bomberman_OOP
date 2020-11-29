package main.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameManagement;
import main.entities.Entity;

import java.util.Vector;

public class Layer {
    static final int TIME_X = 1085;
    static final int TIME_Y = 80;

    static final int FIRST_X = 1020;
    static final int FIRST_Y = 430;

    static final int SECOND_X = 1120;
    static final int SECOND_Y = 530;

    static final int THIRD_X = 1220;
    static final int THIRD_Y = 630;

    static final int Square = 100;

    static Image GROUND;
    static Image OVERLAY;
    static Image PAUSE = new Image("./UI/optionPanel.png");

    static GraphicsContext gc;

    public static void setStage(int id) {
        switch (id) {
            case 0:
                GROUND = new Image("/ground.png");
                OVERLAY = new Image("/overlay.png");
                break;
            case 1:
                GROUND = new Image("./levels/snow_ground.png");
                OVERLAY = new Image("./levels/snow_overlay.png");
                break;
        }
    }

    public static void drawGroundLayer() {
        // 1.6 là tỉ lệ scale mặc định để màn hình được HD (1366x768) khoảng trống còn lại sẽ để dành cho UI.
        gc.drawImage(GROUND, 0, 0, 1366, 768);
    }

    public static void drawOverlay() {
        gc.drawImage(OVERLAY, 0, 0, 608 * 1.6, 480 * 1.6);
        gc.setFont(new Font(40));
        gc.fillText(timeToString(GameManagement.getCurrentGameTime()), TIME_X, TIME_Y);
        drawStatusEffectOverlay();
    }
    public static void drawMobileLayer(Vector<Entity> entities) {
        for (Entity e : entities) {
            e.render(gc);
        }
    }

    public static void drawOptionUI(GraphicsContext gc) {
        gc.drawImage(PAUSE, 560, 220);
    }

    public static String timeToString(long time) {
        time /= 60;
        int min = (int) (time/60);
        int sec = (int) (time - min*60);
        String m = (min < 10 ? "0" : "") + min;
        String s = (sec < 10 ? "0" : "") + sec;
        return m + " : " + s;
    }

    public static void drawStatusEffectOverlay() {
        gc.setFill(Color.rgb(41, 41, 41, 0.5));
        if (true) {
            gc.fillRect(FIRST_X, FIRST_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(FIRST_X, SECOND_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(FIRST_X, THIRD_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(SECOND_X, FIRST_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(SECOND_X, SECOND_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(SECOND_X, THIRD_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(THIRD_X, FIRST_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(THIRD_X, SECOND_Y, Square, Square);
        }
        if (true) {
            gc.fillRect(THIRD_X, THIRD_Y, Square, Square);
        }
    }

    public static void setGraphicsContext(GraphicsContext gc) {
        Layer.gc = gc;
    }

    public static void blind() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1000, 768);
    }

    public static void freezeTime() {
        gc.setFill(Color.rgb(48, 255, 255, 0.5));
        gc.fillRect(0, 0, 1000, 768);
    }
}
