package me.matho.lonescout.sprite;

import me.matho.lonescout.util.Box;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Alex on 28/08/2016.
 */
public class Sprite {

    public Box collider;
    public Box textureBox;
    protected Vector2f vel;

    /**
     * Toggle drawing of this sprite
     */
    private boolean visible = true;

    public Sprite(int x, int y, int w, int h) {

        collider = new Box(x, y, w, h);
        textureBox = new Box(x, y, w, h);
        vel = new Vector2f(0, 0);

    }

    public void render(GameContainer container, Graphics g) {

        if (!visible) return;

    }

    public boolean isVisible() { return visible; }

    public void setVisible(boolean vis) { visible = vis; }

}
