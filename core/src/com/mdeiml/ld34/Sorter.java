package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Sorter extends Machine implements ProductTaker {
    
    public static TextureRegion off;
    public static TextureRegion on;
    
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
            fall = false;
            p = null;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if(p != null)
            batch.draw(on, x, y);
        else
            batch.draw(off, x, y);
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
        if(p == null)
            return;
        if(keys[0] == key) {
            fall = true;
            p.setX(x+8);
        }else if(keys[1] == key) {
            p.setX(right.getX());
            right.takeProduct(p);
            p = null;
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

    public void setRight(ConveyorBelt right) {
        this.right = right;
    }

    public void setDown(ConveyorBelt down) {
        this.down = down;
    }
    
}
