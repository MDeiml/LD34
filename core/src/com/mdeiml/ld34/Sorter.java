package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sorter extends Machine implements ProductTaker {
    
    private TextureRegion tex;
    private Product p;
    private int x;
    private int y;
    private ConveyorBelt right;
    private ConveyorBelt down;
    private int[] keys;
    
    public Sorter(int x, int y) {
        p = null;
        keys = null;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {
        
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(tex, x, y);
    }

    @Override
    public int getNumberKeys() {
        return 2;
    }

    @Override
    public int[] getKeys() {
        return keys;
    }

    @Override
    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    @Override
    public void activate(int key) {
        if(keys[0] == key) {
            //TODO set position
            down.takeProduct(p);
        }else if(keys[1] == key) {
            //TODO set position
            right.takeProduct(p);
        }
    }

    @Override
    public boolean takeProduct(Product p) {
        if(this.p != null) {
            return false;
        }
        this.p = p;
        return true;
    }
    
}
