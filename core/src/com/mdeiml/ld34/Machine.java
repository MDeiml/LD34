package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

public abstract class Machine implements ProductTaker {
    
    public abstract void update(float delta, ArrayList<FallingProduct> fallings);
    public abstract void render(SpriteBatch batch);
    public abstract int getNumberKeys();
    public abstract Key[] getKeys();
    public abstract void setKeys(Key[] keys);
    public abstract void activate(Key key);
    
}