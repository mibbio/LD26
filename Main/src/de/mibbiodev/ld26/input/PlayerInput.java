package de.mibbiodev.ld26.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.entity.Orientation;
import de.mibbiodev.ld26.entity.Player;

/**
 * @author mibbio
 */
public class PlayerInput implements InputProcessor {

    private static final int KEY_UP = Input.Keys.W;
    private static final int KEY_DOWN = Input.Keys.S;
    private static final int KEY_LEFT = Input.Keys.A;
    private static final int KEY_RIHT = Input.Keys.D;

    private Player player;
    private int currentMovement = -1;

    public PlayerInput(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        //if (currentMovement > -1) return false;
        if (keycode != currentMovement) player.setVelocity(Vector2.Zero.cpy());
        currentMovement = keycode;
        switch (keycode) {
            case KEY_DOWN:
                player.setOrientation(Orientation.SOUTH);
                player.getVelocity().y = -1;
                return true;
            case KEY_LEFT:
                player.setOrientation(Orientation.WEST);
                player.getVelocity().x = -1;
                return true;
            case KEY_RIHT:
                player.setOrientation(Orientation.EAST);
                player.getVelocity().x = 1;
                return true;
            case KEY_UP:
                player.setOrientation(Orientation.NORTH);
                player.getVelocity().y = 1;
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode != currentMovement) return false;
        currentMovement = -1;
        switch (keycode) {
            case KEY_DOWN:
            case KEY_LEFT:
            case KEY_RIHT:
            case KEY_UP:
                player.setVelocity(Vector2.Zero.cpy());
                return true;
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
