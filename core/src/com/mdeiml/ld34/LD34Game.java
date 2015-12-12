package com.mdeiml.ld34;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LD34Game extends Game {
	
    AssetManager assetMngr;
    SpriteBatch batch;
    
    @Override
    public void create () {
        batch = new SpriteBatch();
        assetMngr = new AssetManager();
        assetMngr.load("Vordergrund.png", Texture.class);
        assetMngr.finishLoading();
        setScreen(new PlayScreen(this));
    }
    
    @Override
    public void dispose() {
        assetMngr.dispose();
        batch.dispose();
    }
}
