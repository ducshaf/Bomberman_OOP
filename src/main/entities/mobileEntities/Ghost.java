package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.entities.mobileEntities.AI.AI;
import main.graphics.Sprite;

public class Ghost extends Enemy {
    private AI ghost = new AI();
    public Ghost(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0.4);
    }

    @Override
    public boolean canMove(double xx, double yy) {
        double xxa = Math.round(xx*100)/100.0, yya = Math.round(yy*100)/100.0;
        if (direction == 1) {
        }
        if (direction == 2) {
            xxa += 32*1.6 - 0.1;
        }
        if (direction == 3) {
        }
        if (direction == 4) {
            yya += 32*1.6 - 0.1;
        }
        if (xxa < 32*2*1.6 - 0.1 || xxa > 32*17*1.6 + 0.1 || yya < 32*3*1.6 - 0.1 || yya > 32*14*1.6 + 0.1) {
            return false;
        }
        return true;
    }

    @Override
    public void setDirection() {
        direction = ghost.randomDirection();
    }

    @Override
    public Image chooseImage(int id) {
        switch (direction) {
            case 1:     //left
                return Sprite.getMoveSprite(Sprite.ghost_left_0, Sprite.ghost_left_1,
                        Sprite.ghost_left_2, get_animate(), 30);
            case 2:     //right
                return Sprite.getMoveSprite(Sprite.ghost_right_0, Sprite.ghost_right_1,
                        Sprite.ghost_right_2, get_animate(), 30);
            case 3:     //up
                return Sprite.getMoveSprite(Sprite.ghost_up_0, Sprite.ghost_up_1,
                        Sprite.ghost_up_2, get_animate(), 30);
            default:    //down
                return Sprite.getMoveSprite(Sprite.ghost_down_0, Sprite.ghost_down_1,
                        Sprite.ghost_down_2, get_animate(), 30);
        }
    }
}
