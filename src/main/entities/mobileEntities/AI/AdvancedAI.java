package main.entities.mobileEntities.AI;

import main.GameManagement;
import main.entities.Entity;
import main.entities.staticEntities.Grass;
import main.utils.Utils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Math.abs;

public class AdvancedAI extends AI {
    final static int ROW = 11;
    final static int COL = 15;

    private cell[][] cells = new cell[ROW][COL];

    protected Entity enemy;
    private Stack<Pair> path = new Stack<>();
    Pair nextMove;
    boolean reachable = false;
    int cooldown = 30; // ~15s

    public AdvancedAI(Entity e) {
        super();
        enemy = e;
    }

    public int calculateDirection() {
        // Create a parallel thread to aStarSearch for path to player when cd is down or reach old goal.
        //
        if (path == null || cooldown == 30) {
            ExecutorService es = Executors.newSingleThreadExecutor();
            es.execute(new Runnable() {
                @Override
                public void run() {
                    aStarSearch(new Pair(enemy.getTileX(), enemy.getTileY()), new Pair(0, 0));
                }
            });
            es.shutdown();
        }
        // aStarSearch have a cd of ~15s (dont know how it's 15s but oh well :v)
        if (cooldown > 0) {
            cooldown--;
        } else cooldown = 30;

        // if aStarSearch return a path to goal
        if (reachable) {
            if (nextMove == null) {
                setNextMove();
                return 0;
            }

            double x = Utils.getPreciseDouble((nextMove.x + 2)*32*1.6);
            double y = Utils.getPreciseDouble((nextMove.y + 3)*32*1.6);

            // if reached node set next node from stack path.
            // else if reached the final node reset cd to aStarSearch again.
            if (enemy.getX() == x && enemy.getY() == y) {
                if (!path.isEmpty()) {
                    setNextMove();
                } else {
                    cooldown = 30;
                    reachable = false;
                }
            }

            // set direction
            // sometimes this doesnt work (return randomDirection()) bcz cd is down and aStarSearch start.
            // but it still reached goal in the end so IT'S NOT A BUG, IT'S A FEATURE!
            if (enemy.getX() > x) {
                // set direction to left
                return Utils.LEFT;
            }
            if (enemy.getX() < x) {
                // set direction to right
                return Utils.RIGHT;
            }
            if (enemy.getY() > y) {
                // set direction to up
                return Utils.UP;
            }
            if (enemy.getY() < y) {
                // set direction to down
                return Utils.DOWN;
            }
            return 0;
        }
        // else return a random direction.
        return randomDirection();
    }

    public void setNextMove() {
        nextMove = path.pop();
    }

    boolean isValid(int x, int y)  {
        return (x < COL) && (x >= 0) && (y >= 0) && (y < ROW);
    }

    boolean isBlocked(int x, int y, int[][] map) { // TODO
        return (map[y][x] != 0);
    }

    boolean isDestination(int x, int y, Pair des) {
        return (x == des.x) && (y == des.y);
    }

    double calculateDistanceBetweenNodes(int x, int y, Pair des) {
        return (abs(des.x - x) + abs(des.y - y));
    }

    void tracePath(cell[][] cellDetails, Pair des) {
        int col = des.x;
        int row = des.y;

        path.clear();
        // go through cell[][], iterate from goal back to source and push to stack.
        while (col != cellDetails[row][col].parent_j || row != cellDetails[row][col].parent_i) {
            path.push(new Pair(col, row));
            int newCol = cellDetails[row][col].parent_j;
            int newRow = cellDetails[row][col].parent_i;
            row = newRow;
            col = newCol;
        }
        path.push(new Pair(col, row));
    }

    // get a Integer 2D array from list of current static entities.
    public int[][] getMap() {
        int[][] map = new int[ROW][COL];
        Vector<Entity> entities = GameManagement.entities;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (entities.get(i*COL + j) instanceof Grass) {
                    map[i][j] = 0;
                } else map[i][j] = 1;
            }
        }
        return map;
    }

    public void aStarSearch(Pair src, Pair des) {
        int[][] map = getMap();
        if (!isValid(src.x, src.y) || !isValid(des.x, des.y)) {
            reachable = false;
            return;
        }

        if (isBlocked(src.x, src.y, map) || isBlocked(des.x, des.y, map)) {
            reachable = false;
            return;
        }

        if (isDestination(src.x, src.y, des)) {
            reachable = false;
            return;
        }

        boolean[][] closedList = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                cells[i][j] = new cell(-1, -1, 9999999, 9999999, 9999999);
            }
        }

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

            double gNew, hNew, fNew;
            // North
            if (isValid(j, i - 1)) {
                if (isDestination(j, i-1, des)) {
                    cells[i-1][j].parent_j = j;
                    cells[i-1][j].parent_i = i;

                    reachable = true;
                    tracePath(cells, des);
                    return;
                } else if (!closedList[i - 1][j] && !isBlocked(j,i-1, map)) {
                    gNew = cells[i-1][j].g + 1;
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
                if (isDestination(j, i-1, des)) {
                    cells[i+1][j].parent_j = j;
                    cells[i+1][j].parent_i = i;

                    reachable = true;
                    tracePath(cells, des);
                    return;
                } else if (!closedList[i + 1][j] && !isBlocked(j,i+1, map)) {
                    gNew = cells[i+1][j].g + 1;
                    hNew = calculateDistanceBetweenNodes((j), i+1, des);
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
                if (isDestination(j -1, i, des)) {
                    cells[i][j-1].parent_j = j;
                    cells[i][j-1].parent_i = i;

                    reachable = true;
                    tracePath(cells, des);
                    return;
                } else if (!closedList[i][j - 1] && !isBlocked(j-1,i, map)) {
                    gNew = cells[i][j-1].g + 1;
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
                }
            }

            //East
            if (isValid(j+1, i)) {
                if (isDestination(j+1, i, des)) {
                    cells[i][j+1].parent_j = j;
                    cells[i][j+1].parent_i = i;

                    reachable = true;
                    tracePath(cells, des);
                    return;
                } else if (!closedList[i][j + 1] && !isBlocked(j+1,i, map)) {
                    gNew = cells[i][j+1].g + 1;
                    hNew = calculateDistanceBetweenNodes((j+1), i, des);
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

}

class pPair {
    Pair cord;
    double f;

    public pPair(Pair cord, double f) {
        this.cord = cord;
        this.f = f;
    }
}
class cell {
    int parent_i, parent_j;
    // f = g + h
    double f, g, h;

    public cell(int parent_i, int parent_j, double f, double g, double h) {
        this.parent_i = parent_i;
        this.parent_j = parent_j;
        this.f = f;
        this.g = g;
        this.h = h;
    }
}
