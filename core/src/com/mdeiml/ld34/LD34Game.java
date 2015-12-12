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
    public void create() {
        batch = new SpriteBatch();
        assetMngr = new AssetManager();
        assetMngr.load("skin.atlas", TextureAtlas.class);
        assetMngr.load("Vordergrund.png", Texture.class);
        assetMngr.load("background.png", Texture.class);
        assetMngr.load("ascii.fnt", BitmapFont.class);
        assetMngr.load("cb_left.png", Texture.class);
        assetMngr.load("cb_middle.png", Texture.class);
        assetMngr.load("cb_right.png", Texture.class);
        assetMngr.load("board.png", Texture.class);
        assetMngr.load("product1.png", Texture.class);
        assetMngr.load("sorter.png", Texture.class);
        assetMngr.load("exit.png", Texture.class);
        for(int i = 1; i <= 3; i++) {
            assetMngr.load("button"+i+"_up.png", Texture.class);
            assetMngr.load("button"+i+"_down.png", Texture.class);
        }
        assetMngr.finishLoadingAsset("skin.atlas");
        assetMngr.load("skin.json", Skin.class, new SkinLoader.SkinParameter("skin.atlas"));
        assetMngr.finishLoading();
        ConveyorBelt.left = new TextureRegion(assetMngr.get("cb_left.png", Texture.class));
        ConveyorBelt.middle = new TextureRegion(assetMngr.get("cb_middle.png", Texture.class));
        ConveyorBelt.right = new TextureRegion(assetMngr.get("cb_right.png", Texture.class));
        Sorter.tex = new TextureRegion(assetMngr.get("sorter.png", Texture.class));
        Exit.tex = new TextureRegion(assetMngr.get("exit.png", Texture.class));
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
