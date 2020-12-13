package main.entities.mobileEntities.AI;

public class SimpleAI extends AI {
    public SimpleAI() {
        super();
    }

    @Override
    public int calculateDirection() {
        return randomDirection();
    }
}
