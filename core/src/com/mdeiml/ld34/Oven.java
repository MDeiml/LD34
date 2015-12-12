package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Oven extends Machine implements ProductTaker {

    private static final float COOK_TIME = 3;
    
    private TextureRegion tex;
    private Product p;
    private int x;
    private int y;
    private Key[] keys;
    private boolean hot;
    private float cookTime;
    private ConveyorBelt after;
    
    public Oven(int x, int y, ConveyorBelt after) {
        this.x = x;
        this.y = y;
        this.p = null;
        this.hot = false;
        this.after = after;
        cookTime = 0;
    }
    
    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(p != null) {
            if(hot) {
                cookTime -= delta;
                if(cookTime < 0) {
                    after.takeProduct(p);
                }
            }else {
                after.takeProduct(p);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(tex, x, y);
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

}
