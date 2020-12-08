package main.entities.mobileEntities.AI;

import main.GameManagement;
import main.entities.Entity;
import main.entities.staticEntities.Grass;
import java.util.Vector;

public class BomberAI extends AdvancedAI {
    private int waitTime;

    public BomberAI(Entity e) {
        super(e);
    }

    public int getWaitTime() {
        return waitTime;
    }

    public Pair findSafePlace() {
        int tileX = enemy.getTileX();
        int tileY = enemy.getTileY();
        boolean[][] safePlaces = new boolean[ROW][COL];
        Vector<Entity> entities = GameManagement.entities;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (entities.get(i * 15 + j) instanceof Grass) {
                    safePlaces[i][j] = true;
                }
            }
        }

        //North
        for (int i = 1; i <= 2; i++) {
            if (isValid(tileX, tileY-i)) {
                if (!safePlaces[tileY - i][tileX]) {
                    break;
                } else {
                    if (i == 2) return new Pair(tileX, tileY - i);
                    if (isValid(tileX+1, tileY-i)) {
                        if (safePlaces[tileY - i][tileX + 1]) {
                            return new Pair(tileX + 1, tileY - i);
                        }
                    }
                    if (isValid(tileX-1, tileY-i)) {
                        if (safePlaces[tileY - i][tileX - 1]) {
                            return new Pair(tileX - 1, tileY - i);
                        }
                    }
                }
            }
        }

        //South
        for (int i = 1; i <= 2; i++) {
            if (isValid(tileX, tileY+i)) {
                if (!safePlaces[tileY + i][tileX]) {
                    break;
                } else {
                    if (i == 2) return new Pair(tileX, tileY + 2);
                    if (isValid(tileX+1, tileY+i)) {
                        if (safePlaces[tileY + i][tileX + 1]) {
                            return new Pair(tileX + 1, tileY + i);
                        }
                    }
                    if (isValid(tileX-1, tileY+i)) {
                        if (safePlaces[tileY + i][tileX - 1]) {
                            return new Pair(tileX - 1, tileY + i);
                        }
                    }
                }
            }
        }

        //West
        for (int i = 1; i <= 2; i++) {
            if (isValid(tileX-i, tileY)) {
                if (!safePlaces[tileY][tileX - i]) {
                    break;
                } else {
                    if (i == 2) return new Pair(tileX - 2, tileY);
                    if (isValid(tileX-i, tileY + 1)) {
                        if (safePlaces[tileY + 1][tileX - i]) {
                            return new Pair(tileX - i, tileY + 1);
                        }
                    }
                    if (isValid(tileX-i, tileY-1)) {
                        if (safePlaces[tileY - 1][tileX - i]) {
                            return new Pair(tileX - i, tileY + 1);
                        }
                    }
                }
            }
        }

        //East
        for (int i = 1; i <= 2; i++) {
            if (isValid(tileX+i, tileY)) {
                if (!safePlaces[tileY][tileX + i]) {
                    break;
                } else {
                    if (i == 2) return new Pair(tileX + 2, tileY);
                    if (isValid(tileX+i, tileY+1)) {
                        if (safePlaces[tileY + 1][tileX + i]) {
                            return new Pair(tileX + i, tileY + 1);
                        }
                    }
                    if (isValid(tileX+i, tileY-1)) {
                        if (safePlaces[tileY - 1][tileX + i]) {
                            return new Pair(tileX + i, tileY - 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    public int behaviour() {
        if (waitTime > 0) {
            waitTime--;
        }
        return 0;
    }
}
