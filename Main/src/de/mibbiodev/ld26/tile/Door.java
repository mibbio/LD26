package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author mibbio
 */
public class Door extends Tile {

    protected static final Texture LOCKED_IMAGE = new Texture(Gdx.files.internal("data/entities/door_locked.png"));
    protected static final Texture UNLOCKED_IMAGE = new Texture(Gdx.files.internal("data/entities/door_unlocked.png"));

    private Color color;

    protected Door(float x, float y, Color color) {
        super(true, x, y);
        this.color = color;
    }

    public void lock() {
        blocked = true;
    }

    public void unlock() {
        blocked = false;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Texture getTexture(Color scheme) {
        if (blocked) return LOCKED_IMAGE;
        return UNLOCKED_IMAGE;
    }

    @Override
    public void tick(float tickTime) {
        // TODO Auto-generated method stub
    }
}
