package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author mibbio
 */
public class ExitTile extends Tile {

    public ExitTile(float x, float y, String srcImage) {
        super(false, x, y, srcImage);
        sprite.setColor(Color.GRAY);
    }

    @Override
    public Sprite getSprite(Color tint) {
        return sprite;
    }

    @Override
    public void tick(float tickTime) {}
}
