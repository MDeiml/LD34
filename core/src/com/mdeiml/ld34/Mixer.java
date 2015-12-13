package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Mixer extends Machine {
    
    private static final float ANIM_TIME = 3;
    public static TextureRegion[] frames;
    
    private int x;
    private int y;
    private float animTime;
    private Product[] left;
    private Product[] right;
    private ConveyorBelt after;
    private Key[] keys;
    
    public Mixer(int x, int y) {
        this.x = x;
        this.y = y;
        this.animTime = 0;
        this.left = new Product[4];
        this.right = new Product[4];
    }
    

    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(animTime > 0) {
            animTime -= delta;
            if(animTime <= 0) {
                if(left[0] != null && right[0] != null) {
                    after.takeProduct(left[0]);
                    for(int i = 0; i < left.length-1; i++) {
                        left[i] = left[i+1];
                        right[i] = right[i+1];
                    }
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(frames[(int)(animTime/ANIM_TIME*7)], animTime, animTime);
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
        if(p.getX() < x + 32) {
            //left
            for(int i = 0; i < left.length; i++) {
                if(left[i] == null) {
                    left[i] = p;
                    return true;
                }
            }
            return false;
        }else {
            //right
            for(int i = 0; i < right.length; i++) {
                if(right[i] == null) {
                    right[i] = p;
                    return true;
                }
            }
            return false;
        }
    }

}
