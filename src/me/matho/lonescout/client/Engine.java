package me.matho.lonescout.client;

import me.matho.lonescout.gui.GUI;
import me.matho.lonescout.states.*;
import me.matho.lonescout.world.World;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by alex on 21/08/2016.
 */
public class Engine extends StateBasedGame {

    public static int UPDATE_INTERVAL = 1000 / 60;
    public static int FRAME_RATE = 60;
    public static boolean VSYNC = true;
    public static boolean DISPLAY_FPS = true;

    public static void main(String[] args) {

        try {

            AppGameContainer app = new AppGameContainer(new Engine());
            app.setDisplayMode(Window.WIDTH, Window.HEIGHT, Window.FULLSCREEN);
            app.start();

        } catch (SlickException e) {

            e.printStackTrace();

        }

    }

    public Engine() {

        super("Prototype 1.0");

    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {

        container.setMaximumLogicUpdateInterval(UPDATE_INTERVAL);
        container.setMinimumLogicUpdateInterval(UPDATE_INTERVAL);
        container.setTargetFrameRate(FRAME_RATE);
        container.setAlwaysRender(true);
        container.setShowFPS(DISPLAY_FPS);
        container.setVSync(VSYNC);

        Resources.init();
        GUI.init();

        World.loadConnected("2");

        this.addState(new GameState());
        this.addState(new MenuState());

    }
}
