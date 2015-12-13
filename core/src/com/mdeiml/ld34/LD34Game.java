package com.mdeiml.ld34;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LD34Game extends Game {
    
    public static AssetManager a;
	
    AssetManager assetMngr;
    SpriteBatch batch;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        assetMngr = new AssetManager();
        a = assetMngr;
        
        assetMngr.setLoader(String.class, new TextLoader(new InternalFileHandleResolver()));
        
        assetMngr.load("skin.atlas", TextureAtlas.class);
        assetMngr.load("Vordergrund.png", Texture.class);
        assetMngr.load("Background.png", Texture.class);
        assetMngr.load("ascii.fnt", BitmapFont.class);
        assetMngr.load("cb_left.png", Texture.class);
        assetMngr.load("cb_middle.png", Texture.class);
        assetMngr.load("cb_right.png", Texture.class);
        assetMngr.load("board.png", Texture.class);
        for(int i = 1; i <= 6; i++) {
            assetMngr.load("p"+i+".png", Texture.class);
        }
        assetMngr.load("sorter.png", Texture.class);
        assetMngr.load("exit.png", Texture.class);
        assetMngr.load("input.png", Texture.class);
        assetMngr.load("oven.png", Texture.class);
        assetMngr.load("press.png", Texture.class);
        assetMngr.load("mixer.png", Texture.class);
        assetMngr.load("Guillotine.png", Texture.class);
        assetMngr.load("trapdoor.png", Texture.class);
        assetMngr.load("packer.png", Texture.class);
        assetMngr.load("ambient.wav", Sound.class);
        for(int i = 1; i <= 7; i++) {
            assetMngr.load("button"+i+"_up.png", Texture.class);
            assetMngr.load("button"+i+"_down.png", Texture.class);
        }
        assetMngr.finishLoadingAsset("skin.atlas");
        assetMngr.load("skin.json", Skin.class, new SkinLoader.SkinParameter("skin.atlas"));
        assetMngr.finishLoading();
        ConveyorBelt.left = new TextureRegion(assetMngr.get("cb_left.png", Texture.class));
        ConveyorBelt.middle = new TextureRegion(assetMngr.get("cb_middle.png", Texture.class));
        ConveyorBelt.right = new TextureRegion(assetMngr.get("cb_right.png", Texture.class));
        Texture t = assetMngr.get("sorter.png", Texture.class);
        Sorter.on = new TextureRegion(t, 0, 0, 32, 30);
        Sorter.off = new TextureRegion(t, 32, 0, 32, 30);
        Exit.tex = new TextureRegion(assetMngr.get("exit.png", Texture.class));
        t = assetMngr.get("oven.png", Texture.class);
        Oven.on = new TextureRegion(t, 0, 0, 45, 56);
        Oven.off = new TextureRegion(t, 45, 0, 45, 56);
        Oven.cook = new TextureRegion(t, 90, 0, 45, 56);
        Packer.tex = new TextureRegion(assetMngr.get("packer.png", Texture.class));
        t = assetMngr.get("press.png", Texture.class);
        TextureRegion[] anim = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            anim[i] = new TextureRegion(t, i*30, 0, 30, 47);
        }
        Stomper.frames = anim;
        t = assetMngr.get("mixer.png", Texture.class);
        anim = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            anim[i] = new TextureRegion(t, i*66, 0, 66, 66);
        }
        Mixer.frames = anim;
        t = assetMngr.get("Guillotine.png", Texture.class);
        anim = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            anim[i] = new TextureRegion(t, i*6, 0, 6, 37);
        }
        Hacker.blade = anim;
        t = assetMngr.get("trapdoor.png", Texture.class);
        anim = new TextureRegion[7];
        for(int i = 0; i < 7; i++) {
            anim[i] = new TextureRegion(t, i*38, 0, 38, 21);
        }
        Hacker.trapdoor = anim;
        setScreen(new Menu(this));
        assetMngr.get("ambient.wav", Sound.class).loop();
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
