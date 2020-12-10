package main.entities.statusEffect;

import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class IncrementBombNumber extends StatusEffect {
    public IncrementBombNumber(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.bombQuantityUp);
    }

    public IncrementBombNumber(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        player.increaseBombQuantity();
    }

    @Override
    public void update() {
        isActive = false;
    }
}
