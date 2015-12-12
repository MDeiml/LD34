package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FallingProduct {

    private static float SPEED = 32;
    
    private int destHight;
    private Product p;
    private ProductTaker after;
    
    public FallingProduct(Product p, int destHeight, ProductTaker after) {
        this.p = p;
        this.destHight = destHeight;
        this.after = after;
    }
    
    public boolean update(float delta) {
        if(p.getY() <= destHight+8) {
            p.setY(destHight+8);
            after.takeProduct(p);
            return true;
        }
        p.setY(p.getY() - SPEED * delta);
        return false;
    }
    
    public void render(SpriteBatch batch) {
        p.render(batch);
    }

}
