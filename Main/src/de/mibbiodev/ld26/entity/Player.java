package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.screen.RoomScreen;
import de.mibbiodev.ld26.tile.Wire;

/**
 * @author mibbio
 */
public class Player extends MovableEntity implements Energized {
    private Pixmap renderImage;
    private Color lampColor = Color.WHITE;
    private float lampBrightness = 0;
    private float energyLevel = 1f;
    private float pulseStep = 1f;

    public Player(Pixmap rawImage, Vector2 position, float speed, RoomScreen room) {
        super(rawImage, position, speed, room);
        renderImage = new Pixmap(rawImage.getWidth(), rawImage.getHeight(), rawImage.getFormat());
    }

    public void setLampColor(Color lampColor) {
        this.lampColor = lampColor;
    }

    @Override
    public void setEnergyLevel(float energyLevel) {
        if (this.energyLevel > energyLevel) {
            room.getGame().getSoundManager().play("charge03", 0.75f, 0.75f,  getClass());
        } else {
            room.getGame().getSoundManager().play("charge03", 0.75f, 1.25f,  getClass());
        }
        this.energyLevel = energyLevel;
    }

    @Override
    public float getEnergyLevel() {
        return Math.abs(energyLevel);
    }

    @Override
    public boolean drainEnergy(Energized target, float amount) {
        //if (energyLevel > 0.95f) return false;
        float targetEnergy = target.getEnergyLevel();
        float myEnergy = this.getEnergyLevel();

        if (targetEnergy > amount && (1-myEnergy) > amount) {
            targetEnergy -= amount;
            myEnergy += amount;
            target.setEnergyLevel(targetEnergy);
            this.setEnergyLevel(myEnergy);
            if (target instanceof Wire) {
                Wire w = (Wire)target;
                lampColor = w.getColor();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addEnergy(Energized target, float amount) {
        return target.drainEnergy(this, amount);
    }

    @Override
    public void tick(float tickTime) {
        float maxStrength = Math.min((0.2f + energyLevel), 1.0f);
        lampBrightness += pulseStep * tickTime;
        if (lampBrightness >= maxStrength) {
            lampBrightness = maxStrength;
            pulseStep *= -1;
        } else if (lampBrightness <= 0) {
            lampBrightness = 0;
            pulseStep *= -1;
        }

        float energyLoss = 0.01f;
        if (energyLevel < 0) energyLevel += energyLoss * tickTime;
        else energyLevel -= energyLoss * tickTime;

        if (Math.abs(energyLevel) < 0.02f) {
            room.abortReaseon = "dead";
        }

        for (byte x = 0; x < rawImage.getWidth(); x++) {
            for (byte y = 0; y < rawImage.getHeight(); y++) {
                int pixel = rawImage.getPixel(x, y);
                if (pixel == Color.rgba8888(Color.YELLOW)) {
                    renderImage.drawPixel(x, y, Color.rgba8888(lampColor.cpy().mul(lampBrightness)));
                } else {
                    renderImage.drawPixel(x, y, pixel);
                }
            }
        }
        renderTexture.draw(renderImage, 0, 0);
        super.tick(tickTime);
    }

    @Override
    public void draw(SpriteBatch batch) {
        Sprite sprite = new Sprite(renderTexture);
        sprite.rotate(-90 * orientation.ordinal());
        sprite.setPosition(position.x * LD26Game.TILE_SIZE, position.y * LD26Game.TILE_SIZE);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        renderImage.dispose();
        super.dispose();
    }
}
