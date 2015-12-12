package com.mdeiml.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private static final float INPUT_TIME = 3;
    
    private float unprocessed;
    private ArrayList<Integer> buttons;
    private ArrayList<Machine> machines;
    private ArrayList<ConveyorBelt> conveyors;
    private LD34Game game;
    private OrthographicCamera cam;
    private ArrayList<FallingProduct> fallings;
    private int inputX;
    private int inputY;
    private ConveyorBelt input;
    private float inputTime;
    
    public PlayScreen(LD34Game game) {
        this.game = game;
        unprocessed = 0;
        buttons = new ArrayList<Integer>();
        machines = new ArrayList<Machine>();
        conveyors = new ArrayList<ConveyorBelt>();
        fallings = new ArrayList<FallingProduct>();
        inputTime = 0;
        
        level1();
    }
    
    public void level1() {
        inputX = 6;
        inputY = 288;
        ConveyorBelt cb = new ConveyorBelt(0, 256, 64, true, 0);
        input = cb;
        Sorter sorter = new Sorter(47, 249);
        cb.setAfter(sorter);
        conveyors.add(cb);
        Key[] k = addMachine(sorter);
        k[0].setX(192-13);
        k[0].setY(30);
        k[1].setX(192);
        k[1].setY(30);
        System.out.println(Keys.toString(k[0].getKeycode()));
        System.out.println(Keys.toString(k[1].getKeycode()));
        cb = new ConveyorBelt(0, 181, 7*16+8, false, 0);
        Exit e = new Exit(0, 180, true);
        machines.add(e);
        cb.setAfter(e);
        conveyors.add(cb);
        sorter.setDown(cb);
        cb = new ConveyorBelt(72, 256, 384-72, true, 0);
        e = new Exit(384-14, 255, false);
        machines.add(e);
        cb.setAfter(e);
        conveyors.add(cb);
        sorter.setRight(cb);
    }
    
    public int addButton() {
        int left = KEYS.length - buttons.size();
        int rand = (int)(left * Math.random());
        int i = 0;
        int j = 0;
        while(i <= rand) {
            if(!buttons.contains(KEYS[j]))
                i++;
            j++;
        }
        j--;
        int k = KEYS[j];
        buttons.add(k);
        return k;
    }
    
    public Key[] addMachine(Machine m) {
        int nKeys = m.getNumberKeys();
        Key[] mKeys = new Key[nKeys];
        for(int i = 0; i < nKeys; i++) {
            int b = buttons.size()+1;
            TextureRegion up = new TextureRegion(game.assetMngr.get("button"+b+"_up.png", Texture.class));
            TextureRegion down = new TextureRegion(game.assetMngr.get("button"+b+"_down.png", Texture.class));
            mKeys[i] = new Key(addButton(), 0, 0, up, down);
        }
        m.setKeys(mKeys);
        machines.add(m);
        return mKeys;
    }
    
    @Override
    public void show() {
        
    }
    
    public void update(float delta) {
        inputTime += delta;
        if(inputTime >= INPUT_TIME) {
            Product p = new Product(0, new TextureRegion(game.assetMngr.get("product1.png", Texture.class)));
            p.setX(inputX);
            p.setY(inputY);
            fallings.add(new FallingProduct(p, input.getY(), input));
            inputTime = 0;
        }
        for(Machine m : machines) {
            Key[] ks = m.getKeys();
            for(Key k : ks) {
                k.update();
                if(Gdx.input.isKeyJustPressed(k.getKeycode())) {
                    m.activate(k);
                }
            }
        }
        for(int i = 0; i < fallings.size(); i++) {
            if(fallings.get(i).update(delta)) {
                fallings.remove(i);
                i--;
            }
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
        game.batch.draw(game.assetMngr.get("background.png", Texture.class),0,0);
        game.batch.draw(game.assetMngr.get("board.png", Texture.class), 0, 0);
        for(Machine m : machines) {
            Key[] ks = m.getKeys();
            for(Key k : ks) {
                k.render(game.batch);
            }
        }
        for(FallingProduct p : fallings) {
            p.render(game.batch);
        }
        for(ConveyorBelt cb : conveyors) {
            cb.render(game.batch);
        }
        for(Machine m : machines) {
            m.render(game.batch);
        }
        //game.batch.draw(game.assetMngr.get("Vordergrund.png", Texture.class),0,0);
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