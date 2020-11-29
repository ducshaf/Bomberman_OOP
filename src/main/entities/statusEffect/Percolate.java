package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.graphics.Layer;

public class Percolate extends StatusEffect {
    // pass through block.
    // Sửa code chỗ grid của player, nếu cái này !isActive thì mới đi qua đc
    public Percolate(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
    }

    @Override
    public void update() { // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
        }
    }
}
