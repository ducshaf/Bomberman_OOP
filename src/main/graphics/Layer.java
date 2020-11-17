package main.graphics;

import com.sun.prism.Graphics;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;

import java.util.Vector;

public class Layer {
    static Image GROUND = new Image("/ground.png");
    static Image OVERLAY = new Image("/overlay.png");

    public static void drawGroundLayer(GraphicsContext gc) {
        // 1.6 là tỉ lệ scale mặc định để màn hình được HD (1366x768) khoảng trống còn lại sẽ để dành cho UI.
        gc.drawImage(GROUND, 0, 0, 608 * 1.6, 480 * 1.6);
    }

    public static void drawOverlay(GraphicsContext gc) {
        gc.drawImage(OVERLAY, 0, 0, 608 * 1.6, 480 * 1.6);
    }
    public static void drawMobileLayer(Vector<Entity> entities, GraphicsContext gc) {

    }

}
