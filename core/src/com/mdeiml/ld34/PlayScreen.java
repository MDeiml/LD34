package com.mdeiml.ld34;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
        
        loadLevel("lvl5.txt");
    }
    
    public void loadLevel(String filename) {
        game.assetMngr.load(filename, String.class);
        game.assetMngr.finishLoadingAsset(filename);
        String level = game.assetMngr.get(filename, String.class);
        level = level.replaceAll("\r", "");
        String[] lines = level.split("\n");
        
        int sHeight = Gdx.graphics.getHeight() / 2;
        
        String[] inputTokens = lines[0].split(" ");
        inputX = Integer.parseInt(inputTokens[0]);
        inputY = sHeight - Integer.parseInt(inputTokens[1]);
        
        ArrayList<ProductTaker> objects = new ArrayList<ProductTaker>();
        for(int i = 1; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
            switch(tokens[0].charAt(0)) {
                case 'C':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    int w = Integer.parseInt(tokens[3]);
                    boolean dir = Boolean.parseBoolean(tokens[4]);
                    int fh = Integer.parseInt(tokens[5]);
                    ConveyorBelt cb = new ConveyorBelt(x, y, w, dir, fh);
                    objects.add(cb);
                    break;
                }
                case 'S':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    Sorter s = new Sorter(x, y);
                    objects.add(s);
                    break;
                }
                case 'E':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    boolean dir = Boolean.parseBoolean(tokens[3]);
                    Exit e = new Exit(x, y, dir);
                    objects.add(e);
                    break;
                }
                case 'O':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    Oven o = new Oven(x, y);
                    objects.add(o);
                    break;
                }
                case 'T':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    Stomper s = new Stomper(x, y);
                    objects.add(s);
                    break;
                }
                case 'M':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    Mixer s = new Mixer(x, y);
                    objects.add(s);
                    break;
                }
                case 'H':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    Hacker h = new Hacker(x, y);
                    objects.add(h);
                    break;
                }
                case 'P':{
                    int x = Integer.parseInt(tokens[1]);
                    int y = sHeight - Integer.parseInt(tokens[2]);
                    Packer p = new Packer(x, y);
                    objects.add(p);
                    break;
                }
            }
        }
        
        for(int i = 1; i < lines.length; i++) {
            String[] tokens = lines[i].split(" ");
            switch(tokens[0].charAt(0)) {
                case 'C':{
                    ConveyorBelt cb = (ConveyorBelt)objects.get(i-1);
                    int after = Integer.parseInt(tokens[6]);
                    cb.setAfter(objects.get(after));
                    conveyors.add(cb);
                    break;
                }
                case 'S':{
                    Sorter s = (Sorter)objects.get(i-1);
                    int down = Integer.parseInt(tokens[3]);
                    int right = Integer.parseInt(tokens[4]);
                    s.setDown((ConveyorBelt)objects.get(down));
                    s.setRight((ConveyorBelt)objects.get(right));
                    Key[] k = addMachine(s);
                    k[0].setX(192-13);
                    k[0].setY(30);
                    k[0].setButton(game.assetMngr, 1);
                    k[1].setX(192);
                    k[1].setY(30);
                    k[1].setButton(game.assetMngr, 2);
                    break;
                }
                case 'E':{
                    Exit e = (Exit)objects.get(i-1);
                    addMachine(e);
                    break;
                }
                case 'O':{
                    Oven o = (Oven)objects.get(i-1);
                    int after = Integer.parseInt(tokens[3]);
                    o.setAfter((ConveyorBelt)objects.get(after));
                    Key[] k = addMachine(o);
                    k[0].setX(140);
                    k[0].setY(28);
                    k[0].setLeaver(true);
                    k[0].setButton(game.assetMngr, 3);
                    break;
                }
                case 'T':{
                    Stomper s = (Stomper)objects.get(i-1);
                    int after = Integer.parseInt(tokens[3]);
                    s.setAfter(objects.get(after));
                    Key[] k = addMachine(s);
                    k[0].setX(220);
                    k[0].setY(27);
                    k[0].setButton(game.assetMngr, 4);
                    break;
                }
                case 'M':{
                    Mixer m = (Mixer)objects.get(i-1);
                    int after = Integer.parseInt(tokens[3]);
                    m.setAfter((ConveyorBelt)objects.get(after));
                    Key[] k = addMachine(m);
                    k[0].setX(260);
                    k[0].setY(27);
                    k[0].setButton(game.assetMngr, 5);
                    break;
                }
                case 'H':{
                    Hacker h = (Hacker)objects.get(i-1);
                    int left = Integer.parseInt(tokens[3]);
                    h.setLeft((ConveyorBelt)objects.get(left));
                    int right = Integer.parseInt(tokens[4]);
                    h.setRight((ConveyorBelt)objects.get(right));
                    Key[] k = addMachine(h);
                    k[0].setX(40);
                    k[0].setY(37);
                    k[0].setButton(game.assetMngr, 6);
                    break;
                }
                case 'P':{
                    Packer p = (Packer)objects.get(i-1);
                    int after = Integer.parseInt(tokens[3]);
                    p.setAfter((ConveyorBelt)objects.get(after));
                    Key[] k = addMachine(p);
                    k[0].setX(240);
                    k[0].setY(40);
                    k[0].setButton(game.assetMngr, 7);
                    break;
                }
            }
        }
        
        input = (ConveyorBelt)objects.get(Integer.parseInt(inputTokens[2]));
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
            mKeys[i] = new Key(addButton(), 0, 0);
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
        game.batch.draw(game.assetMngr.get("Background.png", Texture.class),0,0);
        for(FallingProduct p : fallings) {
            p.render(game.batch);
        }
        for(ConveyorBelt cb : conveyors) {
            cb.render(game.batch);
        }
        for(Machine m : machines) {
            m.render(game.batch);
        }
        game.batch.draw(game.assetMngr.get("input.png", Texture.class), inputX-4, inputY-14);
        game.batch.draw(game.assetMngr.get("board.png", Texture.class), 0, 0);
        BitmapFont font = game.assetMngr.get("ascii.fnt", BitmapFont.class);
        for(Machine m : machines) {
            Key[] ks = m.getKeys();
            for(Key k : ks) {
                k.render(game.batch);
                int x1 = k.getX();
                int y1 = k.getY();
                int w = k.getWidth();
                x1 = x1 + w / 2 - 2;
                y1 = y1 - 9;
                font.draw(game.batch, Keys.toString(k.getKeycode()), x1, y1);
            }
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