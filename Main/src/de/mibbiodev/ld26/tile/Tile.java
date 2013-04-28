package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.Tickable;

import java.util.Random;

/**
 * @author mibbio
 */
public abstract class Tile implements Tickable, Disposable {

    protected static final float DARKEST_SHADE = 0.5f;
    protected static final float BORDER_SHADE = 0.8f;

    protected boolean blocked = false;
    protected float shade;
    protected Pixmap pixelMap;
    protected Texture tileTexture;
    protected Rectangle bounds;

    protected Random random;

    protected Tile(boolean blocked, float x, float y) {
        this.blocked = blocked;

        random = new Random();
        shade = random.nextFloat();
        clampShade();

        pixelMap = new Pixmap(LD26Game.TILE_SIZE, LD26Game.TILE_SIZE, Pixmap.Format.RGBA8888);
        tileTexture = new Texture(LD26Game.TILE_SIZE, LD26Game.TILE_SIZE, Pixmap.Format.RGBA8888);

        bounds = new Rectangle(x * LD26Game.TILE_SIZE, y * LD26Game.TILE_SIZE, LD26Game.TILE_SIZE, LD26Game.TILE_SIZE);
    }

    public boolean isWalkable() {
        return !blocked;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    protected void clampShade() {
        if (blocked) {
            if (shade < BORDER_SHADE + 0.05f) shade = BORDER_SHADE + 0.05f;
        } else {
            if (shade < DARKEST_SHADE) shade = DARKEST_SHADE;
            if (shade > BORDER_SHADE - 0.2f) shade = BORDER_SHADE - 0.2f;
        }
    }

    @Override
    public void dispose() {
        tileTexture.dispose();
        pixelMap.dispose();
    }

    public abstract Texture getTexture(Color scheme);
}
