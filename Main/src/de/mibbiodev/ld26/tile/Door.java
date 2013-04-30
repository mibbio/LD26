package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author mibbio
 */
public class Door extends Tile {
    public static Texture LOCKED_TEXTURE;
    public static Texture UNLOCKED_TEXTURE;

    private Color color;

    public Door(float x, float y, Color color, String srcImage) {
        super(true, x, y, srcImage);
        this.color = color;
        texture.dispose();
        if (LOCKED_TEXTURE == null) {
            LOCKED_TEXTURE = new Texture(Gdx.files.internal("data/entities/door_locked.png"));
        }
        if (UNLOCKED_TEXTURE == null) {
            UNLOCKED_TEXTURE = new Texture(Gdx.files.internal("data/entities/door_unlocked.png"));
        }
        lock();
    }

    public void lock() {
        blocked = true;
    }

    public void unlock() {
        blocked = false;
    }

    @Override
    public Sprite getSprite(Color tint) {
        if (blocked) sprite.setTexture(LOCKED_TEXTURE);
        else sprite.setTexture(UNLOCKED_TEXTURE);
        sprite.setColor(color.cpy());
        return sprite;
    }

    @Override
    public void tick(float tickTime) {}
}
