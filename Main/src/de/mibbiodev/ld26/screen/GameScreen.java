package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public abstract class GameScreen implements Screen {

    protected SpriteBatch batch;
    protected Camera camera;

    public GameScreen() {
        this.batch = new SpriteBatch();
    }

    public void renderStart() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
    }

    public void renderEnd() {
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(
                LD26Game.ROOM_SIZE * LD26Game.TILE_SIZE,
                LD26Game.ROOM_SIZE * LD26Game.TILE_SIZE
        );
    }

    public void dispose() {
        batch.dispose();
    }
}
