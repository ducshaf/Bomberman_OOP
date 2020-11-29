package main.entities.statusEffect;

import javafx.scene.image.Image;

public class Heal extends StatusEffect {
    public Heal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        //heal player
    }

    @Override
    public void update() {
    }
}
