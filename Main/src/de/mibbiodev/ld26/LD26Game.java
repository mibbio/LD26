package de.mibbiodev.ld26;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import de.mibbiodev.ld26.screen.RoomScreen;

/**
 * @author mibbio
 */
public class LD26Game extends Game {

    public static final String TITLE = "LD26Game";
    public static final byte ROOM_SIZE = 16;
    public static final byte TILE_SIZE = 32;
    public static final float BASE_TICK_TIME = 1/20f;

    private boolean abort = false;

    @Override
    public void create() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop){
            Gdx.app.getGraphics().setDisplayMode(
                    Gdx.app.getGraphics().getWidth(),
                    TILE_SIZE * ROOM_SIZE,
                    false
            );
        }
        RoomScreen entryRoom = new RoomScreen(this, Color.GREEN, "map01");
        setScreen(entryRoom);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void setAbort(boolean value) {
        abort = value;
    }

    public boolean triesAbort() {
        return abort;
    }
}
