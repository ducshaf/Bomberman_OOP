package main.entities.mobileEntities.AI;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Entity;
import main.entities.bomb.Bomb;
import main.entities.mobileEntities.EvilBomb;
import main.entities.staticEntities.DestroyableWall;
import main.entities.staticEntities.Grass;
import main.entities.staticEntities.Wall;
import main.entities.statusEffect.StatusEffect;
import main.utils.Utils;

import java.util.*;

import static java.lang.Math.abs;
import static main.utils.Utils.COL;
import static main.utils.Utils.ROW;

public class BomberAI {
    protected EvilBomb enemy;
    private cell[][] cells = new cell[ROW][COL];
    private Stack<Pair> path = new Stack<>();
    protected int[][] map = new int[ROW][COL];
    Pair nextMove;
    public boolean reachDes = true;
    private boolean bomb = false;

    final int SAFE_DISTANCE = 2;

    public BomberAI(EvilBomb e) {
        enemy = e;
    }

    public int calculateDirection() {
        if (reachDes) {
            path.clear();
            nextMove = null;
            Pair currentPos = new Pair(enemy.getTileX(), enemy.getTileY());
            if (!bomb) {
                aStarSearch(currentPos, new Pair(GameManagement.getPlayer().getTileX(), GameManagement.getPlayer().getTileY()));
            } else if (bomb) {
                Pair safe = findSafePlace(currentPos);
                aStarSearch(new Pair(enemy.getTileX(), enemy.getTileY()), safe);
                System.out.println(safe.x + "," + safe.y);
                bomb = false;
            }
            reachDes = false;
        } else if (!reachDes) {
            if (nextMove == null) {
                setNextMove();
            }

            double x = Utils.getPreciseDouble((nextMove.x + 2)*32*1.6);
            double y = Utils.getPreciseDouble((nextMove.y + 3)*32*1.6);

            if (enemy.getX() == x && enemy.getY() == y) {
                if (!path.empty()) {
                    setNextMove();
                } else {
                    reachDes = true;
                }
                if (reachDes) return 0;
            }

            if (enemy.getY() == y) {
                if (enemy.getX() > x) {
                    return Utils.LEFT;
                }
                if (enemy.getX() < x) {
                    return Utils.RIGHT;
                }
            }
            if (enemy.getX() == x) {
                if (enemy.getY() > y) {
                    return Utils.UP;
                }
                if (enemy.getY() < y) {
                    return Utils.DOWN;
                }
            }
        }
        return 0;
    }

    public void setNextMove() {
        nextMove = path.pop();
        if (GameManagement.entities.get(nextMove.y*15 + nextMove.x) instanceof DestroyableWall) {
            GameManagement.bombs.add(new Bomb(enemy.getTileX(), enemy.getTileY(), new Image("./sprites/bomb/bomb1.png"), 1));
            reachDes = true;
            bomb = true;
        }
    }

    public boolean isSafe(Pair src, Pair des) {
        if ((src.x != des.x && src.y != des.y)
                || Math.abs(des.x - src.x) >= SAFE_DISTANCE || Math.abs(des.y - src.y) >= SAFE_DISTANCE) {
            return true;
        }
        return false;
    }

    boolean isValid(int x, int y)  {
        return (x < COL) && (x >= 0) && (y >= 0) && (y < ROW);
    }

    boolean isBlocked(int x, int y) {
        return (map[y][x] != 0);
    }

    boolean isDestroyable(int x, int y) {
        return (map[y][x] == 1);
    }

    boolean isDestination(int x, int y, Pair des) {
        return (x == des.x) && (y == des.y);
    }

    double calculateDistanceBetweenNodes(int x, int y, Pair des) {
        return (abs(des.x - x) + abs(des.y - y));
    }

    void tracePath(cell[][] cellDetails, Pair des, Pair src) {
        int col = des.x;
        int row = des.y;
        path.clear();

        while (col != src.x || row != src.y) {
            path.push(new Pair(col, row));
            int newCol = cellDetails[row][col].parent_j;
            int newRow = cellDetails[row][col].parent_i;
            row = newRow;
            col = newCol;
        }
        path.push(new Pair(col, row));
    }

    // update map.
    public void getMap() {
        Vector<Entity> entities = GameManagement.entities;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (entities.get(i*COL + j) instanceof Grass || entities.get(i*COL + j) instanceof StatusEffect) {
                    map[i][j] = 0;
                } else if (entities.get(i*COL + j) instanceof DestroyableWall) {
                    map[i][j] = 1;
                } else if (entities.get(i*COL + j) instanceof Wall) map[i][j] = 2;
            }
        }
    }

    public void resetCells() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                cells[i][j] = new cell(-1, -1, 9999999, 9999999, 9999999);
            }
        }
    }

    public void aStarSearch(Pair src, Pair des) {
        if (isDestination(src.x, src.y, des)) {
            return;
        }
        if (!isValid(src.x, src.y) || !isValid(des.x, des.y)) {
            return;
        }
        getMap();

        boolean[][] closedList = new boolean[ROW][COL];
        resetCells();

        int i = src.y;
        int j = src.x;
        cells[i][j].f = 0;
        cells[i][j].h = 0;
        cells[i][j].g = 0;
        cells[i][j].parent_j = j;
        cells[i][j].parent_i = i;

        Set<pPair> openList = new LinkedHashSet<>();
        openList.add(new pPair(new Pair(j, i), 0));

        while (!openList.isEmpty()) {
            pPair a = openList.iterator().next();
            openList.remove(a);

            i = a.cord.y;
            j = a.cord.x;
            closedList[i][j] = true;

            if (isDestination(j, i, des)) {
                tracePath(cells, des, src);
                return;
            }
            double gNew, hNew, fNew;

            // North
            if (isValid(j, i - 1)) {
                if (!closedList[i - 1][j] && !isBlocked(j, i-1)) {
                    gNew = cells[i][j].g + 1;
                    hNew = calculateDistanceBetweenNodes(j, i-1, des);
                    fNew = gNew + hNew;

                    if (cells[i-1][j].f == 9999999 || cells[i-1][j].f > fNew) {
                        openList.add(new pPair(new Pair(j, i-1), fNew));
                        cells[i-1][j].f = fNew;
                        cells[i-1][j].g = gNew;
                        cells[i-1][j].f = fNew;
                        cells[i-1][j].parent_i = i;
                        cells[i-1][j].parent_j = j;
                    }
                } else if (!closedList[i - 1][j] && isDestroyable(j, i-1)) {
                    gNew = cells[i][j].g + 5;
                    hNew = calculateDistanceBetweenNodes(j, i-1, des);
                    fNew = gNew + hNew;

                    if (cells[i-1][j].f == 9999999 || cells[i-1][j].f > fNew) {
                        openList.add(new pPair(new Pair(j, i-1), fNew));
                        cells[i-1][j].f = fNew;
                        cells[i-1][j].g = gNew;
                        cells[i-1][j].f = fNew;
                        cells[i-1][j].parent_i = i;
                        cells[i-1][j].parent_j = j;
                    }
                }
            }
            //South
            if (isValid(j, i + 1)) {
                if (!closedList[i + 1][j] && !isBlocked(j,i+1)) {
                    gNew = cells[i][j].g + 1;
                    hNew = calculateDistanceBetweenNodes(j, i+1, des);
                    fNew = gNew + hNew;

                    if (cells[i+1][j].f == 9999999 || cells[i+1][j].f > fNew) {
                        openList.add(new pPair(new Pair(j, i+1), fNew));
                        cells[i+1][j].f = fNew;
                        cells[i+1][j].g = gNew;
                        cells[i+1][j].f = fNew;
                        cells[i+1][j].parent_i = i;
                        cells[i+1][j].parent_j = j;
                    }
                } else if (!closedList[i + 1][j] && isDestroyable(j, i+1)) {
                    gNew = cells[i][j].g + 5;
                    hNew = calculateDistanceBetweenNodes(j, i+1, des);
                    fNew = gNew + hNew;

                    if (cells[i+1][j].f == 9999999 || cells[i+1][j].f > fNew) {
                        openList.add(new pPair(new Pair(j, i+1), fNew));
                        cells[i+1][j].f = fNew;
                        cells[i+1][j].g = gNew;
                        cells[i+1][j].f = fNew;
                        cells[i+1][j].parent_i = i;
                        cells[i+1][j].parent_j = j;
                    }
                }
            }

            // West
            if (isValid(j - 1, i)) {
                if (!closedList[i][j - 1] && !isBlocked(j-1,i)) {
                    gNew = cells[i][j].g + 1;
                    hNew = calculateDistanceBetweenNodes((j-1), i, des);
                    fNew = gNew + hNew;

                    if (cells[i][j-1].f == 9999999 || cells[i][j-1].f > fNew) {
                        openList.add(new pPair(new Pair(j-1, i), fNew));
                        cells[i][j-1].f = fNew;
                        cells[i][j-1].g = gNew;
                        cells[i][j-1].f = fNew;
                        cells[i][j-1].parent_i = i;
                        cells[i][j-1].parent_j = j;
                    }
                } else if (!closedList[i][j - 1] && isDestroyable(j-1, i)) {
                    gNew = cells[i][j].g + 5;
                    hNew = calculateDistanceBetweenNodes(j-1, i, des);
                    fNew = gNew + hNew;

                    if (cells[i][j-1].f == 9999999 || cells[i][j-1].f > fNew) {
                        openList.add(new pPair(new Pair(j-1, i), fNew));
                        cells[i][j-1].f = fNew;
                        cells[i][j-1].g = gNew;
                        cells[i][j-1].f = fNew;
                        cells[i][j-1].parent_i = i;
                        cells[i][j-1].parent_j = j;
                    }
                }
            }

            //East
            if (isValid(j+1, i)) {
                if (!closedList[i][j + 1] && !isBlocked(j+1,i)) {
                    gNew = cells[i][j].g + 1;
                    hNew = calculateDistanceBetweenNodes(j+1, i, des);
                    fNew = gNew + hNew;

                    if (cells[i][j+1].f == 9999999 || cells[i][j+1].f > fNew) {
                        openList.add(new pPair(new Pair(j+1, i), fNew));
                        cells[i][j+1].f = fNew;
                        cells[i][j+1].g = gNew;
                        cells[i][j+1].f = fNew;
                        cells[i][j+1].parent_i = i;
                        cells[i][j+1].parent_j = j;
                    }
                } else if (!closedList[i][j + 1] && isDestroyable(j+1, i)) {
                    gNew = cells[i][j].g + 5;
                    hNew = calculateDistanceBetweenNodes(j+1, i, des);
                    fNew = gNew + hNew;

                    if (cells[i][j+1].f == 9999999 || cells[i][j+1].f > fNew) {
                        openList.add(new pPair(new Pair(j+1, i), fNew));
                        cells[i][j+1].f = fNew;
                        cells[i][j+1].g = gNew;
                        cells[i][j+1].f = fNew;
                        cells[i][j+1].parent_i = i;
                        cells[i][j+1].parent_j = j;
                    }
                }
            }
        }
    }

    public Pair findSafePlace(Pair src) {
        getMap();
        boolean[][] closedList = new boolean[ROW][COL];

        Set<pPair> openList = new LinkedHashSet<>();
        openList.add(new pPair(src, 0));
        int i = src.y;
        int j = src.x;

        while (!openList.isEmpty()) {
            pPair a = openList.iterator().next();
            openList.remove(a);

            i = a.cord.y;
            j = a.cord.x;
            closedList[i][j] = true;


            // North
            if (isValid(j, i-1) && a.f <= SAFE_DISTANCE && !isBlocked(j, i-1)) {
                Pair des = new Pair(j, i-1);
                if (isSafe(src, des)) {
                    return des;
                } else if (!closedList[i-1][j]) {
                    openList.add(new pPair(des, a.f + 1));
                }
            }

            // South
            if (isValid(j, i+1) && a.f <= SAFE_DISTANCE && !isBlocked(j, i+1)) {
                Pair des = new Pair(j, i+1);
                if (isSafe(src, des)) {
                    return des;
                } else if (!closedList[i+1][j]) {
                    openList.add(new pPair(des,a.f + 1));
                }
            }

            // West
            if (isValid(j-1, i) && a.f <= SAFE_DISTANCE && !isBlocked(j-1, i)) {
                Pair des = new Pair(j-1, i);
                if (isSafe(src, des)) {
                    return des;
                } else if (!closedList[i][j-1]) {
                    openList.add(new pPair(des, a.f + 1));
                }
            }

            // East
            if (isValid(j+1, i) && a.f <= SAFE_DISTANCE && !isBlocked(j+1, i)) {
                Pair des = new Pair(j+1, i);
                if (isSafe(src, des)) {
                    return des;
                } else if (!closedList[i][j+1]) {
                    openList.add(new pPair(des, a.f + 1));
                }
            }
        }
        return src;
    }
}

