package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author mibbio
 */
public class BorderTile extends Tile {

    public BorderTile() {
        super(true);
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
    }

    @Override
    public Texture getTexture(Color scheme) {
        Color c = scheme.cpy().mul(shade);
        pixmap.setColor(1-c.r, 1-c.g, 1-c.b, 1);
        pixmap.fill();
        texture.draw(pixmap, 0, 0);
        return texture;
    }
}
