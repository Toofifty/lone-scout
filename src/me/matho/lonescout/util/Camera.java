package me.matho.lonescout.util;

import me.matho.lonescout.client.Window;
import me.matho.lonescout.sprite.entity.Entity;
import me.matho.lonescout.world.World;

/**
 * Created by alex on 21/08/2016.
 */
public class Camera {

    private Entity target;
    private static float ox, oy;
    private static int scale = 2;

    private int cameraSmoothing = 20;

    public Camera(Entity target) {

        this.target = target;

        this.update(1000);

    }

    public void update(int delta) {

        float tx = Window.WIDTH / (3 * scale) - targetX();
        float ty = Window.HEIGHT / (2 * scale) - targetY();

        tx = Math.min(0, tx);
        tx = Math.max(Window.WIDTH / scale - World.getWidth(), tx);
        ty = Math.min(0, ty);
        ty = Math.max(Window.HEIGHT / scale - World.getHeight(), ty);

        ox = ((cameraSmoothing - 1) * ox + tx) / cameraSmoothing;
        oy = ((cameraSmoothing - 1) * oy + ty) / cameraSmoothing;

    }

    public static int offsetX() {

        return (int) ox;

    }

    public static int offsetY() {

        return (int) oy;

    }

    public static int getScale() {

        return scale;

    }

    public int targetX() {

        return target.collider.midX();

    }

    public int targetY() {

        return target.collider.midY();

    }

}
