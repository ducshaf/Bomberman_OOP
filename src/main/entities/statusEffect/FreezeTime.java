package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.graphics.Layer;

public class FreezeTime extends StatusEffect {
    public FreezeTime(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, new Image("./sprites/power-ups/blind_debuff.png"));
    }

    public FreezeTime() {

    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
    }

    @Override
    public void update() {  // 5s
        if (duration > 0) {
            --duration;
            Layer.freezeTime();
        } else if (isActive) {
            isActive = false;
        }
    }
}
