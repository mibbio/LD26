package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;

/**
 * @author mibbio
 */
public class Door extends Tile {
    public static final String LOCKED_IMAGE = "data/entities/door_locked.png";
    public static final String UNLOCKED_IMAGE = "data/entities/door_unlocked.png";

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
        return tileTexture;
    }

    @Override
    public void tick(float tickTime) {}

    private void refreshTexture(String fileName) {
        Pixmap source = new Pixmap(Gdx.files.internal(fileName));
        pixelMap.setColor(Color.CLEAR);
        pixelMap.fill();
        for (byte x = 0; x < pixelMap.getWidth(); x++) {
            for (byte y = 0; y < pixelMap.getHeight(); y++) {
                if (source.getPixel(x, y) == 255) {
                    pixelMap.drawPixel(x, y, Color.rgba8888(color));
                }
            }
        }
        source.dispose();
        tileTexture.draw(pixelMap, 0, 0);
    }
}
