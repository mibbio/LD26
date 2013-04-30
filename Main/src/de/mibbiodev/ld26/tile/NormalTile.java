package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author mibbio
 */
public class NormalTile extends Tile {

    public NormalTile(float x, float y, String srcImage) {
        super(false, x, y, srcImage);
    }

    @Override
    public void tick(float tickTime) {}

    @Override
    public Sprite getSprite(Color tint) {
        sprite.setColor(tint.cpy().mul(shade));
        return sprite;
    }
}
