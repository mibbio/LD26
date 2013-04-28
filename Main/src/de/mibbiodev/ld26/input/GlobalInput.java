package de.mibbiodev.ld26.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class GlobalInput implements InputProcessor {
    private static final int KEY_BACK = Input.Keys.ESCAPE;

    LD26Game game;

    public GlobalInput(LD26Game game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == KEY_BACK) {
            game.handleAbort();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
