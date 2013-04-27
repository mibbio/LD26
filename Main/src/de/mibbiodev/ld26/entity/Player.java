package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.screen.RoomScreen;

/**
 * @author mibbio
 */
public class Player extends MovableEntity {

    private Pixmap renderImage;
    private Color lampColor = Color.WHITE;
    private float lampBrightness = 0;
    private float energyLevel = 1f;
    private float energyLoss = 0.02f;

    public Player(Pixmap rawImage, Vector2 position, float speed, RoomScreen room) {
        super(rawImage, position, speed, room);
        renderImage = new Pixmap(rawImage.getWidth(), rawImage.getHeight(), rawImage.getFormat());
    }

    public Color getLampColor() {
        return lampColor;
    }

    public void setLampColor(Color lampColor) {
        this.lampColor = lampColor;
    }

    @Override
    public void tick(float tickTime) {
        lampBrightness += energyLevel * tickTime;
        if (lampBrightness >= 1) {
            lampBrightness = 1;
            energyLevel = -energyLevel;
        } else if (lampBrightness <= 0) {
            lampBrightness = 0;
            energyLevel = -energyLevel;
        }

        if (energyLevel < 0) energyLevel += energyLoss * tickTime;
        else energyLevel -= energyLoss * tickTime;

        System.out.println(Math.abs(energyLevel));
        if (Math.abs(energyLevel) < 0.001f) {
            System.out.println("insufficient energy");
            // TODO energylevel auswerten
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
