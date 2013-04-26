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
        config.width = 800;
        config.height = 480;
        config.vSyncEnabled = true;
        new LwjglApplication(new LD26Game(), config);
    }
}
