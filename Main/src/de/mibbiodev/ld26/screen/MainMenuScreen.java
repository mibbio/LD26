package de.mibbiodev.ld26.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
public class MainMenuScreen implements Screen {

    private LD26Game game;

    private Stage stage;
    private BitmapFont blackFont;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private SpriteBatch batch;

    private ChangeListener buttonListener = new ChangeListener() {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            switch ((int) actor.getY()) {
                case 300:   // Start Game
                    game.map = "map01";
                    game.handleAbort("start");
                    break;
                case 200:   // Load Map
                    break;
                case 100:   // Exit
                    game.map = "";
                    game.handleAbort("exit");
                    break;
            }
        }
    };

    public MainMenuScreen(LD26Game game) {
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

        TextButton btnStartGame = new TextButton("Start Game", style);
        btnStartGame.setWidth(200);
        btnStartGame.setHeight(50);
        btnStartGame.setX(Gdx.graphics.getWidth() / 2 - btnStartGame.getWidth() / 2);
        btnStartGame.setY(300);
        btnStartGame.addListener(buttonListener);

        TextButton btnLoadMap = new TextButton("Load Map...", style);
        btnLoadMap.setWidth(200);
        btnLoadMap.setHeight(50);
        btnLoadMap.setX(Gdx.graphics.getWidth() / 2 - btnLoadMap.getWidth() / 2);
        btnLoadMap.setY(200);
        btnLoadMap.addListener(buttonListener);

        TextButton btnEndGame = new TextButton("Exit", style);
        btnEndGame.setWidth(200);
        btnEndGame.setHeight(50);
        btnEndGame.setX(Gdx.graphics.getWidth() / 2 - btnEndGame.getWidth() / 2);
        btnEndGame.setY(100);
        btnEndGame.addListener(buttonListener);

        stage.addActor(btnStartGame);
        stage.addActor(btnLoadMap);
        stage.addActor(btnEndGame);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas("data/ui/button.pack");
        skin = new Skin();
        skin.addRegions(textureAtlas);
        blackFont = new BitmapFont(Gdx.files.internal("data/ui/blackfont.fnt"), false);
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
