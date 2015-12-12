package com.mdeiml.ld34;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LD34Game extends Game {
	
    AssetManager assetMngr;
    SpriteBatch batch;
    
    @Override
    public void create () {
        batch = new SpriteBatch();
        assetMngr = new AssetManager();
        assetMngr.load("skin.atlas", TextureAtlas.class);
        assetMngr.load("Vordergrund.png", Texture.class);
        assetMngr.load("background.png", Texture.class);
        assetMngr.load("ascii.fnt", BitmapFont.class);
        assetMngr.finishLoadingAsset("skin.atlas");
        assetMngr.load("skin.json", Skin.class, new SkinLoader.SkinParameter("skin.atlas"));
        assetMngr.finishLoading();
        setScreen(new PlayScreen(this));
    }
    
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }
    
    @Override
    public void dispose() {
        assetMngr.dispose();
        batch.dispose();
    }
}
