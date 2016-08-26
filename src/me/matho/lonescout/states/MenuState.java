package me.matho.lonescout.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by alex on 21/08/2016.
 */
public class MenuState extends BasicGameState {

    @Override
    public int getID() {

        return States.MENU;

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {



    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.drawString("Menu state!", 50, 50);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        if (container.getInput().isKeyPressed(Input.KEY_ENTER)) game.enterState(States.GAME);

    }
}
