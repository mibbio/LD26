package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    protected Texture texture;
    protected Sprite sprite;
    protected Rectangle bounds;

    protected Random random;

    protected Tile(boolean blocked, float x, float y, String srcImage) {
        if (srcImage == null) srcImage = "data/tile.png";
        this.blocked = blocked;

        // loading image
        texture = new Texture(Gdx.files.internal(srcImage));
        sprite = new Sprite(texture);
        sprite.setPosition(x * LD26Game.TILE_SIZE, y * LD26Game.TILE_SIZE);

        // shading
        random = new Random();
        shade = random.nextFloat();
        clampShade();

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
        texture.dispose();
    }

    public abstract Sprite getSprite(Color tint);
}
