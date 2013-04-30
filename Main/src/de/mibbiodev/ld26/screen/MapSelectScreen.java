package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.mibbiodev.ld26.LD26Game;

/**
 * @author mibbio
 */
public class MapSelectScreen implements Screen {

    private LD26Game game;

    private Stage stage;
    private BitmapFont blackFont;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private SpriteBatch batch;

    private ChangeListener buttonListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            TextButton tb = (TextButton) actor;
            if (tb.getText().toString().equals("back")) {
                game.setScreen(new MainMenuScreen(game));
            } else {
                game.setScreen(new RoomScreen(game, Color.ORANGE, true));
            }
        }
    };

    public MapSelectScreen(LD26Game game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        if (stage == null) {
            stage = new Stage(width, height, true);
        }
        stage.clear();

        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("buttonnormal");
        style.down = skin.getDrawable("buttonpressed");
        style.font = blackFont;

        // generating buttons
        int count = 0;
        FileHandle[] test = Gdx.files.internal("maps").list();
        for (FileHandle handle : test) {
            if (handle.isDirectory()) {
                count++;
                TextButton btn = new TextButton(handle.name(), style);
                btn.setWidth(200);
                btn.setHeight(50);
                if (count <= 5) {
                    btn.setX(Gdx.graphics.getWidth() / 2 - 225);
                    btn.setY(Gdx.graphics.getHeight() - 75 * count);
                } else {
                    btn.setX(Gdx.graphics.getWidth() / 2 + 25);
                    btn.setY(Gdx.graphics.getHeight() - 75 * (count-5));
                }
                btn.addListener(buttonListener);
                stage.addActor(btn);
            }
        }

        TextButton backButton = new TextButton("back", style);
        backButton.setWidth(200);
        backButton.setHeight(50);
        backButton.setX(Gdx.graphics.getWidth() / 2 - 100);
        backButton.setY(25);
        backButton.addListener(buttonListener);
        stage.addActor(backButton);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas("data/ui/button.pack");
        skin = new Skin();
        skin.addRegions(textureAtlas);
        blackFont = new BitmapFont(Gdx.files.internal("data/ui/font/blackfont.fnt"), false);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        batch.dispose();
        skin.dispose();
        textureAtlas.dispose();
        blackFont.dispose();
        stage.dispose();
    }
}
