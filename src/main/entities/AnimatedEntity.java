package main.entities;

import javafx.scene.image.Image;
import main.entities.staticEntities.Grass;

public abstract class AnimatedEntity extends Entity {
    int _animate = 0;
    final int MAX_ANIMATE = 6000;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int get_animate() {
        return _animate;
    }

    public void animate() {
        if (_animate < MAX_ANIMATE) {
            _animate++;
        } else {
            _animate = 0;
        }
    }

    public abstract void update();

    public boolean collide(Entity e) {
        return !(e instanceof Grass);
    }
}
