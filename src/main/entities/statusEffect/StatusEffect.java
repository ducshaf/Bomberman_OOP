package main.entities.statusEffect;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;
import main.entities.mobileEntities.Bomber;

import java.util.Random;

public abstract class StatusEffect extends Entity {
    static Bomber player;
    int duration = 0;
    boolean isActive = false;

    public StatusEffect(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public StatusEffect(Bomber player) {
        super();
        this.player = player;
    }

    public abstract void init();

    public int getDuration() {
        return duration;
    }

    public boolean isActive() {
        return isActive;
    }

    public void disappear() {
        img = null;
    }

    public static StatusEffect initStatusEffect(int x, int y) {
        Random random = new Random();
        switch (random.nextInt(11)) {
            case 0:
                return new Agile(x, y);
            case 1:
                return new FierceBomb(x, y);
            case 2:
                return new FreezeTime(x, y);
            case 3:
                return new Heal(x, y);
            case 4:
                return new IncrementBombLevel(x, y);
            case 5:
                return new IncrementBombNumber(x, y);
            case 6:
                return new Invincibility(x, y);
            case 7:
                return new Percolate(x, y);
            case 8:
                return new TheForce(x, y);
            case 9:
                return new TimeBomb(x, y);
            default:

                return new main.entities.statusEffect.Random(x, y);
        }
    }
}
