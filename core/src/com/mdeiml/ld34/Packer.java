package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Packer extends Machine {
    
    private static final float TIME = 2;
    public static TextureRegion tex;
    
    private int x;
    private int y;
    private ConveyorBelt after;
    private Product p;
    private Key[] keys;
    private boolean on;
    private float timer;
    
    public Packer(int x, int y) {
        this.x = x;
        this.y = y;
        this.timer = 0;
    }

    public void setAfter(ConveyorBelt after) {
        this.after = after;
    }

    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
        if(!on) {
            timer = 0;
        }
        if(timer > 0) {
            timer -= delta;
            if(timer <= 0) {
                timer = 0;
                if(p.getType() == 6) {
                    Product p1 = new Product(1, new TextureRegion(LD34Game.a.get("p1.png", Texture.class)));
                    p1.setX(after.getX());
                    p1.setY(p.getY());
                    after.takeProduct(p1);
                }
                p = null;
            }
        }else {
            if(p != null) {
                if(on) {
                    timer = TIME;
                }else {
                    p.setX(after.getX());
                    after.takeProduct(p);
                    p = null;
                }
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
        on = !on;
    }

    @Override
    public boolean takeProduct(Product p) {
        if(this.p != null)
            return false;
        this.p = p;
        return true;
    }
    
}
