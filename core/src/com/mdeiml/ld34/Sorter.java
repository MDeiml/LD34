package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Sorter extends Machine implements ProductTaker {
    
    private TextureRegion tex;
    private Product p;
    private int x;
    private int y;
    private ConveyorBelt right;
    private ConveyorBelt down;
    private Key[] keys;
    private boolean fall;
    
    public Sorter(int x, int y) {
        p = null;
        keys = null;
        fall = false;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(fall) {
            fallings.add(new FallingProduct(p, down.getY(), down));
        }
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
    public Key[] getKeys() {
        return keys;
    }

    @Override
    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    @Override
    public void activate(Key key) {
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
