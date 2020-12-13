package main.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.GameManagement;

import java.io.File;
import java.lang.reflect.Method;

public class BGM_and_SFX {
    static boolean isBGMOn = true;
    static boolean isSFXOn = true;

    static String BGM_path = "./res/Audio/Bomberman_BGM_1.mp3";
    static String GameOver_path = "./res/Audio/Game_over.mp3";
    static String GameWinning_path = "./res/Audio/Game-winning.mp3";
    static String Explosion_path = "./res/Audio/expolsion_0.mp3";
    static String Power_up_path = "./res/Audio/Power_up.wav";
    static String Get_hit_path = "./res/Audio/Get_hit.wav";

    static Media BGM_media = new Media(new File(BGM_path).toURI().toString());
    static Media GameOver_media = new Media(new File(GameOver_path).toURI().toString());
    static Media GameWinning_media = new Media(new File(GameWinning_path).toURI().toString());
    static Media Explosion_media = new Media(new File(Explosion_path).toURI().toString());
    static Media Power_up_media = new Media(new File(Power_up_path).toURI().toString());
    static Media Get_hit_media = new Media(new File(Get_hit_path).toURI().toString());

    static MediaPlayer BGM_Player = new MediaPlayer(BGM_media);
    static MediaPlayer GameOver_Player = new MediaPlayer(GameOver_media);
    static MediaPlayer GameWinning_Player = new MediaPlayer(GameWinning_media);
    static MediaPlayer Explosion_Player = new MediaPlayer(Explosion_media);
    static MediaPlayer Power_up_Player = new MediaPlayer(Power_up_media);
    static MediaPlayer Get_hit_Player = new MediaPlayer(Get_hit_media);

    public static void turnOnBGM() {
        isBGMOn = true;
    }

    public static void turnOffBGM() {
        isBGMOn = false;
    }

    public static void turnOffSFX() {
        isSFXOn = false;
    }

    public static void turnOnSFX() {
        isSFXOn = true;
    }

    public static void pauseBGM() {
        BGM_Player.pause();
    }

    public static void resumeBGM() {
        BGM_Player.setVolume(0.1);
        BGM_Player.play();
    }

    public static void stopBGM() {
        BGM_Player.stop();
    }

    public static void playGameOver() {
        if (isBGMOn) {
            GameOver_Player.setVolume(0.1);
            GameOver_Player.play();
        }
        else {
            GameOver_Player.stop();
        }
    }

    public static void playGameWinning() {
        if (isBGMOn) {
            GameWinning_Player.setVolume(0.1);
            GameWinning_Player.play();
        }
        else {
            GameWinning_Player.stop();
        }
    }

    public static void playExplosion() {
        if (isSFXOn) {
            Explosion_Player.setVolume(0.1);
            Explosion_Player.play();
        }
        else {
            Explosion_Player.stop();
        }
    }

    public static void playPowerUp() {
        if (isSFXOn) {
            Power_up_Player.setVolume(0.1);
            Power_up_Player.play();
        }
        else {
            Power_up_Player.stop();
        }
    }

    public static void playGetHit() {
        if (isSFXOn) {
            Get_hit_Player.setVolume(0.3);
            Get_hit_Player.play();
        }
        else {
            Get_hit_Player.stop();
        }
    }

    public static void stopGameOver() {
        GameOver_Player.stop();
    }

    public static void stopGameWinning() {
        GameWinning_Player.stop();
    }

    public static void stopExplosion() {
        Explosion_Player.stop();
    }

    public static void stopPowerUp() {
        Power_up_Player.stop();
    }

    public static void stopGetHit() {
        Get_hit_Player.stop();
    }





}