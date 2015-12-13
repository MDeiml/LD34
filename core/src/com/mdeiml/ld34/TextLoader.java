package com.mdeiml.ld34;

import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class TextLoader extends AsynchronousAssetLoader<String, TextLoader.TextParameter> {

    public TextLoader(FileHandleResolver resolver) {
        super(resolver);
    }
    
    private String s;
    
    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {
        this.s = null;
        this.s = file.readString();
    }
    
    @Override
    public String loadSync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {
        String s = this.s;
        this.s = null;
        return s;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextLoader.TextParameter parameter) {
        return null;
    }
    
    public static class TextParameter extends AssetLoaderParameters<String> {
        
    }

}
