package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author mibbio
 */
public class Player extends Entity {

    private Pixmap renderImage;
    private Texture renderTexture;
    private Color lampColor = new Color();
    private float lampBrightness = 0;
    private float blinkStep = 0.05f;

    public Player(FileHandle imageFile) {
        super(imageFile);
        renderImage = new Pixmap(rawImage.getWidth(), rawImage.getHeight(), rawImage.getFormat());
        renderTexture = new Texture(renderImage);
    }

    @Override
    public void tick() {
        lampBrightness += blinkStep;
        if (lampBrightness >= 1) {
            lampBrightness = 1;
            blinkStep = -blinkStep;
        } else if (lampBrightness <= 0) {
            lampBrightness = 0;
            blinkStep = -blinkStep;
        }
        lampColor = Color.ORANGE.cpy().mul(lampBrightness);

        for (byte x = 0; x < rawImage.getWidth(); x++) {
            for (byte y = 0; y < rawImage.getHeight(); y++) {
                int pixel = rawImage.getPixel(x, y);
                if (pixel == Color.rgba8888(Color.YELLOW)) {
                    renderImage.drawPixel(x, y, Color.rgba8888(lampColor));
                } else {
                    renderImage.drawPixel(x, y, pixel);
                }
            }
        }
        renderTexture.draw(renderImage, 0, 0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        Sprite sprite = new Sprite(renderTexture);
        sprite.rotate(-90 * orientation.ordinal());
        sprite.setPosition(50, 50);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        renderTexture.dispose();
        renderImage.dispose();
        super.dispose();
    }
}
