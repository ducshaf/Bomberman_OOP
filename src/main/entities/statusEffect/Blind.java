package main.entities.statusEffect;

import main.GameManagement;
import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

import java.awt.*;

public class Blind extends StatusEffect {
    public Blind(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.blind);
    }

    public Blind(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        GameManagement.isBlind = true;
        System.out.println("is blinded");
    }

    @Override
    public void update() {         // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            GameManagement.isBlind = false;
        }
    }


}
