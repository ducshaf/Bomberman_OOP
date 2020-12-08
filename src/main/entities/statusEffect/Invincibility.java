package main.entities.statusEffect;

import javafx.scene.image.Image;

public class Invincibility extends StatusEffect {
    public Invincibility(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Invincibility() {
        super();
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
            // invincibility
        } else if (isActive) {
            isActive = false;
        }
    }
}
