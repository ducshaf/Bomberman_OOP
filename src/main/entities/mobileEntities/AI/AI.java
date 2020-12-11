package main.entities.mobileEntities.AI;

import java.util.Random;

public class AI {
    protected Random random;

    public AI() {
        random = new Random();
    }

    public int randomDirection() {
        return random.nextInt(4) + 1;
    }
}

