package de.mibbiodev.ld26;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import de.mibbiodev.ld26.input.GlobalInput;
import de.mibbiodev.ld26.screen.SplashScreen;

import java.lang.reflect.Field;

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

    @Override
    public void create() {
        // TODO use this for randomize level color scheme
        Field[] f = Color.class.getFields();
        for (Field field : f) {
            String name = field.getName();
            if (name.matches("^[A-Z]*$") && !name.equals("BLACK") && !name.equals("CLEAR")) {
                try {
                    System.out.println(field.get(field));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


        if (Gdx.app.getType() == Application.ApplicationType.Desktop){
            Gdx.app.getGraphics().setDisplayMode(
                    LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE,
                    LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE,
                    false
            );
        }

        globalInput = new GlobalInput(this);
        soundManager = new SoundManager();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data/music/track02.ogg"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.4f);
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
}
