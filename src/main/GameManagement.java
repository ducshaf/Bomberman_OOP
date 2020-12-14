package main;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import main.audio.BGM_and_SFX;
import main.entities.bomb.Bomb;
import main.entities.mobileEntities.Bomber;
import main.entities.staticEntities.DestroyableWall;
import main.entities.staticEntities.Grass;
import main.entities.statusEffect.StatusEffect;
import main.gameplay.inputHandler.InputManager;
import main.entities.Entity;
import main.gameplay.map.MapGenerator;
import main.graphics.Layer;
import main.gui.App;
import main.gui.UI;
import main.utils.Utils;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;


public class GameManagement {
    static Stage primaryStage = BombermanGame.getPrimaryStage();
    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;

    private static AnimationTimer timer;
    private static long currentGameTime = 0;
    private static long startNanoTime;
    public static boolean isPaused = false;

    public static Vector<Entity> entities = new Vector<>();
    public static Vector<Entity> mobileEntities = new Vector<>();
    public static Vector<Entity> bombs = new Vector<>();

    public static boolean isBlind = false;
    public static boolean isFreeze = false;


    public static Bomber getPlayer() {
        if (mobileEntities.get(0) instanceof Bomber){
            return (Bomber) mobileEntities.get(0);
        }
        return null;
    }

    public static Entity getStaticEntityAt(double x, double y) {
        int tileX = (int) Utils.getPreciseDouble((x/1.6 - 32*2)/32);
        int tileY = (int) Utils.getPreciseDouble((y/1.6 - 32*3)/32);
        return entities.get((tileY)*15 + (tileX));
    }

    public static Scene getScene() {
        return s;
    }

    public static long getCurrentGameTime() {
        return currentGameTime;
    }

    public static void start() {
        BGM_and_SFX.stopGameOver();
        BGM_and_SFX.stopGameWinning();
        BGM_and_SFX.resumeBGM();
        currentGameTime = 0;
        startNanoTime = System.nanoTime();

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ((l - startNanoTime) / (1000000000/60) > currentGameTime) {
                    if (isPaused) {
                        startNanoTime += (1000000000/60);
                    } else {
                        ++currentGameTime;
                    }
                }
                if (!isPaused){
                    gc.clearRect(0, 0, Utils.WIDTH, Utils.HEIGHT);
                    update();
                    render();
                }
            }
        };
        timer.start();
    }

// Render layer ground -> entities -> overlay.
    private static void render() {
        Layer.drawGroundLayer();
        Layer.drawMobileLayer(entities);
        Layer.drawMobileLayer(mobileEntities);
        Layer.drawMobileLayer(bombs);
        Layer.drawOverlay();
        if (isBlind) Layer.blind();
        if (isFreeze) Layer.freezeTime();
    }

// update game logic, xử lí input từ bàn phím, update entities.
    public static void update() {
        if (getPlayer() == null) {
            if (bombs.isEmpty()) pause(1);
        }
        if (mobileEntities.size() == 1) {
            if (bombs.isEmpty()) pause(2);
        }
        if (!isFreeze) {
            for (Iterator<Entity> e = bombs.iterator(); e.hasNext();) {
                Entity bomb = e.next();
                bomb.update();
                if (!bomb.isAlive()) {
                    e.remove();
                    Bomber.bombs.remove((Bomb) bomb);
                }
            }
        }

        for (Iterator<Entity> e = mobileEntities.iterator(); e.hasNext();) {
            Entity mob = e.next();
            if (!isFreeze) {
                mob.update();
            } else {
                if (mob instanceof Bomber) {
                    mob.update();
                }
            }
            if (!mob.isAlive() && !(mob instanceof Bomber)) {
                e.remove();
            } else if (!mob.isAlive() && (mob instanceof Bomber)) pause(1);
        }

        for (ListIterator<Entity> e = entities.listIterator(); e.hasNext();) {
            Entity staticEntity = e.next();
            staticEntity.update();
            if (!staticEntity.isAlive()) {
                Random random = new Random();
                if (random.nextInt(50) < 20 && staticEntity instanceof DestroyableWall) {
                    e.set(StatusEffect.initStatusEffect(staticEntity.getTileX(), staticEntity.getTileY()));
                } else e.set(new Grass(staticEntity.getTileX(), staticEntity.getTileY(), null));
            }
        }
    }

    public static void init() {
        root = new Group();
        s = new Scene(root, Utils.WIDTH, Utils.HEIGHT);
        c = new Canvas(Utils.WIDTH, Utils.HEIGHT);
        isPaused = false;

        s.getStylesheets().add("./utils/style.css");

        root.getChildren().addAll(c, UI.menu);
        gc = c.getGraphicsContext2D();
        Layer.setGraphicsContext(gc);
        InputManager.keyboardHandle(s);
        // generate a random map.
        MapGenerator.generateMap();
        //generate map from txt file.
        //MapGenerator.inputMap();
        // start game loop.
        start();
    }

    public static void pause(int id) {
        isPaused = true;
        if (id == 0) root.getChildren().add(UI.pause);
        if (id == 1){
            BGM_and_SFX.stopBGM();
            BGM_and_SFX.playGameOver();

            root.getChildren().add(UI.gameOver);
        }
        if (id == 2){
            BGM_and_SFX.stopBGM();
            BGM_and_SFX.playGameWinning();
            root.getChildren().add(UI.victory);
        }
    }

    public static void resume() {
        root.getChildren().remove(UI.pause);
        isPaused = false;
    }

    public static void reset() {
        timer.stop();
        entities.clear();
        mobileEntities.clear();
        bombs.clear();
        root.getChildren().removeAll(UI.gameOver, UI.pause, UI.victory);
        MapGenerator.addEntities();
        isPaused = false;
        isFreeze = false;
        isBlind = false;
        InputManager.setInverted(false);
        start();
    }

    public static void exit() {
        timer.stop();
        entities.clear();
        mobileEntities.clear();
        App.init();
        Scene scene = App.getScene();
        primaryStage.setScene(scene);
    }
}
