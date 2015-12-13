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
    private boolean leaver;
    
    public Key(int code, int x, int y, TextureRegion up, TextureRegion down) {
        this.x = x;
        this.y = y;
        this.up = up;
        this.down = down;
        this.keycode = code;
        this.state = false;
        this.leaver = false;
    }
    
    public void update() {
        if(leaver) {
            if(Gdx.input.isKeyJustPressed(keycode))
                state = !state;
        }else {
            state = Gdx.input.isKeyPressed(keycode);
        }
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getWidth() {
        return up.getRegionWidth();
    }

    public int getKeycode() {
        return keycode;
    }

    public void setLeaver(boolean leaver) {
        this.leaver = leaver;
    }
    
}
