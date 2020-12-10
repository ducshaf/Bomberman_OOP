package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.entities.mobileEntities.AI.AdvancedAI;
import main.graphics.Sprite;

public class Slime extends Enemy {
    private AdvancedAI slime = new AdvancedAI(this);
    public Slime(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0.8);
    }

    @Override
    public void setDirection() {
        direction = slime.calculateDirection();
    }

    @Override
    public Image chooseImage(int id) {
        if (id == -1) {
            return Sprite.getMoveSprite(Sprite.slime_die_0, Sprite.slime_die_1, Sprite.slime_die_2,
                    Sprite.slime_die_3, Sprite.slime_die_4, get_animate(), 120);
        }
        return Sprite.getMoveSprite(Sprite.slime_move_0, Sprite.slime_move_1,
                Sprite.slime_move_2, Sprite.slime_move_3, get_animate(), 45);
    }
}
