package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Oven extends Machine implements ProductTaker {

    private static final float COOK_TIME = 3;
    public static TextureRegion on;
    public static TextureRegion off;
    public static TextureRegion cook;
    
    private Product p;
    private int x;
    private int y;
    private Key[] keys;
    private boolean hot;
    private float cookTime;
    private ConveyorBelt after;
    
    public Oven(int x, int y) {
        this.x = x;
        this.y = y;
        this.p = null;
        this.hot = false;
        cookTime = 0;
    }
    
    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(p != null) {
            if(hot) {
                cookTime -= delta;
                if(cookTime < 0) {
                    if(p.getType() == 3) {
                        Product p1 = new Product(4, new TextureRegion(LD34Game.a.get("p4.png", Texture.class)));
                        p1.setX(after.getX());
                        p1.setY(after.getY()+8);
                        after.takeProduct(p1);
                    }
                    p = null;
                }
            }else {
                p.setX(after.getX());
                p.setY(after.getY()+8);
                after.takeProduct(p);
                p = null;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if(hot) {
            if(p == null) {
                batch.draw(on, x, y);
            }else {
                batch.draw(cook, x, y);
            }
        }else {
            batch.draw(off, x, y);
        }
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
        if(key == keys[0]) {
            hot = !hot;
        }
    }

    @Override
    public boolean takeProduct(Product p) {
        if(this.p != null)
            return false;
        this.p = p;
        cookTime = COOK_TIME;
        return true;
    }

    public void setAfter(ConveyorBelt after) {
        this.after = after;
    }

}
