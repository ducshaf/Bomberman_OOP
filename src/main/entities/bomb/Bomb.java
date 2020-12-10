package main.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;
import main.entities.mobileEntities.Bomber;
import main.entities.staticEntities.DestroyableWall;
import main.entities.staticEntities.Grass;
import main.entities.staticEntities.Wall;
import main.entities.statusEffect.StatusEffect;

import java.util.ArrayList;

public class Bomb extends Entity {
    int timer = 150; //150 frame = 2.5s
    ArrayList<Explosion> explosions = new ArrayList<>();
    Bomber player;
    int bombLevel;
    boolean exploded = false;
    boolean passable = true;
    int direction = 0;


    public Bomb(int xUnit, int yUnit, Image img, int bombLevel) {
        super(xUnit, yUnit, img);
        this.bombLevel = bombLevel;
        player = GameManagement.getPlayer();
    }

    public int getBombLevel() {
        return bombLevel;
    }

    public boolean isExploded() {
        return exploded;
    }

    @Override
    public void update() {
        if (timer > 0) { // >0.5s
            if (timer < 30) {
                if (!exploded) {
                    explode();
                    System.out.println(getX() + "," + getY());
                }
                updateExplosions();
            }
            timer--;
            if ((Math.abs(player.getX() - getX()) > 31*1.6 || Math.abs(player.getY() - getY()) > 31*1.6) && passable) {
                passable = false;
            }
            if (direction != 0) {
                move();
            }
        } else isAlive = false;
    }

    private void updateExplosions() {
        for (Explosion explosion : explosions) {
            explosion.update();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y, 32*1.6, 32*1.6);
        for (Explosion explosion : explosions) {
            explosion.render(gc);
        }
    }

    public void explode() {
        timer = 30;
        exploded = true;
        int xUnit = getTileX();
        int yUnit = getTileY();
        img = null;
        if (player.isfierce) bombLevel = 15;
        explosions.add(new Explosion(xUnit, yUnit, new Image("./sprites/bomb/flame_middle.png")));
        //North
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = yUnit - i;
            if (newUnit == -1 || GameManagement.entities.get(newUnit * 15 + xUnit) instanceof Wall) {
                break;
            } else if (newUnit - 1 == -1 || i == bombLevel) {
                explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_up.png")));
                break;
            } else if (GameManagement.entities.get((newUnit) * 15 + xUnit) instanceof DestroyableWall) {
                if (!player.isfierce) {
                    explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_up.png")));
                    break;
                } else explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_vertical.png")));
            } else {
                explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_vertical.png")));
            }
        }
        //South
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = yUnit + i;
            if (newUnit == 11 || GameManagement.entities.get(newUnit * 15 + xUnit) instanceof Wall) {
                break;
            } else if (newUnit + 1 == 11 || i == bombLevel) {
                explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_down.png")));
                break;
            } else if (GameManagement.entities.get(newUnit * 15 + xUnit) instanceof DestroyableWall) {
                if (!player.isfierce) {
                    explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_down.png")));
                    break;
                } else explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_vertical.png")));
            } else {
                explosions.add(new Explosion(xUnit, newUnit, new Image("./sprites/bomb/flame_vertical.png")));
            }
        }
        //East
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = xUnit + i;
            if (newUnit == 15 || GameManagement.entities.get(yUnit * 15 + newUnit) instanceof Wall) {
                break;
            } else if (newUnit + 1 == 15 || i == bombLevel) {
                explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_right.png")));
                break;
            }
            else if (GameManagement.entities.get(yUnit * 15 + newUnit) instanceof DestroyableWall) {
                if (!player.isfierce) {
                    explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_right.png")));
                    break;
                } else explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_horizontal.png")));
            } else {
                explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_horizontal.png")));
            }
        }

        //West
        for (int i = 1; i <= bombLevel; i++) {
            int newUnit = xUnit - i;
            if (newUnit == -1 || GameManagement.entities.get(yUnit * 15 + newUnit) instanceof Wall) {
                break;
            } else if (newUnit - 1 == -1 || i == bombLevel) {
                explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_left.png")));
                break;
            }
            else if (GameManagement.entities.get(yUnit * 15 + newUnit) instanceof DestroyableWall) {
                if (!player.isfierce) {
                    explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_left.png")));
                    break;
                } else explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_horizontal.png")));
            } else {
                explosions.add(new Explosion(newUnit, yUnit, new Image("./sprites/bomb/flame_horizontal.png")));
            }
        }
    }

    public boolean collide(double x, double y) {
        if ((Math.abs(x-getX()) < (30*1.6) && (Math.abs(y-getY()) < 1)) ||
                (Math.abs(y-getY()) < (30*1.6) && (Math.abs(x-getX()) < 1))) {
            return !passable;
        }
        return false;
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

    private boolean collide(Entity staticEntity) {
        return !(staticEntity instanceof Grass || staticEntity instanceof StatusEffect);
    }

    public void move() {
        switch (direction) {
            case 1:
                if (canMove(x-3.2*1.6, y)) {
                    x -= player.getSpeed()*7*1.6;
                } else direction = 0;
                break;
            case 2:
                if (canMove(x+3.2*1.6, y)) {
                    x += player.getSpeed()*7*1.6;
                } else direction = 0;
                break;
            case 3:
                if (canMove(x, y-3.2*1.6)) {
                    y -= player.getSpeed()*7*1.6;
                } else direction = 0;
            case 4:
                if (canMove(x, y+3.2*1.6)) {
                    y += player.getSpeed()*7*1.6;
                } else direction = 0;
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
