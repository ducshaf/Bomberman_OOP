package main.Input;

import javafx.scene.Scene;

public class InputManager {
    private static boolean up = false, down = false, left = false, right = false, space = false;
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
                case SPACE:
                    space = true;
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
                case SPACE:
                    space = false;
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
}
