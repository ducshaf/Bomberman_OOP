package main.entities.bomb;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.AnimatedEntity;
import main.entities.Entity;
import main.entities.staticEntities.DestroyableWall;

public class Explosion extends Entity {
    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        for (Entity e : GameManagement.mobileEntities) {
            AnimatedEntity a = (AnimatedEntity) e;
           if (a.setBombTileX() == getTileX() && a.setBombTileY() == getTileY()) {
               a.killed();
           }
        }
        if (GameManagement.entities.get(getTileY() * 15 + getTileX()) instanceof DestroyableWall) {
            GameManagement.entities.get(getTileY() * 15 + getTileX()).kill();
        }
        for (Entity bomb : GameManagement.bombs) {
            Bomb b = (Bomb) bomb;
            if (bomb.getTileX() == getTileX() && bomb.getTileY() == getTileY()) {
                if (!b.isExploded()) b.explode();
            }
        }
    }

    public boolean collide(Entity e) {
        return false;
    }
}
