package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author mibbio
 */
public class BorderTile extends Tile {

    public BorderTile(float x, float y, String srcImage) {
        super(true, x, y, srcImage);
    }

    @Override
    public void tick(float tickTime) {}

    @Override
    public Sprite getSprite(Color tint) {
        sprite.setColor(tint);
        return sprite;
    }
}
