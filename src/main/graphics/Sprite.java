package main.graphics;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

	/***********************************************************************************
	 * Icon Sprites
	 ***********************************************************************************/
	public static final Image agile = new Image("./sprites/power-ups/move_faster.png");
	public static final Image fierce = new Image("./sprites/power-ups/fierce_bomb.png");
	public static final Image freeze = new Image("./sprites/power-ups/freeze_time.png");
	public static final Image heal = new Image("./sprites/power-ups/hp.png");
	public static final Image bombQualityUp = new Image("./sprites/power-ups/stronger_bomb.png");
	public static final Image bombQuantityUp = new Image("./sprites/power-ups/more_bombs.png");
	public static final Image invincibility = new Image("./sprites/power-ups/invincibility.png");
	public static final Image percolate = new Image("./sprites/power-ups/pass_through.png");
	public static final Image force = new Image("./sprites/power-ups/the_force.png");
	public static final Image time = new Image("./sprites/power-ups/timed_bomb.png");
	public static final Image blind = new Image("./sprites/power-ups/blind_debuff.png");
	public static final Image inversion = new Image("./sprites/power-ups/inversion_debuff.png");
	public static final Image slow = new Image("./sprites/power-ups/move_slower.png");
	public static final Image random = new Image("./sprites/power-ups/random_effect.png");
	public static final Image red_heart = new Image("./UI/red.png");
	public static final Image white_heart = new Image("./UI/white.png");

	/***********************************************************************************
	 * Number
	 ***********************************************************************************/
	public static final Image zero = new Image("./UI/0.png");
	public static final Image one = new Image("./UI/1.png");
	public static final Image two = new Image("./UI/2.png");
	public static final Image three = new Image("./UI/3.png");
	public static final Image four = new Image("./UI/4.png");
	public static final Image five = new Image("./UI/5.png");
	public static final Image six = new Image("./UI/6.png");
	public static final Image seven = new Image("./UI/7.png");
	public static final Image eight = new Image("./UI/8.png");
	public static final Image nine = new Image("./UI/9.png");


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

	public static Image getNumber(int time) {
		switch (time/60) {
			case 0:
				return zero;
			case 1:
				return one;
			case 2:
				return two;
			case 3:
				return three;
			case 4:
				return four;
			case 5:
				return five;
			case 6:
				return six;
			case 7:
				return seven;
			case 8:
				return eight;
			case 9:
				return nine;
			default:
				int i = time/600;
				int j = (time/60)%10;
				return concatenateImages(getNumber(i*60), getNumber(j*60));
		}
	}

	private static Image concatenateImages(Image number, Image number1) {
		BufferedImage n1 = SwingFXUtils.fromFXImage(number, null);
		BufferedImage n2 = SwingFXUtils.fromFXImage(number1, null);

		BufferedImage combined = new BufferedImage(256, 192, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = combined.createGraphics();
		graphics2D.drawImage(n1, 0, 0, null);
		graphics2D.drawImage(n2, 128, 0, null);

		return SwingFXUtils.toFXImage(combined, null);
	}


}
