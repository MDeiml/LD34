package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class ConveyorBelt implements ProductTaker {
    
    private static final float SPEED = 16;
    public static TextureRegion middle;
    public static TextureRegion left;
    public static TextureRegion right;
    
    private int x;
    private int y;
    private int width;
    private boolean dir;
    private ArrayList<Product> products;
    private ProductTaker after;
    private int fallHeight;
    
    public ConveyorBelt(int x, int y, int width, boolean dir, int fallHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.dir = dir;
        this.products = new ArrayList<Product>();
        this.fallHeight = fallHeight;
        this.after = null;
    }
    
    @Override
    public boolean takeProduct(Product p) {
        System.out.println(x);
        if(p.getX() < x || p.getX() > x + width) {
            return false;
        }
        products.add(p);
        return true;
    }
    
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        for(int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            float px = p.getX();
            px += delta * SPEED * (dir ? 1 : -1);
            p.setX(px);
            if(px > x + width || px < x) {
                p.setX(Math.max(Math.min(px, x + width), x));
                if(fallHeight > 0) {
                    fallings.add(new FallingProduct(p, fallHeight, after));
                }else {
                    after.takeProduct(p);
                }
                products.remove(i);
                i--;
            }
        }
    }
    
    public void render(SpriteBatch batch) {
        for(int x1 = x; x1 < x + width - 16; x1 += 16) {
            TextureRegion t;
            if(x1 == x) {
                t = left;
            }else {
                t = middle;
            }
            batch.draw(t, x1, y);
        }
        batch.draw(right, x + width - 16, y);
        
        for(Product p : products) {
            p.render(batch);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setAfter(ProductTaker after) {
        this.after = after;
    }
    
}