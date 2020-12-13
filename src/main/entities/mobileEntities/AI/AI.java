package main.entities.mobileEntities.AI;

import java.util.Random;

public abstract class AI {
    protected static Random random;

    public AI() {
        random = new Random();
    }

    public int randomDirection() {
        return random.nextInt(5);
    }

    public abstract int calculateDirection();
}

