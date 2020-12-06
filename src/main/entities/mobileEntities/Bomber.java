package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.GameManagement;
import main.Input.InputManager;
import main.entities.AnimatedEntity;
import main.graphics.Sprite;

public class Bomber extends AnimatedEntity {
    protected boolean alive = true;
    protected int direction;
    protected double speed;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        speed = 1;
    }

    /***********************************************************************************
     * Bomber Update
     ***********************************************************************************/
    @Override
    public void update() {
        img = chooseImage(direction);
        if (!alive) {
            chooseImage(-1);
        }
        else {
            calculateMove();
            chooseImage(direction);
        }
    }

    /***********************************************************************************
     * Bomber ability
     ***********************************************************************************/


    /***********************************************************************************
     * Bomber movement
     ***********************************************************************************/
    public void move(double p, double q) {

        if (p < 0) {
            direction = 1;
        }
        if (p > 0) {
            direction = 2;
        }
        if (q < 0) {
            direction = 3;
        }
        if (q > 0) {
            direction = 4;
        }

        if (canMove(x,y + q)) {
            y += q;
        }

        if (canMove(x + p,y)) {
            x += p;
        }

    }

    public void calculateMove() {
        double mX = 0, mY = 0;
        if (InputManager.isLeft()) {
            mX = - 2* speed;
            animate();
        }
        if (InputManager.isRight()) {
            mX =  2* speed;
            animate();
        }
        if (InputManager.isUp()) {
            mY = -2 *speed;
            animate();
        }
        if (InputManager.isDown()) {
            mY = 2 * speed;
            animate();
        }

        if (mX != 0 || mY != 0) {
            move(mX, mY);
        }

    }

    public boolean canMove(double xx, double yy) {
        double tl_x = xx +1, tl_y = yy + 1,
                tr_x = xx + 32*1.6 - 1, tr_y = yy +1,
                bl_x = xx + 1, bl_y = yy + 32*1.6 - 1,
                br_x = xx + 32*1.6 -1, br_y = yy + 32*1.6-1;

        if (tl_x < 32*2*1.6 || bl_x < 32*2*1.6 ||
                tr_x > 32*17*1.6 || br_x > 32*17*1.6 ||
                tl_y < 32*3*1.6 || tr_y < 32*3*1.6 ||
                bl_y > 32*14*1.6 || br_y > 32*14*1.6
        ) {
            return false;
        }
        if (direction == 1 && collide(GameManagement.getStaticEntityAt(tl_x, tl_y)) ||
        collide(GameManagement.getStaticEntityAt(bl_x, bl_y))
        ) {
            return false;
        }

        if (direction == 2 && collide(GameManagement.getStaticEntityAt(tr_x, tr_y)) ||
        collide(GameManagement.getStaticEntityAt(br_x, br_y))
        ) {
            return false;
        }

        if (direction == 3 && collide(GameManagement.getStaticEntityAt(tl_x, tl_y)) ||
        collide(GameManagement.getStaticEntityAt(tr_x, tr_y))
        ) {
            return false;
        }

        if (direction == 4 && collide(GameManagement.getStaticEntityAt(bl_x, bl_y)) ||
        collide(GameManagement.getStaticEntityAt(br_x, br_y))
        ) {
            return false;
        }

        return true;
    }

    /***********************************************************************************
     * Bomber collide and die
     ***********************************************************************************/



    /***********************************************************************************
     * Bomber Sprites
     ***********************************************************************************/

    public Image chooseImage(int dir) {
        switch (direction) {
            case 1: //left
                return Sprite.getMoveSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, get_animate(), 24);
            case 2: //right
                return Sprite.getMoveSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, get_animate(), 24);
            case 3: //up
                return Sprite.getMoveSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, get_animate(), 24);
            default: //down
                return Sprite.getMoveSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, get_animate(), 24);
        }
    }
}
