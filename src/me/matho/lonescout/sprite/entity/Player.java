package me.matho.lonescout.sprite.entity;

import me.matho.lonescout.client.Resources;
import me.matho.lonescout.weapon.Gun;
import org.newdawn.slick.*;

/**
 * Player class
 *
 * Created by alex on 21/08/2016.
 */
public class Player extends Entity {

    private float speed = 0.1F;
    private float jumpSpeed = 0.32F;

    private Gun primary;
    private Gun secondary;

    @Override
    public void init() {

        textureBox.pos.set(200, 200);
        textureBox.size.set(16, 16);

        collider.pos.set(textureBox.pos.x + 4, textureBox.pos.y + 4);
        collider.size.set(9, 12);

        setImage(Resources.getSprite("player/pluto/unarmed/still", 0, 0));

        addAnimation("unarmed/idle", new Animation(Resources.getSpriteSheet("player/pluto/unarmed/idle"), 500));
        addAnimation("unarmed/jump", new Animation(Resources.getSpriteSheet("player/pluto/unarmed/jump"), 250));
        addAnimation("unarmed/run", new Animation(Resources.getSpriteSheet("player/pluto/unarmed/run"), 50));
        setAnimation("unarmed/idle");

    }

    @Override
    public void update(GameContainer container, int delta) {

        Input input = container.getInput();

        vel.x = 0;

        if (input.isKeyDown(Input.KEY_A)) vel.x -= speed;
        if (input.isKeyDown(Input.KEY_D)) vel.x += speed;

        if (input.isKeyDown(Input.KEY_SPACE) && canJump()) vel.y = -jumpSpeed;

        if (input.isKeyDown(Input.KEY_E)) {
            Gun g = Gun.generate(10);
            System.out.println(g.getTextureName());
            System.out.println(g.getName());
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
    public void setAnimation(String anim) { super.setAnimation(gunTextureName() + "/" + anim); }

    @Override
    public void updateAnimation() {

        if (!canJump()) {

            setAnimation("jump");

        } else {

            if (vel.x != 0) {

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
