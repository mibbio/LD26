package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.entity.Energized;

/**
 * @author mibbio
 */
public class Wire extends Tile implements Energized {

    protected static final int MARGIN = LD26Game.TILE_SIZE / 4;

    private Color color;
    protected float energyLevel = 0;
    protected float minEnergy = 0.2f;
    protected float pulseStep = 0.2f;
    protected float pulseStrength = 0;

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
        float maxStrength = Math.min((minEnergy + energyLevel), 1.0f);
        pulseStrength += pulseStep * tickTime;
        if (pulseStrength >= maxStrength) {
            pulseStrength = maxStrength;
            pulseStep *= -1;
        } else if (pulseStrength <= minEnergy) {
            pulseStrength = minEnergy;
            pulseStep *= -1;
        }
    }

    @Override
    public Texture getTexture(Color scheme) {
        pixelMap.setColor(color.cpy().mul(pulseStrength));
        pixelMap.fillRectangle(MARGIN, MARGIN, pixelMap.getWidth() - (2*MARGIN), pixelMap.getHeight() - (2*MARGIN));
        tileTexture.draw(pixelMap, 0, 0);
        return tileTexture;
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
    public boolean drainEnergy(Energized target, float amount) { return false; }

    @Override
    public boolean addEnergy(Energized target, float amount) { return false; }
}
