package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.tile.*;

/**
 * @author mibbio
 */
public class RoomScreen extends GameScreen {

    private static final byte ROOM_SIZE = LD26Game.ROOM_SIZE;

    private Game game;
    private Color schemeColor;
    private Tile[][] roomTiles;

    public RoomScreen(Game game, Color schemeColor) {
        this.game = game;
        this.schemeColor = schemeColor;

    }

    @Override
    public void render(float delta) {
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

    }

    @Override
    public void show() {
        roomTiles = new Tile[ROOM_SIZE][ROOM_SIZE];
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                if (x == 0 || x == ROOM_SIZE-1 || y == 0 || y == ROOM_SIZE-1) {
                    roomTiles[x][y] = new BorderTile();
                } else {
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        for (byte x = 0; x < ROOM_SIZE; x++) {
            for (byte y = 0; y < ROOM_SIZE; y++) {
                roomTiles[x][y].dispose();
            }
        }
        super.dispose();
    }
}
