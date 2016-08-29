package me.matho.lonescout.util;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by alex on 21/08/2016.
 */
public class Box {

    public Vector2f pos;
    public Vector2f size;

    public Box(int x, int y, int w, int h) {

        pos = new Vector2f(x, y);
        size = new Vector2f(w, h);

    }

    public Box(Vector2f pos, Vector2f size) {

        this.pos = pos;
        this.size = size;

    }

    public Box copy() { return new Box(pos.copy(), size.copy()); }

    public void move(float x, float y) {

        move(new Vector2f(x, y));

    }

    public void move(Vector2f vector) {

        pos.add(vector);

    }

    /*
     * Current side positions in world space
     */
    public int left()   { return (int) (pos.x); }
    public int right()  { return (int) (pos.x + size.x - 1); }
    public int top()    { return (int) (pos.y); }
    public int bottom() { return (int) (pos.y + size.y - 1); }
    public int midX()   { return (int) (pos.x + size.x / 2 - 1); }
    public int midY()   { return (int) (pos.y + size.y / 2 - 1); }

    /*
     * Current point positions in world space
     */
    public Vector2f topLeft() { return new Vector2f(pos); }
    public Vector2f midLeft() { return new Vector2f(left(), midY()); }
    public Vector2f bottomLeft() { return new Vector2f(left(), bottom()); }
    public Vector2f topRight() { return new Vector2f(right(), top()); }
    public Vector2f midRight() { return new Vector2f(right(), midY()); }
    public Vector2f bottomRight() { return new Vector2f(right(), bottom()); }
    public Vector2f topMid() { return new Vector2f(midX(), top()); }
    public Vector2f bottomMid() { return new Vector2f(midX(), bottom()); }
    public Vector2f midMid() { return new Vector2f(midX(), midY()); }
    public Vector2f centre() { return midMid(); }


    /*
     * Current position in screen space (used to interact with cursor, GUI)
     */
    public int sLeft()   { return left() + (int) Camera.offsetX(); }
    public int sRight()  { return right() + (int) Camera.offsetX(); }
    public int sTop()    { return top() + (int) Camera.offsetY(); }
    public int sBottom() { return bottom() + (int) Camera.offsetY(); }
    public int sMidX()   { return midX() + (int) Camera.offsetX(); }
    public int sMidY()   { return midY() + (int) Camera.offsetY(); }

    public boolean colliding(Box other) {

        return other.right() >= this.left()
            && other.bottom() >= this.top()
            && this.right() >= other.left()
            && this.left() >= other.right();

    }

    public boolean containsPoint(int x, int y) {

        return x >= this.left()
                && x <= this.right()
                && y >= this.top()
                && y <= this.bottom();

    }

    public boolean containsScreenPoint(int x, int y) {

        return x >= this.sLeft()
                && x <= this.sRight()
                && y >= this.sTop()
                && y <= this.sBottom();

    }

}
