package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class NormalTile extends Tile {

    private float tileTickTime;
    private float timeSinceLastTick = 0;

    public NormalTile() {
        super(false);
        tileTickTime = LD26Game.BASE_TICK_TIME;
    }

    public NormalTile(float tileTickTime) {
        this();
        this.tileTickTime = tileTickTime;
    }

    @Override
    public void tick(float tickTime) {
        timeSinceLastTick += tickTime;
        if (timeSinceLastTick >= tileTickTime) {
            shade = random.nextFloat();
            clampShade();
            timeSinceLastTick = 0;
        }
    }

    @Override
    public Texture getTexture(Color scheme) {
        pixmap.setColor(scheme.cpy().mul(shade));
        pixmap.fill();
        pixmap.setColor(Color.BLACK);
        //pixmap.drawRectangle(0, 0, LD26Game.TILE_SIZE, LD26Game.TILE_SIZE);
        texture.draw(pixmap, 0, 0);
        return texture;
    }
}
