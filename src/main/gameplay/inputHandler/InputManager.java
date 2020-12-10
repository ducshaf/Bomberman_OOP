package main.gameplay.inputHandler;

import javafx.scene.Scene;

public class InputManager {
    private static boolean up = false, down = false, left = false, right = false, setBomb = false, controlBomb = false,
            f1 = false, f2 = false, f3 = false, f4 = false, f5 = false, f6 = false, f7 = false, f8 = false, f9 = false;

    private static boolean inverted = false;
    private static long lastPressProcessed = 0;

    public static void keyboardHandle(Scene s) {
        s.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:
                    if (!inverted){
                        up = true;
                    } else {
                        down = true;
                    }
                    break;
                case S:
                    if (!inverted) {
                        down = true;
                    } else {
                        up = true;
                    }
                    break;
                case A:
                    if (!inverted) {
                        left = true;
                    } else {
                        right = true;
                    }
                    break;
                case D:
                    if (!inverted) {
                        right = true;
                    } else {
                        left = true;
                    }
                    break;
                case H:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        setBomb = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case K:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        controlBomb = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F1:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f1 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F2:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f2 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F3:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f3 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F4:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f4 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F5:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f5 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F6:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f6 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F7:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f7 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F8:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f8 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
                case F9:
                    if (System.currentTimeMillis() - lastPressProcessed > 200) {
                        f9 = true;
                        lastPressProcessed = System.currentTimeMillis();
                    }
                    break;
            }
        });
        s.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:
                    if (!inverted){
                        up = false;
                    } else {
                        down = false;
                    }
                    break;
                case S:
                    if (!inverted) {
                        down = false;
                    } else {
                        up = false;
                    }
                    break;
                case A:
                    if (!inverted) {
                        left = false;
                    } else {
                        right = false;
                    }
                    break;
                case D:
                    if (!inverted) {
                        right = false;
                    } else {
                        left = false;
                    }
                    break;
                case K:
                    controlBomb = false;
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

    public static boolean isSetBomb() {
        return setBomb;
    }

    public static boolean isControlBomb() {
        return controlBomb;
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
        setBomb = false;
    }

    public static void setInverted(boolean state) {
        inverted = state;
    }

}
