package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public abstract class GameScreen implements Screen {

    protected LD26Game game;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected boolean isPaused = false;

    public GameScreen(LD26Game game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    public void renderStart() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    public void renderEnd() {
        batch.end();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float size = LD26Game.ROOM_SIZE * LD26Game.TILE_SIZE;
        camera.setToOrtho(false, size * aspectRatio, size);
    }

    @Override
    public final void pause() {
        isPaused = true;
    }

    @Override
    public final void resume() {
        isPaused = false;
    }

    public void dispose() {
        batch.dispose();
    }

    public abstract void tick(float tickTime);
}
