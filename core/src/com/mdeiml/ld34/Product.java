package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Product {
    
    private TextureRegion tex;
    private int type;
    private float x;
    private float y;
    
    public Product(int type, TextureRegion tex) {
        this.type = type;
        this.tex = tex;
    }
    
    public void render(SpriteBatch batch) {
        batch.draw(tex, x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
    
}