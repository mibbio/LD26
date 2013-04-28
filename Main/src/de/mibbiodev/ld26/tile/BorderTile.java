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
        pixmap.setColor(c);
        //pixmap.setColor(1-c.r, 1-c.g, 1-c.b, 1);
        pixmap.fill();
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.drawRectangle(0, 0, LD26Game.TILE_SIZE, LD26Game.TILE_SIZE);
        texture.draw(pixmap, 0, 0);
        return texture;
    }
}
