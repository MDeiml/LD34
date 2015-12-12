package com.mdeiml.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Key {
    
    private int x;
    private int y;
    private TextureRegion up;
    private TextureRegion down;
    private boolean state;
    private int keycode;
    
    public Key(int code, int x, int y, TextureRegion up, TextureRegion down) {
        this.x = x;
        this.y = y;
        this.up = up;
        this.down = down;
        this.keycode = code;
        this.state = false;
    }
    
    public void update() {
        state = Gdx.input.isKeyJustPressed(keycode);
    }
    
    public void render(SpriteBatch batch) {
        batch.draw(state ? down : up, x, y);
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
}
