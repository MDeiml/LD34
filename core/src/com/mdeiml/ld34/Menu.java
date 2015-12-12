package com.mdeiml.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Menu implements Screen {

    private Stage stage;
    private LD34Game game;
    
    public Menu(LD34Game game) {
        this.game = game;
        Skin skin = game.assetMngr.get("skin.json", Skin.class);
        stage = new Stage(new FitViewport(384, 288));
        Table layout = new Table(skin);
        layout.setFillParent(true);
        TextButton play = new TextButton("Play", skin);
        layout.add(play);
        layout.row();
        stage.addActor(layout);
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
