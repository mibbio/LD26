package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author mibbio
 */
public class EnergyOrb extends Wire {

    public EnergyOrb(float x, float y, String srcImage) {
        super(x, y, Color.WHITE, srcImage);
        setEnergyLevel(1f);
        minEnergy = 0;
        pulseStep = super.pulseStep * 10f;
    }

    @Override
    public void tick(float tickTime) {
        super.tick(tickTime);
    }

    @Override
    public Sprite getSprite(Color tint) {
        return super.getSprite(tint);
    }

    /*
    @Override
    public Texture getTexture(Color scheme) {
        super.getTexture(scheme);
        pixelMap.setColor(Color.WHITE);
        pixelMap.drawRectangle(MARGIN, MARGIN, pixelMap.getWidth() - (2*MARGIN), pixelMap.getHeight() - (2*MARGIN));
        tileTexture.draw(pixelMap, 0, 0);
        return tileTexture;
    }

    /*
    public void draw(SpriteBatch batch) {
        sprite = new Sprite(getTexture(null));
        sprite.rotate(45);
        sprite.setPosition(getBounds().x, getBounds().y);
        sprite.draw(batch);
    }
    */
}
