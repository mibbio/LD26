package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    public Wire(float x, float y, Color color, String srcImage) {
        super(false, x, y, srcImage);
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
    public Sprite getSprite(Color tint) {
        Pixmap pixelMap = new Pixmap((int)sprite.getWidth(), (int)sprite.getHeight(), Pixmap.Format.RGBA8888);
        pixelMap.setColor(color.cpy().mul(pulseStrength));
        pixelMap.fillRectangle(MARGIN, MARGIN, pixelMap.getWidth() - (2 * MARGIN), pixelMap.getHeight() - (2 * MARGIN));
        sprite.setTexture(new Texture(pixelMap));
        return sprite;
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
