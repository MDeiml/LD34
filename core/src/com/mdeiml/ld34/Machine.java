package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Machine {
    
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract int getNumberKeys();
    public abstract int[] getKeys();
    public abstract void setKeys(int[] keys);
    public abstract void activate(int key);
    
}