package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.graphics.Layer;

public class Inversion extends StatusEffect {
    public Inversion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
    }

    @Override
    public void update() { // 5s
        if (duration > 0) {
            --duration;
            // invert input control
        } else if (isActive) {
            isActive = false;
        }
    }
}
