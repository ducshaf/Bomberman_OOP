package main.entities.statusEffect;

import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class IncrementBombLevel extends StatusEffect {
    public IncrementBombLevel(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bombQualityUp);
    }

    public IncrementBombLevel(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        player.increaseBombQuality();
    }

    @Override
    public void update() {
        isActive = false;
    }
}
