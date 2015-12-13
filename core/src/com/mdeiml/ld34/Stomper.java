package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Stomper extends Machine {

    private static final float ANIM_TIME = 0.3f;
    private static final int SPEED = 16;
    public static TextureRegion[] frames;
    
    private int x;
    private int y;
    private float anim;
    private Product p;
    private Key[] keys;
    private ProductTaker after;
    
    public Stomper(int x, int y) {
        this.x = x;
        this.y = y;
    } 
    
    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(anim != 0) {
            boolean b1 = anim > ANIM_TIME/2;
            anim -= delta;
            if(p != null && b1 && anim <= ANIM_TIME/2) {
                Product p1 = p;
                p = null;
                if(p1.getType() == 5) {
                    p = new Product(6, new TextureRegion(LD34Game.a.get("p6.png", Texture.class)));
                    p.setX(p1.getX());
                    p.setY(p1.getY());
                }
            }
            if(anim <= 0)
                anim = 0;
        }
        if(p != null) {
            p.setX(p.getX() + delta * SPEED);
            if(p.getX() > x + 45) {
                after.takeProduct(p);
                p = null;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(frames[(int)(anim/ANIM_TIME*7)], x, y);
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
        anim = ANIM_TIME;
    }

    @Override
    public boolean takeProduct(Product p) {
        if(this.p != null)
            return false;
        this.p = p;
        return true;
    }

    public void setAfter(ProductTaker after) {
        this.after = after;
    }

}
