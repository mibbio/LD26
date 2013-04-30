package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class EndScreen implements Screen {

    private LD26Game game;

    private SpriteBatch batch;
    private BitmapFont font;

    private CharSequence text;
    private float duration = 0;

    public EndScreen(LD26Game game, ExitReason exitReason) {
        this.game = game;
        switch (exitReason) {
            case PLAYER_DEAD:
                text = "You ran out of energy!\n\nYour engines\nshut down\nand the last chance\nto escape is gone...";
                break;
            case MAP_COMPLETED:
                text = "congratulation\n\nThere was enough\nenergy in your\n" +
                       "batteries to activate\nthe teleporter.\n\nNow you're back\nat your mothership.";
                break;
        }
    }

    @Override
    public void render(float delta) {
        duration += delta;
        if (duration > 15f) {
            game.setScreen(new MainMenuScreen(game));
            return;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float x = Gdx.graphics.getWidth()/2 - 300/2;
        float y = Gdx.graphics.getHeight()/2 + Gdx.graphics.getHeight()/4;

        batch.begin();
        font.drawMultiLine(batch, text, x, y, 300, BitmapFont.HAlignment.CENTER);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/ui/font/whitefont.fnt"), false);
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
        batch.dispose();
        font.dispose();
    }
}
