package me.matho.lonescout.gui;

import me.matho.lonescout.entity.Entity;
import me.matho.lonescout.states.GameState;
import me.matho.lonescout.util.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Created by alex on 25/08/2016.
 */
public class Overlay {

    private GameState game;

    private int x, y;
    private String text = "";

    public Overlay(GameState game) {
        this.game = game;
    }

    public void render(GameContainer container, Graphics g) {

        if (text.length() != 0) GUI.regFont.drawString(x, y, text);

    }

    public void update(GameContainer container, int delta) {

        final Input input = container.getInput();

        final int x = input.getMouseX() / Camera.getScale();
        final int y = input.getMouseY() / Camera.getScale();

        boolean found = false;

        for (Entity entity : game.getEntities()) {

            if (entity.containsScreenPoint(x, y)) {

                this.x = x + 8;
                this.y = y + 8;
                this.text = entity.getExamine();
                found = true;

            }

        }

        if (!found) text = "";

    }

}
