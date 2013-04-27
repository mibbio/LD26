package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author mibbio
 */
public abstract class Entity {

    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }

    protected Pixmap rawImage;

    protected Vector2 position = Vector2.Zero;
    protected Vector2 velocity = Vector2.Zero;
    protected Orientation orientation = Orientation.NORTH;

    protected Entity(FileHandle imageFile) {
        rawImage = new Pixmap(imageFile);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void dispose() {
        rawImage.dispose();
    }

    public abstract void tick();

    public abstract void draw(SpriteBatch batch);
}
