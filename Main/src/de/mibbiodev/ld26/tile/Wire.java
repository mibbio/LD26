package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.entity.Energized;

/**
 * @author mibbio
 */
public class Wire extends Tile implements Energized {

    private static final int MARGIN = LD26Game.TILE_SIZE / 4;

    private Color color;
    private float energyLevel = 0;
    private float pulseStep = 0.2f;
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
        float maxStrength = Math.min((0.2f + energyLevel), 1.0f);
        pulseStrength += pulseStep * tickTime;
        if (pulseStrength >= maxStrength) {
            pulseStrength = maxStrength;
            pulseStep *= -1;
        } else if (pulseStrength <= 0.2f) {
            pulseStrength = 0.2f;
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

    @Override
    public void setEnergyLevel(float energyLevel) {
        this.energyLevel = energyLevel;
    }

    @Override
    public float getEnergyLevel() {
        return Math.abs(energyLevel);
    }

    @Override
    public void drainEnergy(Energized target, float amount) {}

    @Override
    public void addEnergy(Energized target, float amount) {}
}
