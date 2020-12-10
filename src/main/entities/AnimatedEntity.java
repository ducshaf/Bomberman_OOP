package main.entities;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.mobileEntities.Bomber;
import main.entities.mobileEntities.Enemy;
import main.entities.staticEntities.Grass;
import main.entities.statusEffect.StatusEffect;

public abstract class AnimatedEntity extends Entity {
    int _animate = 0;
    final int MAX_ANIMATE = 6000;
    protected boolean killed = false;
    protected boolean isInvul = false;
    protected int invulTime = 120;


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

    public void invulTimer() {
        if (invulTime > 0) {
            invulTime--;
            System.out.println(invulTime);
        } else {
            invulTime = 120;
            isInvul = false;
            killed = false;
        }
    }

    public abstract void update();

    public void killed() {
        if (!isInvul) {
            killed = true;
            isInvul = true;
        }
    }

    public boolean isKilled() {
        return killed;
    }

    public boolean collide(Entity e) {
        return !(e instanceof Grass || e instanceof StatusEffect);
    }
}
