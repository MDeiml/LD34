package com.mdeiml.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Menu implements Screen {

    private Stage stage;
    private LD34Game game;
    
    public Menu(final LD34Game game) {
        this.game = game;
        Skin skin = game.assetMngr.get("skin.json", Skin.class);
        stage = new Stage(new FitViewport(384, 288));
        Table layout = new Table(skin);
        layout.setFillParent(true);
        layout.add("INSERT TITLE HERE");
        layout.row();
        TextButton play = new TextButton("Play", skin);
        play.setDisabled(true);
        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                dispose();
                return true;
            }
        });
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
        game.batch.begin();
        game.batch.draw(game.assetMngr.get("Background.png", Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
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
