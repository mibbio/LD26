package de.mibbiodev.ld26;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author mibbio
 */
public class DesktopStarter {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = LD26Game.TITLE;
        config.useGL20 = true;
        config.width = LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE;
        config.height = LD26Game.TILE_SIZE * LD26Game.ROOM_SIZE;
        config.vSyncEnabled = true;
        config.resizable = false;
        new LwjglApplication(new LD26Game(), config);
    }
}
