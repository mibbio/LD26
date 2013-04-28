package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author mibbio
 */
public class NormalTile extends Tile {

    public NormalTile(float x, float y) {
        super(false, x, y);
    }

    @Override
    public void tick(float tickTime) {}

    @Override
    public Texture getTexture(Color scheme) {
        pixelMap.setColor(scheme.cpy().mul(shade));
        pixelMap.fill();
        pixelMap.setColor(Color.BLACK);
        tileTexture.draw(pixelMap, 0, 0);
        return tileTexture;
    }
}
