package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;

/**
 * @author mibbio
 */
public class Door extends Tile {

    //protected static final Texture LOCKED_IMAGE = new Texture(Gdx.files.internal("data/entities/door_locked.png"));
    //protected static final Texture UNLOCKED_IMAGE = new Texture(Gdx.files.internal("data/entities/door_unlocked.png"));

    public static final Pixmap LOCKED_IMAGE = new Pixmap(Gdx.files.internal("data/entities/door_locked.png"));
    public static final Pixmap UNLOCKED_IMAGE = new Pixmap(Gdx.files.internal("data/entities/door_unlocked.png"));

    private Color color;

    public Door(float x, float y, Color color) {
        super(true, x, y);
        this.color = color;
        refreshTexture(LOCKED_IMAGE);
    }

    public void lock() {
        blocked = true;
        refreshTexture(LOCKED_IMAGE);
    }

    public void unlock() {
        blocked = false;
        refreshTexture(UNLOCKED_IMAGE);
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Texture getTexture(Color scheme) {
        return texture;
    }

    @Override
    public void tick(float tickTime) {}

    private void refreshTexture(Pixmap source) {
        pixmap.setColor(Color.CLEAR);
        pixmap.fill();
        for (byte x = 0; x < pixmap.getWidth(); x++) {
            for (byte y = 0; y < pixmap.getHeight(); y++) {
                if (source.getPixel(x, y) == 255) {
                    pixmap.drawPixel(x, y, Color.rgba8888(color));
                }
            }
        }
        texture.draw(pixmap, 0, 0);
    }
}
