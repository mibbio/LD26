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

    private GlobalInput globalInput;
    private SoundManager soundManager;

    private Music backgroundMusic;

    @Override
    public void create() {
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

        SplashScreen splash = new SplashScreen(this);
        setScreen(splash);
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

    public void handleAbort() {
        if(getScreen() instanceof GameScreen) {
            Gdx.app.exit();
        } else if (getScreen() instanceof SplashScreen) {
            setScreen(new RoomScreen(this, Color.GREEN, "map01"));
        }
    }

}
