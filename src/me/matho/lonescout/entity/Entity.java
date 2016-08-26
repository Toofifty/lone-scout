package me.matho.lonescout.entity;

import me.matho.lonescout.gui.Examinable;
import me.matho.lonescout.util.Box;
import me.matho.lonescout.world.World;
import org.newdawn.slick.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 21/08/2016.
 */
public abstract class Entity extends Box implements Examinable {

    public static final float GRAVITY_ACCELERATION = 0.025F;

    protected Image image;
    protected Color color;

    private Map<String, Animation> anims;
    private String currentAnim;

    /**
     * Set to true to prevent the sprite flipping on the x axis when the velocity
     * is toward the left (-x)
     */
    private boolean noFlip = false;
    private boolean isFlipped = false;

    /* Physics properties */
    public boolean grounded = false;

    public Entity() {

        anims = new HashMap<>();
        init();

    }

    public abstract void init();

    public void render(GameContainer container, Graphics g) {

        Animation anim = anims.get(currentAnim);
        if (anim != null) {

            if (noFlip || (vx >= 0 && !isFlipped) || vx > 0) {

                isFlipped = false;
                anim.draw(x, y, w, h, color);

            } else {

                isFlipped = true;
                anim.draw(x + w, y, -w, h, color);

            }

        } else if (image != null) {

            if (noFlip || vx >= 0) image.draw(x, y, w, h, color);
            else image.draw(x + w, y, -w, h, color);

        }

    }

    public void update(GameContainer container, int delta) {

        if (!grounded) {

            vy += GRAVITY_ACCELERATION;

            // terminal velocity
            if (vy > 60) vy = 60;

        }

        if (vy != 0) {

            grounded = false;

            // set 'falling'/'jumping' animation

        }

        if (hitLeft(delta) && vx < 0) vx = 0;
        if (hitRight(delta) && vx > 0) vx = 0;

        if (hitTop(delta)) vy = 0;

        if (hitBottom(delta)) {

            vy = 0;
            grounded = true;

        } else {

            grounded = false;

        }

        x += vx * delta;
        y += vy * delta;

        updateAnimation();

    }

    public abstract void updateAnimation();

    public boolean hitLeft(int d) {

        return World.colliding(nLeft(d), nMidY(d));

    }

    public boolean hitRight(int d) {

        return World.colliding(nRight(d), nMidY(d));

    }

    public boolean hitTop(int d) {

        return World.colliding(nMidX(d), nTop(d));

    }

    public boolean hitBottom(int d) {

        return World.colliding(nMidX(d), nBottom(d));

    }

    public void setAnimation(String anim) {

        this.currentAnim = anim;

    }

    public String getAnimation() {

        return this.currentAnim;

    }

    public void addAnimation(String name, Animation anim) {

        anims.put(name, anim);

    }

}
