package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.GameManagement;
import main.graphics.Layer;

import java.awt.*;

public class Blind extends StatusEffect {
    public Blind(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        duration += 300;
        isActive = true;
    }

    @Override
    public void update() {         // 5s
        if (duration > 0) {
            --duration;
            Layer.blind();
        } else if (isActive) {
            isActive = false;
        }
    }


}
