package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author mibbio
 */
public class EnergyOrb extends Wire {

    private Sprite sprite;

    public EnergyOrb(float x, float y) {
        super(x, y, Color.WHITE);
        setEnergyLevel(1f);
        minEnergy = 0;
        pulseStep = super.pulseStep * 10f;
        sprite = new Sprite(texture);
    }

    @Override
    public void tick(float tickTime) {
        super.tick(tickTime);
    }

    @Override
    public Texture getTexture(Color scheme) {
        super.getTexture(scheme);
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(MARGIN, MARGIN, pixmap.getWidth() - (2*MARGIN), pixmap.getHeight() - (2*MARGIN));
        texture.draw(pixmap, 0, 0);
        return texture;
    }

    public void draw(SpriteBatch batch) {
        sprite = new Sprite(getTexture(null));
        sprite.rotate(45);
        sprite.setPosition(getBounds().x, getBounds().y);
        sprite.draw(batch);
    }
}
