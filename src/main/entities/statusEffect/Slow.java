package main.entities.statusEffect;

import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class Slow extends StatusEffect {
    double playerSpeed;
    public Slow(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.slow);
    }

    public Slow(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        playerSpeed = player.getSpeed();
        player.setSpeed(0.5);
        System.out.println("slow down");
    }

    @Override
    public void update() {  // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            player.setSpeed(playerSpeed);
        }
    }

}
