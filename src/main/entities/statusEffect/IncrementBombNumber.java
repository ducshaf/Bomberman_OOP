package main.entities.statusEffect;

import javafx.scene.image.Image;

public class IncrementBombNumber extends StatusEffect {
    public IncrementBombNumber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        //increase bomb number
    }

    @Override
    public void update() {
    }
}
