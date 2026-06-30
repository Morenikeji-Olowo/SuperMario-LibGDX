package com.example.supermario.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.example.supermario.SuperMario;
import com.example.supermario.screens.PlayScreen;

public class Mario extends Sprite {
    public World world;
    public Body b2body;

    public State currentState;
    public State previousState;

    private TextureRegion marioStand;

    private Animation<TextureRegion> marioRun;
    private Animation<TextureRegion> marioJump;


    private float stateTimmer;
    private boolean runningRight;

    public Mario(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;

        stateTimmer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 4; i++){
            frames.add(new TextureRegion(getTexture(), 2 + (i * 16), 12, 16, 16));
        }

        marioRun = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 4; i < 6 ; i++){
            frames.add(new TextureRegion(getTexture(), 66, 12, 16, 16));
        }
        marioJump = new Animation(0.1f, frames);

        marioStand = new TextureRegion(getTexture(), 2,12, 16, 16);

        defineMario();

        setBounds(2,12,16/SuperMario.PPM,16/SuperMario.PPM);
        setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }
    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;

        switch(currentState){
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimmer);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimmer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        stateTimmer = currentState == previousState ? stateTimmer + dt :0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        if(b2body.getLinearVelocity().y < 0){
            return State.FALLING;
        }
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)){
            return State.JUMPING;
        }
        else {
            return State.STANDING;
        }
//        if(currentState == State.STANDING){
//            return State.STANDING;
//        }
//        if(currentState == State.JUMPING){
//            return State.JUMPING;
//        }
//        if(currentState == State.RUNNING){
//            return State.RUNNING;
//        }
//        if(currentState == State.FALLING){
//            return State.FALLING;
//        }
    }
    public void  defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 /SuperMario.PPM, 32 / SuperMario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(100 / SuperMario.PPM, 100 / SuperMario.PPM);

        b2body = world.createBody(bdef);;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6/ SuperMario.PPM);
        fdef.filter.categoryBits = SuperMario.MARIO_BIT;
        fdef.filter.maskBits = SuperMario.DEFAULT_BIT | SuperMario.COIN_BIT | SuperMario.BRICK_BIT | SuperMario.MARIO_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        //create sensor on mario head
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 /SuperMario.PPM, 5 / SuperMario.PPM),  new Vector2(2 /SuperMario.PPM, 5 / SuperMario.PPM));
        fdef.shape =  head;

        b2body.createFixture(fdef).setUserData("head");
        fdef.isSensor = true;
    }
}
