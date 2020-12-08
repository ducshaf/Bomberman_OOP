package main.entities.statusEffect;

import javafx.scene.image.Image;

public class TimeBomb extends StatusEffect{
    // Sửa phần bomb
    public TimeBomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public TimeBomb() {

    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
    }

    @Override
    public void update() {
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
        }
    }
}
