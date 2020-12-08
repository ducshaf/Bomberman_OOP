package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.graphics.Sprite;
import main.utils.Utils;

public abstract class Entity {
    protected double x;
    protected double y;
    protected Image img;
    protected boolean isAlive = true;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = (xUnit + 2) * 32 * 1.6;
        this.y = (yUnit + 3) * 32 * 1.6;
        this.img = img;
    }

    public Entity() {
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y, 32*1.6, 32*1.6);            // 1.6 là tỉ lệ phóng to mặc định
    }                                                             // 1.8 để chiều dài dài hơn 1 chút cho đẹp thôi :v

    public abstract void update();

    public int setBombTileX() {
        return (int) (Utils.getPreciseDouble(x/1.6 - 32*2 + 16)/32);
    }

    public int setBombTileY() {
        return (int) (Utils.getPreciseDouble(y/1.6 - 32*3 + 16)/32);
    }

    public int getTileX() {
        return (int) (Utils.getPreciseDouble(x/1.6 - 32*2)/32);
    }

    public int getTileY() {
        return (int) (Utils.getPreciseDouble(y/1.6 - 32*3)/32);
    }

    public double getX() {
        return Utils.getPreciseDouble(x);
    }

    public double getY() {
        return Utils.getPreciseDouble(y);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }
}
