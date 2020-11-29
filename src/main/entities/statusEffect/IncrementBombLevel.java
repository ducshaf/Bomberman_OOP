package main.entities.statusEffect;

import javafx.scene.image.Image;
public class IncrementBombLevel extends StatusEffect {
    public IncrementBombLevel(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        //increase bomb level (bomb radius)
    }

    @Override
    public void update() {
    }
}
