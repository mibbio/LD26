package de.mibbiodev.ld26.screen;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.tween.SpriteTween;

/**
 * @author mibbio
 */
public class SplashScreen implements Screen {

    private LD26Game game;
    private Texture splashTexture;
    private Sprite splashSprite;
    private SpriteBatch batch;

    private TweenManager tweenManager;

    public SplashScreen(LD26Game game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        float dx = (splashTexture.getWidth() - Gdx.graphics.getWidth()) / 2f;
        float dy = (splashTexture.getHeight() - Gdx.graphics.getHeight()) / 2f;
        splashSprite.setPosition(dx, dy);

        batch.begin();
        splashSprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        splashTexture = new Texture(Gdx.files.internal("data/ui/splash.png"));
        splashSprite = new Sprite(splashTexture);
        splashSprite.setColor(1, 1, 1, 0);
        batch = new SpriteBatch();

        Tween.registerAccessor(Sprite.class, new SpriteTween());
        tweenManager = new TweenManager();
        Tween.to(splashSprite, SpriteTween.ALPHA, 3f).target(1).ease(TweenEquations.easeInQuad).start(tweenManager);

        Gdx.input.setInputProcessor(game.getGlobalInput());
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
