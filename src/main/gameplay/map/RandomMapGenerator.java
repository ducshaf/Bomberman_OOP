package main.gameplay.map;

import javafx.scene.image.Image;
import main.GameManagement;
import main.entities.Bomber;
import main.entities.Grass;
import main.entities.Wall;

import java.util.Random;

public class RandomMapGenerator {
    private static final int WIDTH = 15;
    private static final int HEIGHT = 11;
    private static int[][] map = new int[HEIGHT][WIDTH];

    public static int[][] getMap() {
        return map;
    }

    public static void generateMap() {

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == 0 && j == 0) {
                    map[i][j] = 1;                          //set player
                } else if (i % 2 == 1 && j % 2 == 1) {
                    map[i][j] = 2;                          // set wall
                } else {
                    Random appearRate = new Random();
                    int rate = appearRate.nextInt(300);
                    if (rate <= 40 && (i > 5 || j > 5)) { // 15 tile thì có 1 là enemy
                        if (rate <= 15) {
                            map[i][j] = 3;                  //set standard enemy
                        } else if (rate <= 25) {
                            map[i][j] = 4;                  // set advanced enemy
                        } else if (rate <= 30) {
                            map[i][j] = 5;                  // set ghost
                        } else if (rate <= 35){
                            map[i][j] = 6;                  // set boss monster
                        } else {
                            map[i][j] = 7;                  //set evil bomber
                        }

                        // set ground for enemy
                        makeGround(i, j);
                    } else if (i > 1 || j > 1) {
                        if (rate > 250) {
                            map[i][j] = 9;                  //set box with power-up
                        } else map[i][j] = 8;               //set box
                    }
                }
            }
        }

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                switch (map[i][j]) { // -> adđ entity
                    case 1: // Player
                        GameManagement.entities.add(new Bomber(j, i, new Image("/player_down.png")));
                        break;
                    case 2: // Wall
                        GameManagement.entities.add(new Wall(j, i, new Image("/wall.png")));
                        break;
                    case 3: // Standard enemy
                        break;
                    case 4: // Advance enemy
                        break;
                    case 5: // Ghost
                        break;
                    case 6: // Boss
                        break;
                    case 7: // AI Bomber?
                        break;
                    case 8: // box
                        GameManagement.entities.add(new Grass(j, i, new Image("/box.png")));
                        break;
                    case 9: //box with power-ups
                        GameManagement.entities.add(new Grass(j, i, new Image("/box.png")));
                        break;
                }
            }
        }
    }

    public static void makeGround(int y, int x) {
        if (x % 2 == 1 && y % 2 == 0) {
            horizontalPathway(y, x);
        } else if (x % 2 == 0 && y % 2 == 1) {
            verticalPathway(y, x);
        } else {
            Random vertical = new Random();
            if (vertical.nextBoolean()) {
                verticalPathway(y, x);
            } else {
                horizontalPathway(y, x);
            }
        }
    }

    private static void verticalPathway(int y, int x) {
        for (int i = y - 2; i < y; i++) {
            if (i > 0) {
                map[i][x] = 0;
            }
        }
    }

    private static void horizontalPathway(int y, int x) {
        for (int i = x - 2; i < x; i++) {
            if (i > 0) {
                map[y][i] = 0;
            }
        }
    }

    public static void print() {
        generateMap();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
