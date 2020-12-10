package main.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameManagement;
import main.entities.Entity;
import main.entities.mobileEntities.Bomber;
import main.utils.Utils;

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

    static final int SQUARE = 100;

    static final int first_X = 1120;
    static final int second_x = 1184;
    static final int third_x = 1250;

    static final int first_y = 128;
    static final int second_y = 203;

    static final int square = 40;

    static Image GROUND;
    static Image OVERLAY;
    static Image PAUSE = new Image("./UI/optionPanel.png");
    static Image GAMEOVER = new Image("./UI/gameOverPanel.png");
    static Image VICTORY = new Image("./UI/winPanel.png");

    static GraphicsContext gc;

    public static void setStage(int id) {
        switch (id) {
            case 0:
                GROUND = new Image("/levels/grass_ground.png");
                OVERLAY = new Image("/levels/grass_overlay.png");
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
        drawMainUI();
    }
    public static void drawMobileLayer(Vector<Entity> entities) {
        for (Entity e : entities) {
            e.render(gc);
        }
    }

    public static void drawOptionUI(GraphicsContext gc, String id) {
        if (id.equals("pause")) gc.drawImage(PAUSE, 560, 220);
        else if (id.equals("gameOver")) gc.drawImage(GAMEOVER, 560, 220);
        else if (id.equals("victory")) gc.drawImage(VICTORY, 560, 220);
    }

    public static String timeToString(long time) {
        time /= 60;
        int min = (int) (time/60);
        int sec = (int) (time - min*60);
        String m = (min < 10 ? "0" : "") + min;
        String s = (sec < 10 ? "0" : "") + sec;
        return m + " : " + s;
    }

    public static void drawMainUI() {
        if (Bomber.lives >= 1) {
            gc.drawImage(Sprite.red_heart, first_X, second_y, square, square);
        } else {
            gc.drawImage(Sprite.white_heart, first_X, second_y, square, square);
        }
        if (Bomber.lives >= 2) {
            gc.drawImage(Sprite.red_heart, second_x, second_y, square, square);
        } else {
            gc.drawImage(Sprite.white_heart, second_x, second_y, square, square);
        }
        if (Bomber.lives == 3) {
            gc.drawImage(Sprite.red_heart, third_x, second_y, square, square);
        } else {
            gc.drawImage(Sprite.white_heart, third_x, second_y, square, square);
        }

        gc.drawImage(Sprite.getNumber(Bomber.bombQuantity*60), first_X+10, first_y+10, square-20, square-20);
        gc.drawImage(Sprite.getNumber((int) (Utils.getPreciseDouble(Bomber.speed)*600)), second_x+10, first_y+10, square-20, square-20);
        gc.drawImage(Sprite.getNumber(Bomber.bombQuality*60), third_x+10, first_y+10, square-20, square-20);
    }

    public static void drawStatusEffectOverlay() {
        gc.setFill(Color.rgb(41, 41, 41, 0.5));
        if (!Bomber.status.get("fierce").isActive()) {
            gc.fillRect(FIRST_X, FIRST_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("fierce").getDuration()), FIRST_X+25, FIRST_Y+25, 50, 50);
        }
        if (!Bomber.status.get("force").isActive()) {
            gc.fillRect(FIRST_X, SECOND_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("force").getDuration()), FIRST_X+25, SECOND_Y+25, 50, 50);
        }
        if (!Bomber.status.get("blind").isActive()) {
            gc.fillRect(FIRST_X, THIRD_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("blind").getDuration()), FIRST_X+25, THIRD_Y+25, 50, 50);
        }
        if (!Bomber.status.get("freeze").isActive()) {
            gc.fillRect(SECOND_X, FIRST_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("freeze").getDuration()), SECOND_X+25, FIRST_Y+25, 50, 50);
        }
        if (!Bomber.status.get("time").isActive()) {
            gc.fillRect(SECOND_X, SECOND_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("time").getDuration()), SECOND_X+25, SECOND_Y+25, 50, 50);
        }
        if (!Bomber.status.get("invert").isActive()) {
            gc.fillRect(SECOND_X, THIRD_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("invert").getDuration()), SECOND_X+25, THIRD_Y+25, 50, 50);
        }
        if (!Bomber.status.get("invincible").isActive()) {
            gc.fillRect(THIRD_X, FIRST_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("invincible").getDuration()), THIRD_X+25, FIRST_Y+25, 50, 50);
        }
        if (!Bomber.status.get("percolate").isActive()) {
            gc.fillRect(THIRD_X, SECOND_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("percolate").getDuration()), THIRD_X+25, SECOND_Y+25, 50, 50);
        }
        if (!Bomber.status.get("slow").isActive()) {
            gc.fillRect(THIRD_X, THIRD_Y, SQUARE, SQUARE);
        } else {
            gc.drawImage(Sprite.getNumber(Bomber.status.get("slow").getDuration()), THIRD_X+25, THIRD_Y+25, 50, 50);
        }
    }

    public static void setGraphicsContext(GraphicsContext gc) {
        Layer.gc = gc;
    }

    public static void blind() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 973, 768);
    }

    public static void freezeTime() {
        gc.setFill(Color.rgb(0, 165, 255, 0.3));
        gc.fillRect(0, 0, 973, 768);
    }
}
