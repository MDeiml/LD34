package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FallingProduct {

    private static float SPEED = 5;
    
    private int destHight;
    private Product p;
    private ProductTaker after;
    
    public FallingProduct(Product p, int destHeight, ProductTaker after) {
        this.p = p;
        this.destHight = destHeight;
        this.after = after;
    }
    
    public void update(float delta) {
        if(p.getY() < destHight) {
            p.setY(destHight);
            after.takeProduct(p);
        }
        p.setY(p.getY() - (int)(SPEED * delta));
    }
    
    public void render(SpriteBatch batch) {
        p.render(batch);
    }

}
