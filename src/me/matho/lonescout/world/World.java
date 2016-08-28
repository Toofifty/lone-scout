package me.matho.lonescout.world;

import me.matho.lonescout.client.Resources;
import me.matho.lonescout.client.Window;
import me.matho.lonescout.util.Camera;
import org.newdawn.slick.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 21/08/2016.
 */
public class World {

    public static MapData[] maps;

    private static final Pattern DEF_PATTERN = Pattern.compile("^!def (\\S): ([&a-z_-]+)\\((\\d+), ?(\\d+)\\)$");
    private static final Pattern TYPE_PATTERN = Pattern.compile("^type: ([a-z]+)");

    private static final int LAYERS = 3;

    private static String conChars = "";

    public static void render(float xRender, float yRender) {

        for (MapData map : maps) {

            map.render(xRender, yRender);

        }

    }

    public static void load(String mapName) {

        maps = new MapData[LAYERS];

        try {

            List<String> mapInfo = Files.readAllLines(Paths.get("res/maps/" + mapName + "_info.txt"));

            Map<String, Image> tileset = new HashMap<>();

            // populate the tileset information
            Matcher match;
            for (String line : mapInfo) {

                // ignore commented lines
                if (line.startsWith("#")) continue;

                // define keyword
                // defines a map char to a tile coordinate on the sprite sheet,
                // and determines whether to collide or not
                match = DEF_PATTERN.matcher(line);

                if (match.find()) {

                    final String tileChar = match.group(1);
                    String sheetName = match.group(2);
                    final int sheetX = Integer.parseInt(match.group(3));
                    final int sheetY = Integer.parseInt(match.group(4));

                    sheetName = sheetName.replace("&", "");

                    tileset.put(tileChar, Resources.getSprite("tile/" + sheetName, sheetX, sheetY));

                }

            }

            List<String> textMap;
            Image[][] map;
            String line;
            for (int i = 0; i < LAYERS; i++) {

                textMap = Files.readAllLines(Paths.get("res/maps/" + mapName + "_map_layer" + i + ".txt"));

                map = new Image[textMap.get(0).length()][textMap.size()];

                for (int y = 0; y < textMap.size(); y++) {

                    line = textMap.get(y);

                    for (int x = 0; x < line.length(); x++) {

                        char c = line.charAt(x);

                        if (c != ' ') {

                            map[x][y] = tileset.get(Character.toString(c));

                        }

                    }

                }

                maps[i] = new MapData(i == 1, map);

            }

        } catch (IOException e) {

            System.err.println("Failed to load map info for: " + mapName);
            e.printStackTrace();

        }

    }

    public static void loadConnected(String mapName) {

        maps = new MapData[LAYERS];

        try {
            List<String> mapInfo = Files.readAllLines(Paths.get("res/maps/" + mapName + "_info.txt"));

            Map<String, Image> tileset = new HashMap<>();

            // populate the tileset information
            Matcher match;
            for (String line : mapInfo) {

                // ignore commented lines
                if (line.startsWith("#")) continue;

                // define keyword
                // defines a map char to a tile coordinate on the sprite sheet,
                // and determines whether to collide or not
                match = DEF_PATTERN.matcher(line);

                if (match.find()) {

                    final String tileChar = match.group(1);
                    String sheetName = match.group(2);
                    final int sheetX = Integer.parseInt(match.group(3));
                    final int sheetY = Integer.parseInt(match.group(4));

                    if (sheetName.contains("&")) {

                        sheetName = sheetName.replace("&", "");

                        for (int i = 0; i < 16; i++) {

                            tileset.put(tileChar + i, Resources.getSprite("tile/" + sheetName, sheetX + i, sheetY));
                            conChars = conChars + tileChar;

                        }

                    } else {

                        tileset.put(tileChar, Resources.getSprite("tile/" + sheetName, sheetX, sheetY));

                    }

                }

            }

            List<String> textMap;
            Image[][] map;
            String line;
            for (int i = 0; i < LAYERS; i++) {

                textMap = Files.readAllLines(Paths.get("res/maps/" + mapName + "_map_layer" + i + ".txt"));

                map = new Image[textMap.get(0).length()][textMap.size()];

                for (int y = 0; y < textMap.size(); y++) {

                    line = textMap.get(y);

                    for (int x = 0; x < line.length(); x++) {

                        char c = line.charAt(x);

                        if (c != ' ') {

                            if (conChars.contains("" + c)) {

                                final boolean top = y > 0 && textMap.get(y - 1).charAt(x) != ' ';
                                final boolean right = x < line.length() && line.charAt(x + 1) != ' ';
                                final boolean bottom = y < textMap.size() && textMap.get(y + 1).charAt(x) != ' ';
                                final boolean left = x > 0 && line.charAt(x - 1) != ' ';

                                map[x][y] = tileset.get(c + "" + conValue(top, right, bottom, left));

                            } else {

                                map[x][y] = tileset.get(Character.toString(c));

                            }

                        }

                    }

                }

                maps[i] = new MapData(i == 1, map);

            }


        } catch (IOException e) {

            System.err.println("Failed to load map info for: " + mapName);
            e.printStackTrace();

        }

    }

    private static int conValue(boolean top, boolean right, boolean bottom, boolean left) {

        int val = 0;

        if (top) val += 1;
        if (right) val += 2;
        if (bottom) val += 4;
        if (left) val += 8;

        return val;

    }

    public static boolean inBounds(int x, int y) {

        return maps[1].inBounds(x, y);

    }

    public static boolean solid(int x, int y) {

        return maps[1].solid(x, y);

    }

    public static boolean colliding(float x, float y) {

        int xTile = (int) x / Tile.SIZE;
        int yTile = (int) y / Tile.SIZE;

        return solid(xTile, yTile) || !inBounds(xTile, yTile);

    }

    public static int getWidth() {

        return maps[1].getWidth() * Tile.SIZE;

    }

    public static int getHeight() {

        return maps[1].getHeight() * Tile.SIZE;

    }

}

class MapData {

    private boolean clip;
    private Image[][] map;

    private int width;
    private int height;

    public MapData(boolean clip, Image[][] map) {

        this.clip = clip;
        this.map = map;

        width = map.length;
        height = map[0].length;

    }

    public int getWidth() {

        return width;

    }

    public int getHeight() {

        return height;

    }

    public boolean isClip() {

        return clip;

    }

    public Image[][] getMap() {

        return map;

    }

    public void render(float xRender, float yRender) {

        final int offset = 2;
        final int xStart = (int) (xRender / Tile.SIZE) - offset;
        final int yStart = (int) (yRender / Tile.SIZE) - offset;
        final int xEnd = (Window.WIDTH / Tile.SIZE / Camera.getScale()) + xStart + offset * 2;
        final int yEnd = (Window.HEIGHT / Tile.SIZE / Camera.getScale()) + yStart + offset * 2;

        for (int x = xStart; x < xEnd; x++) {

            for (int y = yStart; y < yEnd; y++) {

                if (solid(x, y)) {

                    map[x][y].draw(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);

                }

            }

        }

    }

    public boolean inBounds(int x, int y) {

        return x >= 0 && y >= 0 && x < width && y < height;

    }

    public boolean solid(int x, int y) {

        return inBounds(x, y) && map[x][y] != null;

    }

}