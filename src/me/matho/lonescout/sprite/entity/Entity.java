package me.matho.lonescout.sprite.entity;

import me.matho.lonescout.gui.Examinable;
import me.matho.lonescout.sprite.Sprite;
import me.matho.lonescout.util.Box;
import me.matho.lonescout.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 21/08/2016.
 */
public abstract class Entity extends Sprite implements Examinable {

    private static final float GRAVITY_ACCELERATION = 0.025F;

    private Image image;
    private Color color;

    private Map<String, Animation> anims;
    private String currentAnim;

    /**
     * Set to true to prevent the sprite flipping on the x axis when the velocity
     * is toward the left (-x)
     */
    private boolean noFlip = false;
    private boolean isFlipped = false;

    /**
     * Determines how the entity clips
     * 0: Sprite does not clip with anything (particles)
     * 1: Sprite only clips with world tiles and projectiles
     * 2: Full block
     */
    private int clip = 1;

    /* Physics properties */
    private int jumps = 0;
    private int maxJumps = 1;

    public Entity() {

        super(0, 0, 0, 0);

        anims = new HashMap<>();
        init();

    }

    public abstract void init();

    @Override
    public void render(GameContainer container, Graphics g) {

        if (!isVisible()) return;

        Animation anim = anims.get(currentAnim);
        if (anim != null) {

            if (noFlip || (vel.x >= 0 && !isFlipped) || vel.x > 0) {

                isFlipped = false;
                anim.draw(textureBox.left(), textureBox.top(), textureBox.size.x, textureBox.size.y, color);

            } else {

                isFlipped = true;
                anim.draw(textureBox.right() + 1, textureBox.top(), -textureBox.size.x, textureBox.size.y, color);

            }

        } else if (image != null) {

            if (noFlip || vel.x >= 0)
                image.draw(textureBox.pos.x, textureBox.pos.y, textureBox.size.x, textureBox.size.y, color);
            else
                image.draw(textureBox.pos.x + textureBox.size.x, textureBox.pos.y, -textureBox.size.x,
                        textureBox.size.y, color);

        }

    }

    public void update(GameContainer container, int delta) {

        if (jumps >= maxJumps) {

            vel.y += GRAVITY_ACCELERATION;

            // terminal velocity
            if (vel.y > 60) vel.y = 60;

        }

        // make sure walking off an edge counts as one jump
        if (vel.y != 0) {

            if (jumps == 0) jumps = 1;

        }

        // apply x velocity to check next frame for collisions
        collider.move(vel.x * delta, 0);
        textureBox.move(vel.x * delta, 0);

        // test the three points on the left edge of the entity
        // if any collide with the map, the sprite is clipping
        // on this side
        while (World.testEdge(collider.topLeft(), collider.midLeft(), collider.bottomLeft())) {
            // move the entity to the right once, and check if
            // it is still clipping to the left
            collider.move(1, 0);
            textureBox.move(1, 0);

            // definitely hit something, vx should be zero
            vel.x = 0;

            // continue until no longer clipping
        }

        // same method for the right edge of the entity
        while (World.testEdge(collider.topRight(), collider.midRight(), collider.bottomRight())) {
            collider.move(-1, 0);
            textureBox.move(-1, 0);
            vel.x = 0;
        }

        // use same method to test for y clipping
        collider.move(0, vel.y * delta);
        textureBox.move(0, vel.y * delta);

        // check bottom of entity
        while (World.testEdge(collider.bottomLeft(), collider.bottomMid(), collider.bottomRight())) {
            collider.move(0, -1);
            textureBox.move(0, -1);
            vel.y = 0;
            // if it has collided (even if it still needs to move up)
            // we know it's hit a floor so we can reset the jump
            jumps = 0;
        }

        // check top edge of entity
        while (World.testEdge(collider.topLeft(), collider.topMid(), collider.topRight())) {
            collider.move(0, 1);
            textureBox.move(0, 1);
            vel.y = 0;
        }

        // check if the entity is still on the floor, or should
        // start falling (from walking off of an edge)

        // test the rect one pixel lower, to see if it is colliding
        // if it isn't the entity is falling
        final Box floorTest = collider.copy();
        floorTest.move(0, 1);
        if (!World.testEdge(floorTest.bottomLeft(), floorTest.bottomMid(), floorTest.bottomRight())) {
            if (jumps == 0) jumps = 1;
        }

        updateAnimation();

    }

    public abstract void updateAnimation();

    protected void setImage(Image img) { image = img;  }

    public boolean canJump() { return jumps < maxJumps; }

//    public boolean hitLeft(int d) { return World.colliding(nLeft(d), nMidY(d)); }
//    public boolean hitRight(int d) { return World.colliding(nRight(d), nMidY(d)); }
//    public boolean hitTop(int d) { return World.colliding(nMidX(d), nTop(d)); }
//    public boolean hitBottom(int d) { return World.colliding(nMidX(d), nBottom(d)); }

    public void setAnimation(String anim) { this.currentAnim = anim; }

    public String getAnimation() { return this.currentAnim; }

    public void addAnimation(String name, Animation anim) { anims.put(name, anim); }

}
