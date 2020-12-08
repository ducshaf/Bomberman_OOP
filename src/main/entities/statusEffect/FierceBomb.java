package main.entities.statusEffect;

import javafx.scene.image.Image;

public class FierceBomb extends StatusEffect {
    int oldBombLevel;
    public FierceBomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public FierceBomb() {
    }

    @Override
    public void init(int oldBombLevel) {
        duration += 600;
        isActive = true;
        if (oldBombLevel != 15){
            this.oldBombLevel = oldBombLevel;
        }
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

    public int getOldBombLevel() {
        return oldBombLevel;
    }
}
