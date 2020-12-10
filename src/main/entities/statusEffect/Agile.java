package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class Agile extends StatusEffect {
    public Agile(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.agile);
    }

    public Agile(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        player.increaseSpeed();
    }

    @Override
    public void update() {
        isActive = false;
    }
}
