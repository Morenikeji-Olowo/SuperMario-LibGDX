package com.example.supermario.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.example.supermario.SuperMario;
import com.example.supermario.sprites.Brick;
import com.example.supermario.sprites.Coin;

public class B2WorldCreator {
    private BodyDef bdef;
    private FixtureDef fdef;

    public B2WorldCreator(World world, TiledMap map){
        bdef = new BodyDef();
        fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Body body;
    //ground
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / SuperMario.PPM, (rect.getY() + rect.getHeight() /2) /SuperMario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 /SuperMario.PPM, rect.getHeight()/2 / SuperMario.PPM);
            fdef.shape = shape;

            body.createFixture(fdef);
        }
//pipes
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() /2) / SuperMario.PPM, (rect.getY() + rect.getHeight() /2) /SuperMario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2 /SuperMario.PPM, rect.getHeight()/2 / SuperMario.PPM);
            fdef.shape = shape;

            body.createFixture(fdef);
        }

        //brick
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(world, map, rect);
        }

        //coin
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world, map, rect);
        }

    }
}
