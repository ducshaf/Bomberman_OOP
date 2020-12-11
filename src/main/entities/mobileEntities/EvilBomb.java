package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.bomb.Bomb;
import main.entities.mobileEntities.AI.BomberAI;
import main.graphics.Sprite;

public class EvilBomb extends Enemy{
    private BomberAI bomberAI = new BomberAI(this);

    public EvilBomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0.8);
    }

    @Override
    public boolean canMove(double xx, double yy) {
        double xxa = xx, yya = yy;
        if (direction == 2) {
            xxa += 32*1.6 - 0.1;
        }
        if (direction == 4) {
            yya += 32*1.6 - 0.1;
        }
        if (xxa < 32*2*1.6 - 0.1 || xxa > 32*17*1.6 + 0.1 || yya < 32*3*1.6 - 0.1 || yya > 32*14*1.6 + 0.1) {
            return false;
        }
        if (collide(GameManagement.getStaticEntityAt(xxa, yya))) {
            return false;
        }
        return true;
    }

    @Override
    public void setDirection() {
        direction = bomberAI.calculateDirection();
        if (getTileX() == GameManagement.getPlayer().getTileX() || getTileY() == GameManagement.getPlayer().getTileY()) {
            killed();
        }
    }

    @Override
    public void die() {
        if (death_animate > 0) {
            animate();
            img = chooseImage(-1);
            death_animate--;
        } else {
            System.out.println(direction);
            isAlive = false;
            Bomb a = new Bomb(getTileX(), getTileY(), null, 16);
            a.setFierce(true);
            a.explode();
            GameManagement.bombs.add(a);
        }
    }

    @Override
    public Image chooseImage(int id) {
        switch (direction) {
            case 1: //left
                return Sprite.getMoveSprite(Sprite.bomber_left_0, Sprite.bomber_left_1, Sprite.bomber_left_2,
                        Sprite.bomber_left_3, Sprite.bomber_left_4, get_animate(), 24);
            case 2: //right
                return Sprite.getMoveSprite(Sprite.bomber_right_0, Sprite.bomber_right_1, Sprite.bomber_right_2,
                        Sprite.bomber_right_3, Sprite.bomber_right_4, get_animate(), 24);
            case 3: //up
                return Sprite.getMoveSprite(Sprite.bomber_up_0, Sprite.bomber_up_1, Sprite.bomber_up_2,
                        Sprite.bomber_up_3, Sprite.bomber_up_4, get_animate(), 24);
            case 4: //down
                return Sprite.getMoveSprite(Sprite.bomber_down_0, Sprite.bomber_down_1, Sprite.bomber_down_2,
                        Sprite.bomber_down_3, Sprite.bomber_down_4, get_animate(), 24);
            default:
                return img;
        }
    }
}
