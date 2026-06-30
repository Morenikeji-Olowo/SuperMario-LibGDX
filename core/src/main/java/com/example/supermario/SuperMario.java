package com.example.supermario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.supermario.screens.PlayScreen;
import com.example.supermario.screens.StartMenu;

import java.security.PublicKey;
import java.util.Stack;

public class SuperMario extends Game {
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 208;
    public static final float PPM =  100;

    public static final short DEFAULT_BIT =1;
    public static final short MARIO_BIT = 2;
    public static final short BRICK_BIT = 4;
    public static final short COIN_BIT = 8;
    public static final short DESTROY_BIT = 16;
    public SpriteBatch batch;

    public static AssetManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("audio/music/mario_music.ogg", Music.class);
        manager.load("audio/music/start_menu_loading.mp3", Music.class);
        manager.load("audio/sounds/coin.wav",  Sound.class);
        manager.load("audio/sounds/bump.wav",  Sound.class);
        manager.load("audio/sounds/breakblock.wav",  Sound.class);
        manager.load("audio/sounds/game_over.mp3",  Sound.class);
        manager.finishLoading();
        setScreen(new StartMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
