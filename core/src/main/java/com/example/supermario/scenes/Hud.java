package com.example.supermario.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.supermario.SuperMario;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private static Integer worldTimer;
    private float timeCount;
    private static Integer score;

    private BitmapFont font;
    private OrthographicCamera camera;

    private Label.LabelStyle  labelStyle;

    Label countDownLabel;
    Label timeLabel;
    static Label scoreLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;



    public Hud (SpriteBatch sb){
        score = 0;
        timeCount = 0;
        worldTimer = 000;


        font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(font,Color.WHITE);
        camera = new OrthographicCamera();
        viewport = new FitViewport(SuperMario.V_WIDTH, SuperMario.V_HEIGHT, camera);

        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTimer), style);
        scoreLabel = new Label(String.format("%06d", score), style);
        timeLabel = new Label("TIME:", style);
        levelLabel = new Label("1-1:", style);
        worldLabel = new Label("WORLD:", style);
        marioLabel = new Label("MARIO:", style);

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);

    }
    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            worldTimer++;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public static String displayScore(){
        return String.format("%06d", score);
    }
    @Override
    public void dispose(){
        stage.dispose();
    }
}
