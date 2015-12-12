package com.mdeiml.ld34;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class Exit extends Machine {

    public static TextureRegion tex;
    
    private int x;
    private int y;
    private boolean dir;
    
    public Exit(int x, int y, boolean dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    
    @Override
    public boolean takeProduct(Product p) {
        return true;
    }

    @Override
    public void update(float delta, ArrayList<FallingProduct> fallings) {
    }

    @Override
    public void render(SpriteBatch batch) {
        float w = (dir ? 1 : -1) * tex.getRegionWidth();
        int x1 = x + (dir ? 0 : tex.getRegionWidth());
        batch.draw(tex, x1, y, w, tex.getRegionHeight());
    }

    @Override
    public int getNumberKeys() {
        return 0;
    }

    @Override
    public Key[] getKeys() {
        return new Key[0];
    }

    @Override
    public void setKeys(Key[] keys) {
    }

    @Override
    public void activate(Key key) {
    }

}
