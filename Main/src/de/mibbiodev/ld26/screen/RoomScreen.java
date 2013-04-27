package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.tile.*;

/**
 * @author mibbio
 */
public class RoomScreen extends GameScreen {

    private static final byte ROOM_SIZE = LD26Game.ROOM_SIZE;

    private Color schemeColor;
    private Pixmap roomStructure;
    private Tile[][] roomTiles;

    public RoomScreen(Game game, Color schemeColor, FileHandle mapFile) {
        super(game);
        this.schemeColor = schemeColor;
        this.roomStructure = new Pixmap(mapFile);
    }

    @Override
    public void render(float delta) {
        if (isPaused) return;
        renderStart();
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                batch.draw(roomTiles[x][y].getTexture(schemeColor), x*LD26Game.TILE_SIZE, y*LD26Game.TILE_SIZE);
            }
        }
        renderEnd();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
        roomTiles = new Tile[ROOM_SIZE][ROOM_SIZE];
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                int t = roomStructure.getPixel(x, ROOM_SIZE-1-y);

                if (t == Color.rgba8888(Color.BLACK)) {
                    roomTiles[x][y] = new BorderTile();
                } else if (t == Color.rgba8888(Color.WHITE)) {
                    roomTiles[x][y] = new NormalTile();
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

        roomStructure.dispose();
        super.dispose();
    }

    @Override
    public void tick() {
        for (byte x = 0; x < LD26Game.ROOM_SIZE; x++) {
            for (byte y = 0; y < LD26Game.ROOM_SIZE; y++) {
                roomTiles[x][y].tick();
            }
        }
    }
}
