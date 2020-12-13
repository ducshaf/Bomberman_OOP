package main.entities.mobileEntities;

import javafx.scene.image.Image;
import main.entities.mobileEntities.AI.AI;
import main.graphics.Sprite;

public class Snow_element extends Enemy{
    private AI ai = new AI();
    public Snow_element(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0.8);
    }

    @Override
    public void setDirection() {
        direction = ai.randomDirection();
    }

    @Override
    public Image chooseImage(int id) {
        if (id == -1) {
            return Sprite.getMoveSprite(Sprite.Snow_element_die_1, Sprite.Snow_element_die_2,Sprite.Snow_element_die_3,
                    Sprite.Snow_element_die_4, Sprite.Snow_element_die_5, get_animate(), 90);
        }
        switch (direction) {
            case 1:
            case 3:
                return Sprite.getMoveSprite(Sprite.Snow_element_left_1, Sprite.Snow_element_left_2,
                        Sprite.Snow_element_left_3, Sprite.Snow_element_left_4, Sprite.Snow_element_left_5,
                        Sprite.Snow_element_left_6, Sprite.Snow_element_left_7,
                        get_animate(), 40);
            case 2:
            default:
                return Sprite.getMoveSprite(Sprite.Snow_element_right_1, Sprite.Snow_element_right_2,
                        Sprite.Snow_element_right_3, Sprite.Snow_element_right_4, Sprite.Snow_element_right_5,
                        Sprite.Snow_element_right_6, Sprite.Snow_element_right_7,
                        get_animate(), 40);
        }
    }
}