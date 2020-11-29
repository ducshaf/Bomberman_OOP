package main.entities.statusEffect;

import javafx.scene.image.Image;

public class FierceBomb extends StatusEffect {
    int oldBombLevel;
    public FierceBomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        // set old bomb level if player's bomb level is not 99.
    }

    @Override
    public void update() {         // 5s
        if (duration > 0) {
            --duration;
            // increase bomb level to 99
        } else if (isActive) {
            isActive = false;
            // set bomb level to old bomblevel.
        }
    }
}
