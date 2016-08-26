package me.matho.lonescout.util;

/**
 * Created by alex on 21/08/2016.
 */
public class Box {

    protected int x, y;
    protected int w, h;
    protected float vx, vy;

    public Box() {

    }

    public Box(int x, int y, int w, int h) {

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        vx = 0;
        vy = 0;

    }

    /*
     * Current position in world space
     */
    public int left()   { return x; }
    public int right()  { return x + w; }
    public int top()    { return y; }
    public int bottom() { return y + h; }
    public int midX()   { return x + w / 2; }
    public int midY()   { return y + h / 2; }

    /*
     * Current position in screen space (used to interact with cursor, GUI)
     */
    public int sLeft()   { return left() + (int) Camera.offsetX(); }
    public int sRight()  { return right() + (int) Camera.offsetX(); }
    public int sTop()    { return top() + (int) Camera.offsetY(); }
    public int sBottom() { return bottom() + (int) Camera.offsetY(); }
    public int sMidX()   { return midX() + (int) Camera.offsetX(); }
    public int sMidY()   { return midY() + (int) Camera.offsetY(); }

    /*
     * Predicted values for the next frame (using delta)
     */
    public float nLeft(int d)   { return left() + vx * d; }
    public float nRight(int d)  { return right() + vx * d; }
    public float nTop(int d)    { return top() + vy * d; }
    public float nBottom(int d) { return bottom() + vy * d; }
    public float nMidX(int d)   { return midX() + vx * d; }
    public float nMidY(int d)   { return midY() + vy * d; }

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
