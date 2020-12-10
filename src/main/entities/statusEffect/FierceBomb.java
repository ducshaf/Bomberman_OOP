package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class FierceBomb extends StatusEffect {
    public FierceBomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.fierce);
    }

    public FierceBomb(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        player.isfierce = true;
    }

    @Override
    public void update() {         // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            player.isfierce = false;
        }
    }
}
