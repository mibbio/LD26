package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.*;
import de.mibbiodev.ld26.LD26Game;

import java.util.Random;

/**
 * @author mibbio
 */
public abstract class Tile {

    protected boolean blocked = false;
    protected float shade;
    protected Pixmap pixmap;
    protected Texture texture;

    protected Tile(boolean blocked) {
        this.blocked = blocked;

        Random random = new Random();
        shade = Math.max(random.nextFloat(), 0.4f);

        pixmap = new Pixmap(LD26Game.TILE_SIZE, LD26Game.TILE_SIZE, Pixmap.Format.RGBA8888);
        texture = new Texture(LD26Game.TILE_SIZE, LD26Game.TILE_SIZE, Pixmap.Format.RGBA8888);
    }

    public boolean isWalkable() {
        return !blocked;
    }

    public abstract void tick();

    public abstract Texture getTexture(Color scheme);

    public void dispose() {
        texture.dispose();
        pixmap.dispose();
    }
}
