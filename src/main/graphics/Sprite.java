package main.graphics;

import javafx.scene.image.*;

import java.util.Arrays;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
	public static final int DEFAULT_SIZE = 16;
	public static final int SCALED_SIZE = DEFAULT_SIZE * 2;

	/***********************************************************************************
	 * Player Sprites
	 ***********************************************************************************/
	public static final Image player_down = new Image("./sprites/player/player_down.png");
	public static final Image player_down_1 = new Image("./sprites/player/player_down_1.png");
	public static final Image player_down_2 = new Image("./sprites/player/player_down_2.png");

	public static final Image player_up = new Image("./sprites/player/player_up.png");
	public static final Image player_up_1 = new Image("./sprites/player/player_up_1.png");
	public static final Image player_up_2 = new Image("./sprites/player/player_up_2.png");

	public static final Image player_left = new Image("./sprites/player/player_left.png");
	public static final Image player_left_1 = new Image("./sprites/player/player_left_1.png");
	public static final Image player_left_2 = new Image("./sprites/player/player_left_2.png");

	public static final Image player_right = new Image("./sprites/player/player_right.png");
	public static final Image player_right_1 = new Image("./sprites/player/player_right_1.png");
	public static final Image player_right_2 = new Image("./sprites/player/player_right_2.png");

	public static final Image player_die = new Image("./sprites/player/animation_die1.png");
	public static final Image player_die_1 = new Image("./sprites/player/animation_die2.png");
	public static final Image player_die_2 = new Image("./sprites/player/animation_die3.png");

	/***********************************************************************************
	 * Slime Sprites
	 ***********************************************************************************/
	public static final Image slime_move_0 = new Image("./sprites/slime/slime_0.png");
	public static final Image slime_move_1 = new Image("./sprites/slime/slime_1.png");
	public static final Image slime_move_2 = new Image("./sprites/slime/slime_2.png");
	public static final Image slime_move_3 = new Image("./sprites/slime/slime_3.png");

	public static final Image slime_die_0 = new Image("./sprites/slime/slime_die_0.png");
	public static final Image slime_die_1 = new Image("./sprites/slime/slime_die_1.png");
	public static final Image slime_die_2 = new Image("./sprites/slime/slime_die_2.png");
	public static final Image slime_die_3 = new Image("./sprites/slime/slime_die_3.png");
	public static final Image slime_die_4 = new Image("./sprites/slime/slime_die_4.png");

	/***********************************************************************************
	 * Ghost Sprites
	 ***********************************************************************************/
	public static final Image ghost_up_0 = new Image("./sprites/ghost/ghost_up_0.png");
	public static final Image ghost_up_1 = new Image("./sprites/ghost/ghost_up_1.png");
	public static final Image ghost_up_2 = new Image("./sprites/ghost/ghost_up_2.png");

	public static final Image ghost_down_0 = new Image("./sprites/ghost/ghost_down_0.png");
	public static final Image ghost_down_1 = new Image("./sprites/ghost/ghost_down_1.png");
	public static final Image ghost_down_2 = new Image("./sprites/ghost/ghost_down_2.png");

	public static final Image ghost_left_0 = new Image("./sprites/ghost/ghost_left_0.png");
	public static final Image ghost_left_1 = new Image("./sprites/ghost/ghost_left_1.png");
	public static final Image ghost_left_2 = new Image("./sprites/ghost/ghost_left_2.png");

	public static final Image ghost_right_0 = new Image("./sprites/ghost/ghost_right_0.png");
	public static final Image ghost_right_1 = new Image("./sprites/ghost/ghost_right_1.png");
	public static final Image ghost_right_2 = new Image("./sprites/ghost/ghost_right_2.png");

	public static Image getMoveSprite(Image x0, Image x1, Image x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;

		if (calc < diff) {
			return x0;
		}
		if (calc < diff*2) {
			return x1;
		}
		return x2;
	}

	public static Image getMoveSprite(Image x0, Image x1, Image x2, Image x3, int animate, int time) {
		int calc = animate % time;
		int diff = time / 4;

		if (calc < diff) {
			return x0;
		}
		if (calc < diff*2) {
			return x1;
		}
		if (calc < diff*3) {
			return x2;
		}
		return x3;
	}

	public static Image getMoveSprite(Image x0, Image x1, Image x2, Image x3, Image x4, int animate, int time) {
		int calc = animate % time;
		int diff = time / 5;

		if (calc < diff) {
			return x0;
		}
		if (calc < diff*2) {
			return x1;
		}
		if (calc < diff*3) {
			return x2;
		}
		if (calc < diff*4) {
			return x3;
		}
		return x4;
	}
}
