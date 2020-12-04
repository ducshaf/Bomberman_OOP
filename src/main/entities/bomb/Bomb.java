package main.entities.bomb;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;
import main.entities.staticEntities.Wall;

import java.util.ArrayList;

public class Bomb extends Entity {
    int timer = 150; //150 frame = 2.5s
    ArrayList<Explosion> explosions = new ArrayList<>();
    int bombLevel;
    boolean exploded = false;
    public Bomb(int xUnit, int yUnit, Image img, int bombLevel) {
        /*   TODO Nhớ sửa trong code khi đặt bom.
        int x = super.getTileX();
        int y = super.getTileY();
        */
        super(xUnit, yUnit, img);
        this.bombLevel = bombLevel;
    }

    public int getBombLevel() {
        return bombLevel;
    }

    @Override
    public void update() {
        if (timer > 0) { // >0.5s
            if (timer < 30) {
                if (!exploded) explode();
                else updateExplosions();
            }
            timer--;
        }
    }

    private void updateExplosions() {
        for (Explosion explosion : explosions) {
            explosion.update();
        }
    }

    // TODO: render explosions

    public void explode() {
        timer = 30;
        exploded = true;
        int xUnit = getTileX();
        int yUnit = getTileY();
        explosions.add(new Explosion(xUnit, yUnit, null /*Image of explosion center*/));
        //North
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = yUnit - i;
            if (newUnit == -1) {
                break;
            } else if (GameManagement.entities.get((newUnit - 1) * 15 + xUnit) instanceof Wall
                    /*|| GameManagement.entities.get(newUnit * 15 + xUnit) instanceof Box*/ || newUnit - 1 == -1) {
                explosions.add(new Explosion(xUnit, newUnit, null)); // end of explosion
                break;
            } else {
                explosions.add(new Explosion(xUnit, newUnit, null));
            }
        }
        //East
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = xUnit + i;
            if (newUnit == 15) {
                break;
            } else if (GameManagement.entities.get(yUnit * 15 + newUnit + 1) instanceof Wall
                  /*|| GameManagement.entities.get(yUnit * 15 + newUnit) instanceof Box*/ || newUnit + 1 == 15) {
                explosions.add(new Explosion(newUnit, yUnit, null)); // end of explosion
                break;
            } else {
                explosions.add(new Explosion(newUnit, yUnit, null));
            }
        }
        //South
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = yUnit + i;
            if (newUnit == 11) {
                break;
            } else if (GameManagement.entities.get((newUnit + 1) * 15 + xUnit) instanceof Wall
                  /*|| GameManagement.entities.get(newUnit * 15 + xUnit) instanceof Box*/ || newUnit + 1 == 11) {
                explosions.add(new Explosion(xUnit, newUnit, null)); // end of explosion
                break;
            } else {
                explosions.add(new Explosion(xUnit, newUnit, null));
            }
        }
        //West
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = xUnit - i;
            if (newUnit == -1) {
                break;
            } else if (GameManagement.entities.get(yUnit * 15 + newUnit - 1) instanceof Wall
                  /*|| GameManagement.entities.get(yUnit * 15 + newUnit) instanceof Box*/ || newUnit - 1 == -1) {
                explosions.add(new Explosion(newUnit, yUnit, null)); // end of explosion
                break;
            } else {
                explosions.add(new Explosion(newUnit, yUnit, null));
            }
        }
    }
}
