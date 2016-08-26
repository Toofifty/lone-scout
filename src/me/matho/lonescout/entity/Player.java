package me.matho.lonescout.entity;

import me.matho.lonescout.client.Resources;
import org.newdawn.slick.*;

/**
 * Created by alex on 21/08/2016.
 */
public class Player extends Entity {

    private float speed = 0.15F;
    private float jumpSpeed = 0.4F;

    @Override
    public void init() {

        x = 200;
        y = 600;

        w = 16;
        h = 16;

        image = Resources.getSprite("player/pluto/still", 0, 0);

        addAnimation("idle_unarmed", new Animation(Resources.getSpriteSheet("player/pluto/idle_unarmed"), 500));
        addAnimation("run_unarmed", new Animation(Resources.getSpriteSheet("player/pluto/run_unarmed"), 50));
        setAnimation("idle_unarmed");

    }

    @Override
    public void update(GameContainer container, int delta) {

        Input input = container.getInput();

        vx = 0;

        if (input.isKeyDown(Input.KEY_A)) vx -= speed;
        if (input.isKeyDown(Input.KEY_D)) vx += speed;

        if (input.isKeyDown(Input.KEY_SPACE) && grounded) {

            vy = -jumpSpeed;
            grounded = false;

        }

        super.update(container, delta);

    }

    @Override
    public void updateAnimation() {

        if (vx != 0) {

            setAnimation("run_unarmed");

        } else {

            setAnimation("idle_unarmed");

        }

    }

    @Override
    public String getExamine() {
        return "Player";
    }
}
