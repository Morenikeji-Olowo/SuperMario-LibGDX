    package com.example.supermario.screens;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.Input;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.audio.Music;
    import com.badlogic.gdx.audio.Sound;
    import com.badlogic.gdx.graphics.Color;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.BitmapFont;
    import com.badlogic.gdx.graphics.g2d.Sprite;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.scenes.scene2d.Stage;
    import com.badlogic.gdx.scenes.scene2d.ui.Label;
    import com.badlogic.gdx.scenes.scene2d.ui.Table;
    import com.badlogic.gdx.utils.ScreenUtils;
    import com.badlogic.gdx.utils.viewport.FitViewport;
    import com.example.supermario.SuperMario;
    import com.example.supermario.scenes.Hud;


    public class GameOverScreen implements Screen {
        private SuperMario game;
        private Stage stage;

        private SpriteBatch batch;
        private Sound  sound;
        private Texture texture;

        GameOverScreen(SuperMario game){
            this.game = game;
            this.batch = game.batch;
            texture = new Texture("game_over_background.jpg");
            stage = new Stage(new FitViewport(SuperMario.V_WIDTH , SuperMario.V_HEIGHT));

            Table table = new Table();
            table.setFillParent(true);
            table.center();
            stage.addActor(table);
            Label gameOverLabel = new Label("GAME OVER",
                new Label.LabelStyle(new BitmapFont(), Color.RED));
            table.add(gameOverLabel).padBottom(10).row();

            Label scoreLabel = new Label("SCORE: " + Hud.displayScore(),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));
            table.add(scoreLabel).padBottom(10).row();

            Label restartLabel = new Label("Press ENTER to restart",
                new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
            table.add(restartLabel).row();
            table.row();
            table.add(scoreLabel);

            sound = SuperMario.manager.get("audio/sounds/game_over.mp3", Sound.class);
            sound.play();

        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                game.setScreen(new StartMenu(game));
            }
            ScreenUtils.clear(0, 0, 0, 1);
            batch.begin();
            batch.draw(texture, 0, 0, SuperMario.V_WIDTH, SuperMario.V_HEIGHT);
            batch.end();

            stage.draw();
        }

        @Override
        public void resize(int width, int height) {

        }

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
            stage.dispose();
            sound.dispose();
            texture.dispose();
            batch.dispose();
        }
    }
