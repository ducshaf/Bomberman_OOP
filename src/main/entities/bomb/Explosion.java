package main.entities.bomb;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;
import main.entities.Grass;

public class Explosion extends Entity {
    public Explosion(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        for (Entity e : GameManagement.mobileEntities) {
            //if (this.collide(e)) kill();
        }
        //if (GameManagement.entities[getTileY() * 15 + getTileX()] instanceof Box) {
        //    GameManagement.entities.set(getTileY() * 15 + getTileX(), new Grass(x, y, null));
        //}
        //for (Bomb bomb : GameManagement.bombs) {
         //   if (this.collide(bomb)) {
         //       bomb.explode();
           // }
       // }
    }
}
