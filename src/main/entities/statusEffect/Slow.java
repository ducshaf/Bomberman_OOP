package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.graphics.Layer;

public class Slow extends StatusEffect {
    double playerSpeed;
    public Slow(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        duration += 300;
        isActive = true;
        // set playerSpeed
        // set player speed to half
    }

    @Override
    public void update() {  // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            // return player speed with playerSpeed
        }
    }

}
