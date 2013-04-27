package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.screen.RoomScreen;

/**
 * @author mibbio
 */
public abstract class MovableEntity extends Entity {

    protected Vector2 velocity;
    protected float speed;

    protected MovableEntity(Pixmap rawImage, Vector2 position, float speed, RoomScreen room) {
        super(rawImage, position, room);
        this.speed = speed;
        velocity = new Vector2(0, 0);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void tick(float tickTime) {
        Vector2 oldPosition = position.cpy();
        position.add(velocity.cpy().scl(tickTime * speed));
        super.tick(tickTime);

        Rectangle newBounds = bounds;
        switch (orientation) {
            case EAST:
                newBounds.x += bounds.getWidth();
                break;
            case NORTH:
                newBounds.y += bounds.getHeight();
                break;
        }

        if (!room.getInsection(newBounds).isWalkable()) {
            position = oldPosition;
            super.tick(tickTime);
        }
    }
}
