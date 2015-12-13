package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Hacker extends Machine {

    private static final float TIME = 3;
    private static final float ANIM_TIME = 0.5f;
    private static final float ANIM_TIME1 = 0.5f;
    public static TextureRegion[] blade;
    public static TextureRegion[] trapdoor;
    
    private int x;
    private int y;
    private ConveyorBelt left;
    private ConveyorBelt right;
    private Product p;
    private Key[] keys;
    private float timer;
    private float animTime;
    private float animTime1;
    
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
                animTime1 = ANIM_TIME1;
            }
        }
        if(animTime > 0) {
            animTime1 = 0;
            animTime -= delta;
            if(animTime <= 0) {
                animTime = 0;
                animTime1 = ANIM_TIME1;
            }
        }
        if(animTime1 > 0) {
            timer = 0;
            animTime1 -= delta;
            if(p != null && animTime1 < ANIM_TIME1/2) {
                p.setX(x);
                fallings.add(new FallingProduct(p, left.getY(), left));
                p = null;
            }
            if(animTime1 <= 0) {
                animTime1 = 0;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(trapdoor[(int)(animTime1/ANIM_TIME1*7)], x, y);
        batch.draw(blade[(int)(animTime/ANIM_TIME*7)], x+16, y+21);
        if(p != null)
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
        p.setX(x+8);
        p.setY(y+21);
        this.p = p;
        this.timer = TIME;
        return true;
    }

    public void setLeft(ConveyorBelt left) {
        this.left = left;
    }

    public void setRight(ConveyorBelt right) {
        this.right = right;
    }

}
