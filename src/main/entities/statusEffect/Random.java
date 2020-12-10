package main.entities.statusEffect;

import main.entities.mobileEntities.Bomber;
import main.graphics.Sprite;

public class Random extends StatusEffect{
    public Random(int x, int y) {
        super(x, y, Sprite.random);
    }

    public Random(Bomber bomber) {
        super(bomber);
    }

    @Override
    public void update() {
        isActive = false;
    }

    @Override
    public void init() {
        isActive = true;
        java.util.Random randomChance = new java.util.Random();
        switch (randomChance.nextInt(14)) {
            case 1:
                Bomber.status.get("agile").init();
                break;
            case 2:
                Bomber.status.get("blind").init();
                break;
            case 3:
                Bomber.status.get("fierce").init();
                break;
            case 4:
                Bomber.status.get("freeze").init();
                break;
            case 5:
                Bomber.status.get("heal").init();
                break;
            case 6:
                Bomber.status.get("increaseBLvl").init();
                break;
            case 7:
                Bomber.status.get("increaseBNum").init();
                break;
            case 8:
                Bomber.status.get("invert").init();
                break;
            case 9:
                Bomber.status.get("invincible").init();
                break;
            case 10:
                Bomber.status.get("percolate").init();
                break;
            case 11:
                Bomber.status.get("slow").init();
                break;
            case 12:
                Bomber.status.get("force").init();
                break;
            case 13:
                Bomber.status.get("time").init();
                break;
        }
    }
}
