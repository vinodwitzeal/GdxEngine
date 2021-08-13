package com.badlogic.engine.network.downloader;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageDownloader {
    private FileDownloader fileDownloader;
    public ImageDownloader(FileDownloader fileDownloader){
        this.fileDownloader=fileDownloader;
    }

    public void download(String url, Image image){
    }
}
