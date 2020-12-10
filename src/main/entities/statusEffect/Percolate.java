package main.entities.statusEffect;

import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class Percolate extends StatusEffect {
    double x = 0;
    double y = 0;
    public Percolate(int xUnit, int yUnit) {
        super(xUnit, yUnit, Sprite.percolate);
    }

    public Percolate(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void init() {
        duration += 600;
        if (!isActive) {
            x = player.getX();
            y = player.getY();
        }
        player.ispercolate = true;
        isActive = true;
        System.out.println("percolation start..." + player.getX() +  player.getY());
    }

    @Override
    public void update() { // 5s
        if (duration > 0) {
            --duration;
        } else if (isActive) {
            isActive = false;
            player.ispercolate = false;
            if (!player.canMove(player.getX(), player.getY())) {
                player.setX(x);
                player.setY(y);
            }
        }
    }
}
