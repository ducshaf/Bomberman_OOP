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
        duration += 1200;
        isActive = true;
        player.isRemote = true;
    }

    @Override
    public void update() {
        if (duration > 0) {
            --duration;
            if (GameManagement.bombs.isEmpty()) player.isRemote = true;
            if (InputManager.isControlBomb()) {
                player.isRemote = false;
                for (Entity bomb : GameManagement.bombs) {
                    Bomb b = (Bomb) bomb;
                    b.explode();
                }
            }
        } else if (isActive) {
            isActive = false;
            player.isRemote = false;
        }
    }
}
