package main.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = (int) ((xUnit + 2) * Sprite.SCALED_SIZE * 1.6);
        this.y = (int) ((yUnit + 3) * Sprite.SCALED_SIZE * 1.6);
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y, 32*1.6, 32*1.8);            // 1.6 là tỉ lệ phóng to mặc định
    }                                                              // 1.8 để chiều dài dài hơn 1 chút cho đẹp thôi :v
    public abstract void update();

    public int getTileX() {
        return (int) (x/1.6 - 32*2)/32;
    }

    public int getTileY() {
        return (int) (y/1.6 - 32*3)/32;
    }
}
