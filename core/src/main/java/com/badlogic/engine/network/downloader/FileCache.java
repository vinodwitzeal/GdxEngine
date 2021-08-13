package com.badlogic.engine.network.downloader;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileCache {
    private FileHandle cacheDir;
    public FileCache(){
        if (Gdx.app.getType()== Application.ApplicationType.Android && Gdx.files.isLocalStorageAvailable()){
            cacheDir=Gdx.files.local("/cache");
        }else {
            cacheDir=Gdx.files.external("/cache");
        }

        if (!cacheDir.exists()){
            cacheDir.mkdirs();
        }
    }

    public FileHandle getFile(String name){
        return cacheDir.child(name);
    }
}
