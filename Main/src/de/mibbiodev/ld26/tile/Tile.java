package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.*;
import de.mibbiodev.ld26.LD26Game;

import java.util.Random;

/**
 * @author mibbio
 */
public abstract class Tile {

    protected static final float DARKEST_SHADE = 0.5f;
    protected static final float BORDER_SHADE = 0.8f;

    protected boolean blocked = false;
    protected float shade;
    protected Pixmap pixmap;
    protected Texture texture;

    protected Random random;

    protected Tile(boolean blocked) {
        this.blocked = blocked;

        random = new Random();
        shade = random.nextFloat();
        if (blocked) {
            if (shade < BORDER_SHADE + 0.05f) shade = BORDER_SHADE + 0.05f;
        } else {
            if (shade < DARKEST_SHADE) shade = DARKEST_SHADE;
            if (shade > BORDER_SHADE - 0.2f) shade = BORDER_SHADE - 0.2f;
        }

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
