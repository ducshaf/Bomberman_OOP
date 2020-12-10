package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.mobileEntities.Bomber;
import main.graphics.Layer;
import main.graphics.Sprite;

public class FreezeTime extends StatusEffect {
    public FreezeTime(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.freeze);
    }

    public FreezeTime(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        GameManagement.isFreeze = true;
        System.out.println("DA WARUDO");
    }

    @Override
    public void update() {  // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            GameManagement.isFreeze = false;
        }
    }
}
