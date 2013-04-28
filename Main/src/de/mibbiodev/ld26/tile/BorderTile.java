package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class BorderTile extends Tile {

    public BorderTile(float x, float y) {
        super(true, x, y);
    }

    @Override
    public void tick(float tickTime) {}

    @Override
    public Texture getTexture(Color scheme) {
        Color c = scheme.cpy().mul(shade);
        pixelMap.setColor(c);
        pixelMap.fill();
        pixelMap.setColor(Color.DARK_GRAY);
        pixelMap.drawRectangle(0, 0, LD26Game.TILE_SIZE, LD26Game.TILE_SIZE);
        tileTexture.draw(pixelMap, 0, 0);
        return tileTexture;
    }
}
