package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class Heal extends StatusEffect {
    public Heal(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.heal);
    }

    public Heal(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        player.increaseLives();
    }

    @Override
    public void update() {
        isActive = false;
    }
}
