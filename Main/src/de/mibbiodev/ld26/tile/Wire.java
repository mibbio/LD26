package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class Wire extends Tile {

    private static final int MARGIN = LD26Game.TILE_SIZE / 4;

    private Color color;
    private float pulseStep = 0.01f;
    private float pulseStrength = 0;

    public Wire(float x, float y, Color color) {
        super(false, x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void tick(float tickTime) {
        pulseStrength += pulseStep;
        if (pulseStrength >= 1) {
            pulseStrength = 1;
            pulseStep *= -1;
        } else if (pulseStrength <= 0.5f) {
            pulseStrength = 0.5f;
            pulseStep *= -1;
        }
    }

    @Override
    public Texture getTexture(Color scheme) {
        pixmap.setColor(color.cpy().mul(pulseStrength));
        pixmap.fillRectangle(MARGIN, MARGIN, pixmap.getWidth() - (2*MARGIN), pixmap.getHeight() - (2*MARGIN));
        texture.draw(pixmap, 0, 0);
        return texture;
    }
}
