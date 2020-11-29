package main.entities.statusEffect;

import javafx.scene.image.Image;

public class TheForce extends StatusEffect {
    // Sửa code phần grid của player
    public TheForce(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
    }

    @Override
    public void update() {
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
        }
    }

}
