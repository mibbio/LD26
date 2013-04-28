package de.mibbiodev.ld26;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import de.mibbiodev.ld26.input.AppInput;
import de.mibbiodev.ld26.screen.*;

/**
 * @author mibbio
 */
public class LD26Game extends Game {

    public static final String TITLE = "LD26Game";
    public static final byte ROOM_SIZE = 16;
    public static final byte TILE_SIZE = 32;
    public static final float BASE_TICK_TIME = 1/20f;

    private AppInput appInput;

    @Override
    public void create() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop){
            Gdx.app.getGraphics().setDisplayMode(
                    LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE,
                    LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE,
                    false
            );
        }

        appInput = new AppInput(this);

        //RoomScreen entryRoom = new RoomScreen(this, Color.GREEN, "map01");
        //setScreen(entryRoom);
        SplashScreen splash = new SplashScreen(this);
        setScreen(splash);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public AppInput getAppInput() {
        return appInput;
    }

    public void handleAbort() {
        if(getScreen() instanceof GameScreen) {
            Gdx.app.exit();
        } else if (getScreen() instanceof SplashScreen) {
            setScreen(new RoomScreen(this, Color.GREEN, "map01"));
        }
    }

}
