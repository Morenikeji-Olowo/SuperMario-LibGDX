package com.example.supermario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.supermario.SuperMario;

public class StartMenu implements Screen {
    private  SuperMario game;
    private SpriteBatch batch;
    private Texture texture;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Music  music;

    private BitmapFont font;

    private Stage stage;

    public StartMenu(SuperMario game){
        this.game = game;
        this.batch = game.batch;

        camera = new OrthographicCamera();
        viewport = new FitViewport(SuperMario.V_WIDTH, SuperMario.V_HEIGHT, camera);
        camera.position.set(SuperMario.V_WIDTH / 2f, SuperMario.V_HEIGHT / 2f, 0);
        camera.update();

        texture = new Texture("start_menu_screen.jpg");

        music = SuperMario.manager.get("audio/music/start_menu_loading.mp3", Music.class);
        music.setLooping(true);
        music.play();

        font = new BitmapFont();
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.overFontColor = Color.YELLOW;

        Label.LabelStyle titleStyle = new Label.LabelStyle(font, Color.RED);
        Label titleLabel = new Label("SuperMario", titleStyle);

// Change button text to show the keys
        TextButton playButton = new TextButton("P - Play", buttonStyle);
        TextButton quitButton = new TextButton("Q - Quit", buttonStyle);

        stage = new Stage(new FitViewport(SuperMario.V_WIDTH, SuperMario.V_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(titleLabel).padBottom(30).row();
        table.add(playButton).width(100).height(30).padBottom(10).row();
        table.add(quitButton).width(100).height(30).row();
        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture, 0, 0, SuperMario.V_WIDTH, SuperMario.V_HEIGHT);
        batch.end();

        stage.act(delta);
        stage.draw();

        // Keyboard input
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.P)) {
            music.stop();
            game.setScreen(new PlayScreen(game));
        }
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}
