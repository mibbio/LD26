package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author mibbio
 */
public class NormalTile extends Tile {

    public NormalTile() {
        super(false);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
    }

    @Override
    public Texture getTexture(Color scheme) {
        pixmap.setColor(scheme.cpy().mul(shade));
        pixmap.fill();
        texture.draw(pixmap, 0, 0);
        return texture;
    }
}
