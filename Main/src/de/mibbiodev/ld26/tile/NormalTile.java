package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.mibbiodev.ld26.LD26Game;

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
        pixmap.setColor(Color.BLACK);
        pixmap.drawRectangle(0, 0, LD26Game.TILE_SIZE, LD26Game.TILE_SIZE);
        texture.draw(pixmap, 0, 0);
        return texture;
    }
}
