package de.mibbiodev.ld26;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import de.mibbiodev.ld26.input.GlobalInput;
import de.mibbiodev.ld26.screen.*;

/**
 * @author mibbio
 */
public class LD26Game extends Game {

    public static final String TITLE = "energized";
    public static final byte ROOM_SIZE = 16;
    public static final byte TILE_SIZE = 32;
    public static final float BASE_TICK_TIME = 1/20f;

    public String map = "map01";

    private GlobalInput globalInput;
    private SoundManager soundManager;

    private Music backgroundMusic;

    // screens

    @Override
    public void create() {

        System.out.println(Color.DARK_GRAY);

        // just filesystem tests
        FileHandle[] test = Gdx.files.internal("out").list();

        for (FileHandle handle : test) {
            System.out.println(handle.name());
        }
        // end fs test


        if (Gdx.app.getType() == Application.ApplicationType.Desktop){
            Gdx.app.getGraphics().setDisplayMode(
                    LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE,
                    LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE,
                    false
            );
        }

        globalInput = new GlobalInput(this);
        soundManager = new SoundManager();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data/music/track01.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.3f);
        backgroundMusic.play();

        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        backgroundMusic.stop();
        soundManager.dispose();
        backgroundMusic.dispose();
        super.dispose();
    }

    public GlobalInput getGlobalInput() {
        return globalInput;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void handleAbort(String reason) {
        if(getScreen() instanceof GameScreen) {
            System.out.println("handleAbort 1 " + reason);
            setScreen(new MainMenuScreen(this));
            System.out.println("handleAbort 2");
        } else if (getScreen() instanceof SplashScreen) {
            setScreen(new MainMenuScreen(this));
        } else if (getScreen() instanceof MainMenuScreen) {
            if (reason.equals("exit")) Gdx.app.exit();
            if (reason.equals("start")) setScreen(new RoomScreen(this, Color.GREEN, map));
        }
    }

}
