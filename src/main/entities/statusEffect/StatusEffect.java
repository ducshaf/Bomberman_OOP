package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.Entity;

public abstract class StatusEffect extends Entity {
    // Tạo thêm phần input ấn số 1->9 đc 1 cái status effect để test
    static int duration = 0;
    static boolean isActive = false;

    public StatusEffect(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void init();

    public int getDuration() {
        return duration;
    }

    public boolean isActive() {
        return isActive;
    }

    public void disappear() {
        img = null;
    }
}
