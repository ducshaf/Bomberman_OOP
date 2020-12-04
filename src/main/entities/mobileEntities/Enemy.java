package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.AnimatedEntity;
import main.utils.Utils;

public abstract class Enemy extends AnimatedEntity {
    protected boolean alive = true;
    protected int direction;
    protected double speed;
    protected boolean changeDirection = false;

    public Enemy(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img);
        this.speed = speed;
    }

    public void update() {
        animate();
        img = chooseImage(direction);
        if (!alive) {
            chooseImage(-1);
        } else {
            chooseImage(direction);
            move();
        }
    }

    public abstract void setDirection();

    public void move() {
        double mX = 0, mY = 0;

        switch (direction) {
            case 0:
                break;
            case Utils.UP: // Up
                mY = -speed;
                break;
            case Utils.DOWN: // down
                mY = speed;
                break;
            case Utils.LEFT: // left
                mX = -speed;
                break;
            case Utils.RIGHT: // right
                mX = speed;
                break;
        }

        double tileOffsetX = Utils.getPreciseDouble(x / 1.6) % 32;
        double tileOffsetY = Utils.getPreciseDouble(y / 1.6) % 32;
        if (tileOffsetX == 0 && tileOffsetY == 0.0 && !changeDirection) {
            setDirection();
            changeDirection = true;
            return;
        }

        if (!canMove(x+mX, y+mY)) {
            setDirection();
            return;
        }
        changeDirection = false;
        x += mX;
        y += mY;
    }

    public boolean canMove(double xx, double yy) {
        double xxa = xx, yya = yy;
        if (direction == 2) {
            xxa += 32*1.6 - 0.1;
        }
        if (direction == 4) {
            yya += 32*1.6 - 0.1;
        }
        if (xxa < 32*2*1.6 - 0.1 || xxa > 32*17*1.6 + 0.1 || yya < 32*3*1.6 - 0.1 || yya > 32*14*1.6 + 0.1) {
            return false;
        }
        else if (collide(GameManagement.getStaticEntityAt(xxa, yya))) {
            return false;
        }
        return true;
    }

    public boolean is_alive() {
        return alive;
    }

    public abstract Image chooseImage(int id);
}
