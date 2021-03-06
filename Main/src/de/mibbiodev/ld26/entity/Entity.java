package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.Tickable;
import de.mibbiodev.ld26.screen.RoomScreen;

/**
 * @author mibbio
 */
public abstract class Entity implements Tickable {

    protected RoomScreen room;
    protected Pixmap rawImage;
    protected Texture renderTexture;
    protected Orientation orientation = Orientation.NORTH;
    protected Vector2 position;
    protected Rectangle bounds;

    protected Entity(Pixmap rawImage, Vector2 position, RoomScreen room) {
        this.position = position;
        this.rawImage = rawImage;
        this.room = room;
        this.renderTexture = new Texture(rawImage);
        bounds = new Rectangle(position.x, position.y, rawImage.getWidth(), rawImage.getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        bounds.setX(position.x * LD26Game.TILE_SIZE);
        bounds.setY(position.y * LD26Game.TILE_SIZE);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void dispose() {
        renderTexture.dispose();
        rawImage.dispose();
    }

    public void tick(float tickTime) {
        bounds.setX(position.x * LD26Game.TILE_SIZE);
        bounds.setY(position.y * LD26Game.TILE_SIZE);
    }

    public abstract void draw(SpriteBatch batch);
}
