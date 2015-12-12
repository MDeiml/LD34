package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public class ConveyorBelt implements ProductTaker {
    
    private static final float SPEED = 16;
    
    private int x;
    private int y;
    private int width;
    private boolean dir;
    private ArrayList<Product> products;
    private ProductTaker after;
    private int fallHight;
    
    public ConveyorBelt(int x, int y, int width, boolean dir, ProductTaker after, int fallHight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.dir = dir;
        this.products = new ArrayList<Product>();
        this.fallHight = fallHight;
        this.after = after;
    }
    
    @Override
    public boolean takeProduct(Product p) {
        if(p.getX() < x || p.getX() > x + width) {
            return false;
        }
        products.add(p);
        return true;
    }
    
    public void update(float delta) {
        for(int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            int px = p.getX();
            px += (int)(delta * SPEED * (dir ? 1 : -1));
            p.setX(px);
            if(px > x + width || px < x) {
                p.setX(Math.max(Math.min(px, x + width), x));
                after.takeProduct(p);
            }
        }
    }
    
    public void render(SpriteBatch batch) {
        for(Product p : products) {
            p.render(batch);
        }
    }
    
}