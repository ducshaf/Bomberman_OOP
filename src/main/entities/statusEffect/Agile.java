package main.entities.statusEffect;

import javafx.scene.image.Image;

public class Agile extends StatusEffect {
    public Agile(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        // increase player movement speed
    }

    @Override
    public void update() {
    }
}
