package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.mobileEntities.Bomber;
import main.gameplay.inputHandler.InputManager;
import main.graphics.Layer;
import main.graphics.Sprite;

public class Inversion extends StatusEffect {
    public Inversion(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.inversion);
    }

    public Inversion(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 1200;
        isActive = true;
        InputManager.setInverted(true);
        System.out.println("input reverse");
    }

    @Override
    public void update() { // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            InputManager.setInverted(false);
        }
    }
}
