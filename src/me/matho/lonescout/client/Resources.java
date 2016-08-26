package me.matho.lonescout.client;

import me.matho.lonescout.world.Tile;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 21/08/2016.
 */
public class Resources {

    public static final String RES_DIR = "res/";
    public static final String PLAYER_DIR = "player/";

    private static Map<String, Image> images;
    private static Map<String, SpriteSheet> sprites;
    private static Map<String, Sound> sounds;

    public static void init() {

        images = new HashMap<>();
        sprites = new HashMap<>();
        sounds = new HashMap<>();

        try {

            images.put("null", loadImage("null.png"));

        } catch (SlickException e) {

            e.printStackTrace();

        }

    }

    /**
     * Load an image from RES_DIR + path
     *
     * @param path file path
     * @return Image object
     * @throws SlickException
     */
    private static Image loadImage(String path) throws SlickException {

        return new Image(RES_DIR + path, false, Image.FILTER_NEAREST);

    }

    /**
     * Load a spritesheet from RES_DIR + path, with tiles of the given size
     *
     * @param path file path
     * @param tw tile width
     * @param th tile height
     * @return Image object
     * @throws SlickException
     */
    private static SpriteSheet loadSprite(String path, int tw, int th) throws SlickException {

        return new SpriteSheet(loadImage(path), tw, th);

    }

    /**
     * Get an image from the key.
     *
     * If the image has not yet been loaded, will try to load it using the given key as a path, and save the key to the
     * hash map.
     * If no image found at the path, the key will be saved to the hash map pointing to a null texture.
     *
     * @param key hash map key / file path
     * @return Image object
     */
    public static Image getImage(String key) {

        if (!images.containsKey(key)) {

            try {

                images.put(key, loadImage(key + ".png"));

            } catch (SlickException e) {

                System.err.println("Could not load image: " + RES_DIR + key + ".png");
                e.printStackTrace();

                images.put(key, images.get("null"));

            }

        }

        return images.get(key);

    }

    /**
     * Get a SpriteSheet from the key.
     *
     * Use when needed tile size is equal to Tile.SIZE
     *
     * If the SpriteSheet has not yet been loaded, will try to load it using the given key as a path (with cell/tile
     * sizes as Tile.SIZE) and save the key to the hash map.
     * If no image found at the path, a fatal error will propagate.
     *
     * @param key hash map key / file path
     * @return SpriteSheet object
     */
    public static SpriteSheet getSpriteSheet(String key) {

        if (!sprites.containsKey(key)) {

            return getSpriteSheet(key, Tile.SIZE, Tile.SIZE);

        }

        return sprites.get(key);

    }

    /**
     * Get a SpriteSheet from the key.
     *
     * Use when needed tile size is NOT equal to Tile.SIZE
     *
     * If the SpriteSheet has not yet been loaded, will try to load it using the given key as a path (with cell/tile
     * sizes as tw, th) and save the key to the hash map.
     * If no image found at the path, a fatal error will propagate.
     *
     * @param key hash map key / file path
     * @param tw tile width
     * @param th tile height
     * @return SpriteSheet object
     */
    public static SpriteSheet getSpriteSheet(String key, int tw, int th) {

        if (!sprites.containsKey(key)) {

            try {

                sprites.put(key, loadSprite(key + ".png", tw, th));

            } catch (SlickException e) {

                System.err.println("Could not find spritesheet: " + RES_DIR + key + ".png");
                e.printStackTrace();

            }

        }

        return sprites.get(key);

    }

    /**
     * Get a sprite from the SpriteSheet found with key, at the position (x, y)
     *
     * If SpriteSheet not found, will try to create it using getSpriteSheet with the default Tile.SIZE
     *
     * @param key hash map key / file path
     * @param x x coordinate of image
     * @param y y coordinate of image
     * @return Image object
     */
    public static Image getSprite(String key, int x, int y) {

        if (!sprites.containsKey(key)) {

            return getSprite(key, x, y, Tile.SIZE, Tile.SIZE);

        }

        return sprites.get(key).getSprite(x, y);

    }

    /**
     * Get a sprite from the SpriteSheet found with key, at the position (x, y)
     *
     * If SpriteSheet not found, will try to create it using getSpriteSheet with the dimensions tw x th
     *
     * @param key hash map key / file path
     * @param x x coordinate of image
     * @param y y coordinate of image
     * @param tw tile width
     * @param th tile height
     * @return Image object
     */
    public static Image getSprite(String key, int x, int y, int tw, int th) {

        if (!sprites.containsKey(key)) {

            getSpriteSheet(key, tw, th);

        }

        return sprites.get(key).getSprite(x, y);

    }

}
