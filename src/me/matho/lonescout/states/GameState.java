package me.matho.lonescout.states;

import me.matho.lonescout.client.Resources;
import me.matho.lonescout.sprite.entity.Entity;
import me.matho.lonescout.sprite.entity.Player;
import me.matho.lonescout.gui.Overlay;
import me.matho.lonescout.util.Camera;
import me.matho.lonescout.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by alex on 21/08/2016.
 */
public class GameState extends BasicGameState {

    private ArrayList<Entity> entities;

    private Camera camera;
    private Overlay overlay;

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    @Override
    public int getID() {

        return States.GAME;

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        entities = new ArrayList<>();

        final Player player = new Player();

        camera = new Camera(player);
        overlay = new Overlay(this);
        entities.add(player);

        container.setMouseCursor(Resources.getSprite("gui/cursor", 0, 0).getScaledCopy(Camera.getScale()),0, 0);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.scale(camera.getScale(), camera.getScale());
        g.translate(camera.offsetX(), camera.offsetY());
        {
            World.render(-camera.offsetX(), -camera.offsetY());

            for (int i = 0; i < entities.size(); i++) {

                entities.get(i).render(container, g);

            }

        }
        g.translate(-camera.offsetX(), -camera.offsetY());

        overlay.render(container, g);

        //GUI.titleFont.drawString(Window.WIDTH / (2 * camera.getScale()), Window.HEIGHT / (2 * camera.getScale()), "I like DOGS");

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        camera.update(delta);

        for (int i = 0; i < entities.size(); i++) {

            entities.get(i).update(container, delta);

        }

        overlay.update(container, delta);

        if (container.getInput().isKeyPressed(Input.KEY_ENTER)) game.enterState(States.MENU);

    }
}
