package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class SplashScreen implements Screen {

    private LD26Game game;
    private Texture splashTexture;
    private Sprite splashSprite;
    private SpriteBatch batch;

    public SplashScreen(LD26Game game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splashSprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        splashTexture = new Texture(Gdx.files.internal("data/ui/splash_tmp.png"));
        splashSprite = new Sprite(splashTexture);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(game.getAppInput());
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        splashTexture.dispose();
        batch.dispose();
    }
}
