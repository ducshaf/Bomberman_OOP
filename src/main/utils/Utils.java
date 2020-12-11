package main.utils;

public class Utils {
    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;

    public static final int ROW = 11;
    public static final int COL = 15;

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    // Vì nhân, chia float/double không chính xác tí nào.
    public static double getPreciseDouble(double x) {
        return Math.round(x * 10000) / 10000.0;
    }

    public static int getTileX(double x) {
        return (int) (Utils.getPreciseDouble(x/1.6 - 32*2)/32);
    }

    public static int getTileY(double y) {
        return (int) (Utils.getPreciseDouble(y/1.6 - 32*3)/32);
    }
}
