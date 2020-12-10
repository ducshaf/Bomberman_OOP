package main.entities.statusEffect;

import main.GameManagement;
import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.mobileEntities.Bomber;
import main.gameplay.inputHandler.InputManager;
import main.graphics.Sprite;

public class TimeBomb extends StatusEffect{
    // Sửa phần bomb
    public TimeBomb(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.time);
    }

    public TimeBomb(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        isActive = true;
        GameManagement.isRemoteControlled = true;
    }

    @Override
    public void update() {
        if (duration > 0) {
            --duration;
            if (InputManager.isControlBomb()) {
                GameManagement.isRemoteControlled = false;
                for (Entity bomb : GameManagement.bombs) {
                    Bomb b = (Bomb) bomb;
                    b.explode();
                }
                duration = 0;
            }
        } else if (isActive) {
            isActive = false;
            GameManagement.isRemoteControlled = false;
        }
    }
}
