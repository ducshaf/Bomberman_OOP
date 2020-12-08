package main.gameplay.inputHandler;

import javafx.scene.Scene;

public class InputManager {
    private static boolean up = false, down = false, left = false, right = false, space = false,
            f1 = false, f2 = false, f3 = false, f4 = false, f5 = false, f6 = false, f7 = false, f8 = false, f9 = false;
    private static long lastPressProcessed = 0;
    public static void keyboardHandle(Scene s) {
        s.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:
                    up = true;
                    break;
                case S:
                    down = true;
                    break;
                case A:
                    left = true;
                    break;
                case D:
                    right = true;
                    break;
                case H:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        space = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F1:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f1 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F2:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f2 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F3:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f3 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F4:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f4 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F5:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f5 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F6:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f6 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F7:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f7 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F8:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f8 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F9:
                    if (System.currentTimeMillis() - lastPressProcessed > 5000) {
                        f9 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
            }
        });
        s.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:
                    up = false;
                    break;
                case S:
                    down = false;
                    break;
                case A:
                    left = false;
                    break;
                case D:
                    right = false;
                    break;
                case F1:
                    f1 = false;
                    break;
                case F2:
                    f2 = false;
                    break;
                case F3:
                    f3 = false;
                    break;
                case F4:
                    f4 = false;
                    break;
                case F5:
                    f5 = false;
                    break;
                case F6:
                    f6 = false;
                    break;
                case F7:
                    f7 = false;
                    break;
                case F8:
                    f8 = false;
                    break;
                case F9:
                    f9 = false;
                    break;
            }
        });
    }


    public static boolean isUp() {
        return up;
    }

    public static boolean isDown() {
        return down;
    }

    public static boolean isLeft() {
        return left;
    }

    public static boolean isRight() {
        return right;
    }

    public static boolean isSpace() {
        return space;
    }

    public static boolean isF1() {return f1;}

    public static boolean isF2() {
        return f2;
    }

    public static boolean isF3() {
        return f3;
    }

    public static boolean isF4() {
        return f4;
    }

    public static boolean isF5() {
        return f5;
    }

    public static boolean isF6() {
        return f6;
    }

    public static boolean isF7() {
        return f7;
    }

    public static boolean isF8() {
        return f8;
    }

    public static boolean isF9() {
        return f9;
    }

    public static void setSpace() {
        space = false;
    }
}
