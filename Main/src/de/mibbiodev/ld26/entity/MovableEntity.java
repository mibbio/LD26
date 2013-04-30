package de.mibbiodev.ld26.entity;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.screen.RoomScreen;
import de.mibbiodev.ld26.tile.ExitTile;
import de.mibbiodev.ld26.tile.Tile;

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
        Vector2 newPosition = position.cpy();
        newPosition.add(velocity.cpy().scl(tickTime * speed));
        setPosition(newPosition);

        for (Tile tile : room.getInsections(bounds)) {
            if (this instanceof Player && tile instanceof ExitTile) room.abortReaseon = "success";
            if (!tile.isWalkable()) setPosition(oldPosition);
        }
    }
}
