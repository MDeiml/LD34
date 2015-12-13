package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Hacker extends Machine {

    private static final float TIME = 3;
    private static final float ANIM_TIME = 0.5f;
    public static TextureRegion[] frames;
    
    private int x;
    private int y;
    private ConveyorBelt left;
    private ConveyorBelt right;
    private Product p;
    private Key[] keys;
    private float timer;
    private float animTime;
    
    public Hacker(int x, int y) {
        this.x = x;
        this.y = y;
        this.timer = 0;
        this.animTime = 0;
    }
    
    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(timer > 0) {
            timer = timer - delta;
            if(timer <= 0) {
                //TODO
            }
        }
        if(animTime > 0) {
            animTime -= delta;
            if(animTime <= 0) {
                animTime = 0;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(frames[(int)(animTime/ANIM_TIME*7)], TIME, TIME);
        p.render(batch);
    }

    @Override
    public int getNumberKeys() {
        return 1;
    }

    @Override
    public Key[] getKeys() {
        return keys;
    }

    @Override
    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    @Override
    public void activate(Key key) {
        animTime = ANIM_TIME;
    }

    @Override
    public boolean takeProduct(Product p) {
        if(this.p != null)
            return false;
        this.p = p;
        this.timer = TIME;
        return true;
    }

}
