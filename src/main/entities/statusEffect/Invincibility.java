package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class Invincibility extends StatusEffect {
    public Invincibility(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.invincibility);
    }

    public Invincibility(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        player.isinvul = true;
        System.out.println("Invincibility");
    }

    @Override
    public void update() { // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            player.isinvul = false;
        }
    }
}
