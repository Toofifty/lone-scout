package me.matho.lonescout.entity;

import me.matho.lonescout.client.Resources;
import me.matho.lonescout.weapon.Gun;
import org.newdawn.slick.*;

/**
 * Created by alex on 21/08/2016.
 */
public class Player extends Entity {

    private float speed = 0.15F;
    private float jumpSpeed = 0.4F;

    private Gun primary;
    private Gun secondary;

    @Override
    public void init() {

        x = 200;
        y = 600;

        w = 16;
        h = 16;

        image = Resources.getSprite("player/pluto/unarmed/still", 0, 0);

        addAnimation("unarmed/idle", new Animation(Resources.getSpriteSheet("player/pluto/unarmed/idle"), 500));
        addAnimation("unarmed/jump", new Animation(Resources.getSpriteSheet("player/pluto/unarmed/jump"), 250));
        addAnimation("unarmed/run", new Animation(Resources.getSpriteSheet("player/pluto/unarmed/run"), 50));
        setAnimation("unarmed/idle");

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

    public String gunTextureName() {

        if (primary != null) {

            return primary.getTextureName();

        } else {

            return "unarmed";

        }

    }

    @Override
    public void setAnimation(String anim) {

        super.setAnimation(gunTextureName() + "/" + anim);

    }

    @Override
    public void updateAnimation() {

        System.out.println(grounded);

        if (!grounded) {

            setAnimation("jump");

        } else {

            if (vx != 0) {

                setAnimation("run");

            } else {

                setAnimation("idle");

            }

        }

    }

    @Override
    public String getExamine() {
        return "Player";
    }
}
