package main.audio;

import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SoundManager {
    static ExecutorService soundPol = Executors.newFixedThreadPool(2);
    static HashMap<String, AudioClip> sfxMap = new HashMap<>();

    public SoundManager(int threads) {
        soundPol = Executors.newFixedThreadPool(threads);
    }

    public void loadSFX() {
        AudioClip explosion = new AudioClip("");
        sfxMap.put("explosion", explosion);
        AudioClip click = new AudioClip("");
        sfxMap.put("click", click);
    }
}
