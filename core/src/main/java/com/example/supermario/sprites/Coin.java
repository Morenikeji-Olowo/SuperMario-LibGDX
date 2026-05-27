package com.example.supermario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.physics.box2d.World;
import com.example.supermario.SuperMario;
import com.badlogic.gdx.math.Rectangle;
import com.example.supermario.scenes.Hud;

public class Coin extends InteractiveTileObject{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;

    public Coin(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(SuperMario.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "onHeadHit");
        if(getCell().getTile().getId() == BLANK_COIN){
            SuperMario.manager.get("audio/sounds/bump.wav", Sound.class).play();
        }
        else{
            SuperMario.manager.get("audio/sounds/coin.wav", Sound.class).play();
        }
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);

    }
}
