package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.statusEffect.*;
import main.gameplay.inputHandler.InputManager;
import main.entities.AnimatedEntity;
import main.graphics.Sprite;
import main.utils.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Bomber extends AnimatedEntity {
    public int dead_animation = 70;
    public int direction;
    public static double speed;
    public static int bombQuality;
    public static int bombQuantity;
    public static int lives = 3;
    public static Map<String, StatusEffect> status = new HashMap<>();
    public static Vector<Bomb> bombs = new Vector<>();

    public boolean isinvul = false;
    public boolean ispercolate = false;
    public boolean isforceuser = false;
    public boolean isfierce = false;
    public boolean isRemote = false;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        speed = 0.8;
        bombQuality = 1;
        bombQuantity = 1;
        lives = 3;
        status.clear();
        status.put("blind", new Blind(this));
        status.put("fierce", new FierceBomb(this));
        status.put("freeze", new FreezeTime(this));
        status.put("invert", new Inversion(this));
        status.put("invincible", new Invincibility(this));
        status.put("percolate", new Percolate(this));
        status.put("slow", new Slow(this));
        status.put("force", new TheForce(this));
        status.put("time", new TimeBomb(this));
        status.put("heal", new Heal(this));
        status.put("agile", new Agile(this));
        status.put("increaseBLvl", new IncrementBombLevel(this));
        status.put("increaseBNum", new IncrementBombNumber(this));
        status.put("random", new Random(this));
    }

    /***********************************************************************************
     * Bomber Update
     ***********************************************************************************/
    @Override
    public void update() {
        if (killed) {
            if (lives == 0) {
                if (dead_animation > 0) {
                    animate();
                    img = chooseImage(-1);
                    dead_animation--;
                } else isAlive = false;
            } else {
                if (invulTime == 120) {
                    if (!isinvul) {
                        lives--;
                    }
                }
                invulTimer();
            }
        }
        if (lives != 0) {
            calculateMove();
            collideEnemy();
            pickUpPowerup();
            img = chooseImage(direction);
            if (InputManager.isSetBomb()) {
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
        if (bombs.size() < bombQuantity) {
            Bomb bomb = new Bomb(setBombTileX(), setBombTileY(), new Image("./sprites/bomb/bomb1.png"), bombQuality);
            bombs.add(bomb);
            GameManagement.bombs.add(bomb);
        }
    }

    public void updateStatus() {
        StatusEffect blind = status.get("blind");
        if (InputManager.isF1()) {
            if (!blind.isActive()){
                blind.init();
            }
        }
        if (blind.isActive()) {
            blind.update();
        }

        StatusEffect fierce =  status.get("fierce");
        if (InputManager.isF2()) {
            if (!fierce.isActive()){
                fierce.init();
            }
        }
        if (fierce.isActive()) {
            fierce.update();
        }

        StatusEffect freeze = status.get("freeze");
        if (InputManager.isF3()) {
            if (!freeze.isActive()) {
                freeze.init();
            }
        }
        if (freeze.isActive()) {
            freeze.update();
        }

        StatusEffect invert = status.get("invert");
        if (InputManager.isF4()) {
            if (!invert.isActive()) {
                invert.init();
            }
        }
        if (invert.isActive()) {
            invert.update();
        }

        StatusEffect invincibility = status.get("invincible");
        if (InputManager.isF5()) {
            if (!invincibility.isActive()) {
                invincibility.init();
            }
        }
        if (invincibility.isActive()) {
            invincibility.update();
        }

        StatusEffect percolate = status.get("percolate");
        if (InputManager.isF6()) {
            if (!percolate.isActive()) {
                percolate.init();
            }
        }
        if (percolate.isActive()) {
            percolate.update();
        }

        StatusEffect slow = status.get("slow");
        if (InputManager.isF7()) {
            if (!slow.isActive()) {
                slow.init();
            }
        }
        if (slow.isActive()) {
            slow.update();
        }

        StatusEffect timeBomb = status.get("time");
        if (InputManager.isF8()) {
            if (!timeBomb.isActive()) {
                timeBomb.init();
            }
        }
        if (timeBomb.isActive()) {
            timeBomb.update();
        }

        StatusEffect force = status.get("force");
        if (InputManager.isF9()) {
            if (!force.isActive()) {
                force.init();
            }
        }
        if (force.isActive()) {
            force.update();
        }

        StatusEffect heal = status.get("heal");
        if (heal.isActive()) {
            heal.update();
        }

        StatusEffect bombLvl = status.get("increaseBLvl");
        if (bombLvl.isActive()) {
            bombLvl.update();
        }

        StatusEffect bombNum = status.get("increaseBNum");
        if (bombNum.isActive()) {
            bombNum.update();
        }

        StatusEffect agile = status.get("agile");
        if (agile.isActive()) {
            agile.update();
        }

        StatusEffect random = status.get("random");
        if (random.isActive()) {
            random.update();
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
        if (!ispercolate) {
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

            for (Entity bomb : GameManagement.bombs) {
                Bomb b = (Bomb) bomb;
                if (b.collide(xx, yy)) {
                    if (isforceuser) {
                        b.setDirection(direction);
                    }
                    return false;
                }
            }
        }

        return true;
    }

    /***********************************************************************************
     * Bomber collide and die
     ***********************************************************************************/
    public void getStatusEffect(StatusEffect statusEffect) {
        if (statusEffect.isAlive()) {
            if (statusEffect instanceof Agile) status.get("agile").init();
            if (statusEffect instanceof IncrementBombLevel) status.get("increaseBLvl").init();
            if (statusEffect instanceof IncrementBombNumber) status.get("increaseBNum").init();
            if (statusEffect instanceof Heal) status.get("heal").init();
            if (statusEffect instanceof FierceBomb) status.get("fierce").init();
            if (statusEffect instanceof FreezeTime) status.get("freeze").init();
            if (statusEffect instanceof Invincibility) status.get("invincible").init();
            if (statusEffect instanceof Percolate) status.get("percolate").init();
            if (statusEffect instanceof TheForce) status.get("force").init();
            if (statusEffect instanceof TimeBomb) status.get("time").init();
            if (statusEffect instanceof Random) status.get("random").init();
        }
    }

    public void collideEnemy() {
        for (Entity e : GameManagement.mobileEntities) {
            if (! (e instanceof Bomber)) {
                if ((Utils.getTileX(x - 32) == e.getTileX() && getTileY() == e.getTileY())
                        || Utils.getTileX(x + 32) == e.getTileX() && getTileY() == e.getTileY()
                        || getTileX() == e.getTileX() && Utils.getTileY(y - 25) == e.getTileY()
                        || getTileX() == e.getTileX() && Utils.getTileY(y + 32) == e.getTileY())
                {
                    if (e instanceof Ghost && e.isAlive()) {
                        e.kill();
                        java.util.Random random = new java.util.Random();
                        switch (random.nextInt(3)) {
                            case 0:
                                status.get("blind").init();
                                break;
                            case 1:
                                status.get("invert").init();
                                break;
                            case 2:
                                status.get("slow").init();
                                break;
                        }
                    }
                    else killed();
                }
            }
        }
    }

    public void pickUpPowerup() {
        if (GameManagement.getStaticEntityAt(x+16*1.6, y+16*1.6) instanceof StatusEffect) {
            getStatusEffect((StatusEffect) GameManagement.getStaticEntityAt(x + 16 * 1.6, y + 16 * 1.6));
            GameManagement.getStaticEntityAt(x+1.6*16, y+1.6*16).kill();
        }
    }

    /***********************************************************************************
     * Bomber Sprites
     ***********************************************************************************/

    public Image chooseImage(int dir) {
        switch (dir) {
            case -1:
                return Sprite.getMoveSprite(Sprite.player_die, Sprite.player_die_1,
                        Sprite.player_die_2, Sprite.player_die_2, get_animate(), 70);
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
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void increaseBombQuantity() {
        bombQuantity++;
        System.out.println("increase bomb quantity");
    }

    public void increaseBombQuality() {
        bombQuality++;
        System.out.println("increase bomb quality");
    }

    public void increaseLives() {
        if (lives < 3) {
            lives++;
        }
    }

    public void increaseSpeed() {
        speed += 0.1;
        System.out.println("increase speed to " + speed);
    }

}
