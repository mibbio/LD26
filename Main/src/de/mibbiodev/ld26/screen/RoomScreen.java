package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.entity.Player;
import de.mibbiodev.ld26.input.AppInput;
import de.mibbiodev.ld26.input.PlayerInput;
import de.mibbiodev.ld26.tile.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mibbio
 */
public class RoomScreen extends GameScreen {

    private static final byte ROOM_SIZE = LD26Game.ROOM_SIZE;

    private Color schemeColor;
    private FileHandle groundFile;
    private FileHandle wireFile;
    private Tile[][] roomTiles;

    private List<Wire> wires;

    private Player player;

    private InputMultiplexer inputMultiplexer;

    private float timeSinceLastTick = 0;

    public RoomScreen(Game game, Color schemeColor, String mapName) {
        super(game);
        this.schemeColor = schemeColor;
        groundFile = Gdx.files.internal("data/maps/" + mapName + "_ground.png");
        wireFile = Gdx.files.internal("data/maps/" + mapName + "_wires.png");
        player = new Player(
                new Pixmap(Gdx.files.internal("data/entities/player2.png")),
                new Vector2(2, 2),
                2f, this
        );

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new PlayerInput(player));
        inputMultiplexer.addProcessor(new AppInput(game));

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public List<Tile> getInsections(Rectangle bounds) {
        List<Tile> intersections = new ArrayList<Tile>();
        for (byte x = 0; x < LD26Game.ROOM_SIZE; x++) {
            for (byte y = 0; y < LD26Game.ROOM_SIZE; y++) {
                if (roomTiles[x][y].getBounds().overlaps(bounds)) {
                    intersections.add(roomTiles[x][y]);
                }
            }
        }
        return intersections;
    }

    @Override
    public void render(float delta) {
        if (isPaused) return;

        // calculate tick time
        timeSinceLastTick += delta;
        if (timeSinceLastTick >= LD26Game.BASE_TICK_TIME) {
            tick(timeSinceLastTick);
            timeSinceLastTick = 0;
        }

        renderStart();
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                batch.draw(roomTiles[x][y].getTexture(schemeColor), x*LD26Game.TILE_SIZE, y*LD26Game.TILE_SIZE);
            }
        }

        for (Wire wire : wires) {
            batch.draw(wire.getTexture(null), wire.getBounds().x, wire.getBounds().y);
        }

        player.draw(batch);
        renderEnd();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();

        // loading ground tiles and walls
        Pixmap groundImage = new Pixmap(groundFile);
        roomTiles = new Tile[ROOM_SIZE][ROOM_SIZE];
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                int t = groundImage.getPixel(x, ROOM_SIZE-1-y);

                if (t == Color.rgba8888(Color.BLACK)) {
                    roomTiles[x][y] = new BorderTile(x, y);
                } else if (t == Color.rgba8888(Color.WHITE)) {
                    roomTiles[x][y] = new NormalTile(2f, x, y);
                }
            }
        }
        groundImage.dispose();

        // loading wires
        Pixmap wireImage = new Pixmap(wireFile);
        wires = new ArrayList<Wire>();
        Color color = new Color();
        for (byte x = 0; x < wireImage.getWidth(); x++) {
            for (byte y = 0; y < wireImage.getHeight(); y++) {
                Color.rgba8888ToColor(color, wireImage.getPixel(x, ROOM_SIZE-1-y));
                if (color.a > 0) {
                    System.out.println("Alpha " + color.a);
                    wires.add(new Wire(x, y, color.cpy()));
                }
            }
        }
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                roomTiles[x][y].dispose();
            }
        }

        player.dispose();

        super.dispose();
    }

    @Override
    public void tick(float tickTime) {
        for (byte x = 0; x < LD26Game.ROOM_SIZE; x++) {
            for (byte y = 0; y < LD26Game.ROOM_SIZE; y++) {
                roomTiles[x][y].tick(tickTime);
            }
        }

        for (Wire wire : wires) {
            wire.tick(tickTime);
            if (wire.getBounds().overlaps(player.getBounds())) {
                player.setLampColor(wire.getColor());
            }
        }

        player.tick(tickTime);
    }
}
