package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class TheForce extends StatusEffect {
    // Sửa code phần grid của player
    public TheForce(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.force);
    }

    public TheForce(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        System.out.println("the force awaken...");
        duration += 3000;
        isActive = true;
        player.isforceuser = true;
    }

    @Override
    public void update() {
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            player.isforceuser = false;
        }
    }

}
