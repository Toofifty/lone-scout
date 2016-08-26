package me.matho.lonescout.gui;

import me.matho.lonescout.client.Resources;
import org.newdawn.slick.SpriteSheetFont;

/**
 * Created by alex on 23/08/2016.
 */
public class GUI {

    public static SpriteSheetFont titleFont;
    public static SpriteSheetFont regFont;

    public static void init() {

        titleFont = new SpriteSheetFont(Resources.getSpriteSheet("gui/title", 8, 12), ' ');
        regFont = new SpriteSheetFont(Resources.getSpriteSheet("gui/reg_aa", 6, 10), ' ');

    }

}
