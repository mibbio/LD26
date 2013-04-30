package de.mibbiodev.ld26;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import de.mibbiodev.ld26.input.GlobalInput;
import de.mibbiodev.ld26.screen.RoomScreen;
import de.mibbiodev.ld26.screen.SplashScreen;

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

    public void runMap(String mapName) {
        this.map = mapName;
        setScreen(new RoomScreen(this, Color.ORANGE, true));
    }

    /*
    public void changeScreen(String reason) {
        if(getScreen() instanceof GameScreen) {
            if (reason.equals("back")) setScreen(new MainMenuScreen(this));
            else setScreen(new EndScreen(this, reason));

        } else if (getScreen() instanceof SplashScreen) {
            setScreen(new MainMenuScreen(this));

        } else if (getScreen() instanceof MainMenuScreen) {
            if (reason.equals("mapselect")) setScreen(new MapSelectScreen(this));
            if (reason.equals("exit")) Gdx.app.exit();
            if (reason.equals("start")) setScreen(new RoomScreen(this, Color.GREEN, map, false));

        } else if (getScreen() instanceof EndScreen) {
            setScreen(new MainMenuScreen(this));

        } else if (getScreen() instanceof MapSelectScreen) {
            if (reason.equals("back")) setScreen(new MainMenuScreen(this));
            else {
                setScreen(new RoomScreen(this, Color.GREEN, map, true));
            }
        }
    }*/

}
