package com.mdeiml.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class PlayScreen implements Screen {

    private static final int FPS = 60;
    private static final int[] KEYS = {Keys.A,
                                       Keys.B,
                                       Keys.C,
                                       Keys.D,
                                       Keys.E,
                                       Keys.F,
                                       Keys.G,
                                       Keys.H,
                                       Keys.I,
                                       Keys.J,
                                       Keys.K,
                                       Keys.L,
                                       Keys.M,
                                       Keys.N,
                                       Keys.O,
                                       Keys.P,
                                       Keys.Q,
                                       Keys.R,
                                       Keys.S,
                                       Keys.T,
                                       Keys.U,
                                       Keys.V,
                                       Keys.W,
                                       Keys.X,
                                       Keys.Y,
                                       Keys.Z };
    
    private float unprocessed;
    private ArrayList<Integer> buttons;
    private ArrayList<Machine> machines;
    private ArrayList<ConveyorBelt> conveyors;
    private LD34Game game;
    private OrthographicCamera cam;
    private ArrayList<FallingProduct> fallings;
    
    public PlayScreen(LD34Game game) {
        this.game = game;
        unprocessed = 0;
        buttons = new ArrayList<Integer>();
        machines = new ArrayList<Machine>();
        conveyors = new ArrayList<ConveyorBelt>();
        fallings = new ArrayList<FallingProduct>();
    }
    
    public int addButton() {
        int left = KEYS.length - buttons.size();
        int rand = (int)(left * Math.random());
        int i = 0;
        while(i <= rand) {
            if(!buttons.contains(KEYS[i]))
                i++;
        }
        i--;
        int k = KEYS[i];
        buttons.add(k);
        return k;
    }
    
    public void addMachine(Machine m) {
        int nKeys = m.getNumberKeys();
        int[] mKeys = new int[nKeys];
        for(int i = 0; i < nKeys; i++) {
            mKeys[i] = addButton();
        }
        m.setKeys(mKeys);
        machines.add(m);
    }
    
    @Override
    public void show() {
        
    }
    
    public void update(float delta) {
        for(Machine m : machines) {
            int[] ks = m.getKeys();
            for(int k : ks) {
                if(Gdx.input.isKeyJustPressed(k)) {
                    m.activate(k);
                }
            }
        }
        for(FallingProduct p : fallings) {
            p.update(delta);
        }
        for(ConveyorBelt cb : conveyors) {
            cb.update(delta, fallings);
        }
        for(Machine m : machines) {
            m.update(delta, fallings);
        }
    }

    @Override
    public void render(float delta) {
        unprocessed += delta;
        
        while(unprocessed > 1f/FPS) {
            update(1f/FPS);
            unprocessed -= 1f/FPS;
        }
        
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        for(FallingProduct p : fallings) {
            p.render(game.batch);
        }
        for(ConveyorBelt cb : conveyors) {
            cb.render(game.batch);
        }
        for(Machine m : machines) {
            m.render(game.batch);
        }
        game.batch.draw(game.assetMngr.get("Vordergrund.png", Texture.class),0,0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        cam = new OrthographicCamera(width/2, height/2);
        cam.translate(width/4, height/4);
        cam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    
}