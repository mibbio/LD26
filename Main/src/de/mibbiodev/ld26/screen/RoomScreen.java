package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.entity.Player;
import de.mibbiodev.ld26.input.PlayerInput;
import de.mibbiodev.ld26.tile.*;

import java.util.*;

/**
 * @author mibbio
 */
public class RoomScreen extends GameScreen {

    private static final byte ROOM_SIZE = LD26Game.ROOM_SIZE;

    public String abortReaseon = "";

    private Color schemeColor;
    private FileHandle groundFile;
    private FileHandle wireFile;
    private Tile[][] roomTiles;

    private List<WireStrip> wireStrips;
    private List<EnergyOrb> orbs;
    private Player player;

    private float timeSinceLastTick = 0;

    public RoomScreen(LD26Game game, Color schemeColor, String mapName) {
        super(game);
        this.schemeColor = schemeColor;
        groundFile = Gdx.files.internal("data/maps/" + mapName + "_ground.png");
        wireFile = Gdx.files.internal("data/maps/" + mapName + "_wires.png");
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
        for (WireStrip ws : wireStrips) {
            if (ws.getDoor().getBounds().overlaps(bounds)) {
                intersections.add(ws.getDoor());
            }
        }
        return intersections;
    }

    public LD26Game getGame() {
        return game;
    }

    @Override
    public void render(float delta) {
        if (isPaused) return;
        if (!abortReaseon.equals("")) {
            game.handleAbort(abortReaseon);
            return;
        }

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

        for (WireStrip strip : wireStrips) {
            strip.draw(batch);
        }

        for (EnergyOrb orb : orbs) {
            orb.draw(batch);
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

        // loading wires
        Pixmap wireImage = new Pixmap(wireFile);
        wireStrips = WireStrip.load(wireImage);

        // creating orbs
        orbs = new ArrayList<EnergyOrb>();
        Color orbpixel = new Color();
        for (byte x = 0; x < wireImage.getWidth(); x++) {
            for (byte y = 0; y < wireImage.getHeight(); y++) {
                int pixel = wireImage.getPixel(x, LD26Game.ROOM_SIZE - 1 - y);
                Color.rgba8888ToColor(orbpixel, pixel);
                if (orbpixel.equals(Color.LIGHT_GRAY)) {
                    orbs.add(new EnergyOrb(x, y));
                }
            }
        }
        wireImage.dispose();

        // loading ground tiles, walls and doors
        Pixmap groundImage = new Pixmap(groundFile);
        roomTiles = new Tile[ROOM_SIZE][ROOM_SIZE];
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                int t = groundImage.getPixel(x, ROOM_SIZE-1-y);

                if (t == Color.rgba8888(Color.BLACK)) {
                    roomTiles[x][y] = new BorderTile(x, y);
                } else if (t == Color.rgba8888(Color.WHITE)) {
                    roomTiles[x][y] = new NormalTile(x, y);
                } else  if (t == Color.rgba8888(Color.DARK_GRAY)) {
                    roomTiles[x][y] = new ExitTile(x, y);
                } else {
                    Color doorColor = new Color();
                    Color.rgba8888ToColor(doorColor, t);
                    roomTiles[x][y] = new NormalTile(x, y);
                    for (WireStrip strip : wireStrips) {
                        if (strip.getColor().equals(doorColor)) strip.setDoor(new Door(x, y, doorColor));
                    }
                }
            }
        }
        groundImage.dispose();

        player = new Player(
                new Pixmap(Gdx.files.internal("data/entities/player2.png")),
                new Vector2(2, 2),
                2f, this
        );

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new PlayerInput(player));
        inputMultiplexer.addProcessor(game.getGlobalInput());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        player.dispose();
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                roomTiles[x][y].dispose();
            }
        }
        for (WireStrip strip : wireStrips) {
            strip.dispose();
        }
        for (EnergyOrb orb : orbs) {
            orb.dispose();
        }
        super.dispose();
    }

    @Override
    public void tick(float tickTime) {
        game.getSoundManager().tick(tickTime);
        for (byte x = 0; x < LD26Game.ROOM_SIZE; x++) {
            for (byte y = 0; y < LD26Game.ROOM_SIZE; y++) {
                roomTiles[x][y].tick(tickTime);
            }
        }

        for (WireStrip strip : wireStrips) {
            strip.tick(tickTime);
            if (strip.overlaps(player.getBounds())) {
                if (strip.drainEnergy(player, 0.2f * tickTime)) {
                    player.setLampColor(strip.getColor());
                }
            }
        }


        for (Iterator<EnergyOrb> orbIterator = orbs.iterator(); orbIterator.hasNext();) {
            EnergyOrb orb = orbIterator.next();
            if (orb.getEnergyLevel() <= 0.01f) {
                orbIterator.remove();
            } else {
                orb.tick(tickTime);
                if (orb.getBounds().overlaps(player.getBounds())) {
                    player.drainEnergy(orb, 0.2f * tickTime);
                }
            }
        }

        player.tick(tickTime);
    }
}
