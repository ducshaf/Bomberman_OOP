package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.bomb.Bomb;
import main.entities.statusEffect.*;
import main.gameplay.inputHandler.InputManager;
import main.entities.AnimatedEntity;
import main.graphics.Layer;
import main.graphics.Sprite;

import java.util.HashMap;
import java.util.Map;

public class Bomber extends AnimatedEntity {
    protected boolean alive = true;
    protected int direction;
    protected double speed;
    protected int bombQuality;
    protected int bombQuantity;
    protected int lives = 3;
    protected Map<String, StatusEffect> status = new HashMap<>();

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        speed = 1;
        bombQuality = 1;
        bombQuantity = 4;
        status.put("blind", new Blind());
        status.put("fierce", new FierceBomb());
        status.put("freeze", new FreezeTime());
        status.put("invert", new Inversion());
        status.put("invincible", new Invincibility());
        status.put("percolate", new Percolate());
        status.put("slow", new Slow());
        status.put("force", new TheForce());
        status.put("time", new TimeBomb());
    }

    /***********************************************************************************
     * Bomber Update
     ***********************************************************************************/
    @Override
    public void update() {
        if (killed) {
            if (invulTime == 120) {
                System.out.println("lives--");
                lives--;
            }
            invulTimer();
        }
        if (lives == 0) {
            System.out.println("died");
            isAlive = false;
        }
        if (isAlive) {
            calculateMove();
            img = chooseImage(direction);
            if (InputManager.isSpace()) {
                setBomb();
                InputManager.setSpace();
            }

            updateStatus();
        }
    }

    /***********************************************************************************
     * Bomber ability
     ***********************************************************************************/
    public void setBomb() {
        if (GameManagement.bombs.size() < bombQuantity) {
            GameManagement.bombs.add(new Bomb(setBombTileX(), setBombTileY(),
                    new Image("./sprites/power-ups/fierce_bomb.png"), bombQuality));
        }
    }

    public void updateStatus() {
        StatusEffect blind = status.get("blind");
        if (InputManager.isF1()) {
            blind.init();
            GameManagement.isBlind = true;
        }
        if (blind.isActive()) {
            blind.update();
        } else GameManagement.isBlind = false;

        StatusEffect fierce = status.get("fierce");
        if (InputManager.isF2()) {
            fierce.init();
        }
        if (fierce.isActive()) {
            fierce.update();
        }
    }

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
            mX = - 1.6 * speed;
            animate();
        }
        if (InputManager.isRight()) {
            mX =  1.6 * speed;
            animate();
        }
        if (InputManager.isUp()) {
            mY = -1.6 *speed;
            animate();
        }
        if (InputManager.isDown()) {
            mY = 1.6 * speed;
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


    /***********************************************************************************
     * Utilities
     ***********************************************************************************/
    public int getBombQuality() {
        return bombQuality;
    }

    public void setBombQuality(int bombQuality) {
        this.bombQuality = bombQuality;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getBombQuantity() {
        return bombQuantity;
    }

    public void setBombQuantity(int bombQuantity) {
        this.bombQuantity = bombQuantity;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
