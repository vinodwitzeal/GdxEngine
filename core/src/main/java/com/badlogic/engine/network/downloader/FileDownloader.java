package com.badlogic.engine.network.downloader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDownloader {
    private final Map<String, FileDownloadCallback> fileToDownloads;
    private FileCache fileCache;

    public FileDownloader() {
        fileToDownloads = Collections.synchronizedMap(new HashMap<String, FileDownloadCallback>());
        fileCache = new FileCache();
    }

    public void download(String url, FileDownloadListener listener) {
        download(url, null, listener);
    }

    public void download(String url, String name, FileDownloadListener listener) {
        if (url == null || url.isEmpty()) return;
        if (name == null || name.isEmpty()) {
            name = url.hashCode() + "";
        }
        if (fileToDownloads.containsKey(name)){
            fileToDownloads.get(name).setListener(listener);
        }else {
            fileToDownloads.put(name, new FileDownloadCallback(listener));
            startDownloading(url, name);
        }
    }

    private void startDownloading(String url, String name) {
        FileHandle fileHandle = fileCache.getFile(name);
        if (fileHandle.exists()) {
            if (fileHandle.file().canExecute()) {
                fileToDownloads.get(name).setDownloadComplete(fileHandle);
            } else {
                fileHandle.delete();
            }
        }
        Net.HttpRequest request = new HttpRequestBuilder()
                .method(Net.HttpMethods.GET)
                .url(url)
                .followRedirects(true)
                .timeout(5000)
                .build();

        final FileDownloadCallback finalCallback = fileToDownloads.get(name);
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                if (httpResponse.getStatus().getStatusCode() == HttpStatus.SC_ACCEPTED) {
                    long totalBytes = DownloadUtils.getTotalBytes(httpResponse);
                    long totalMB = DownloadUtils.bytesToMB(totalBytes);
                    int bufferSize = 4096;
                    byte[] buffer = new byte[bufferSize];
                    OutputStream os = fileHandle.write(false);
                    InputStream inputStream = httpResponse.getResultAsStream();
                    try {
                        int readBytes = 0;
                        long downloadedBytes = 0;
                        while ((readBytes = inputStream.read(buffer, 0, bufferSize)) != -1) {
                            os.write(buffer, 0, readBytes);
                            downloadedBytes += readBytes;
                            long downloadedMB = DownloadUtils.bytesToMB(downloadedBytes);
                            float percent = (float) (downloadedBytes / totalBytes);
                            fileHandle.file();
                            if (downloadedMB <= 0) {
                                float downloadedKB = DownloadUtils.bytesToKB(downloadedBytes);
                                finalCallback.setProgress(fileHandle.readBytes(),percent, downloadedKB + "KB", totalMB + "MB");
                            } else {
                                finalCallback.setProgress(fileHandle.readBytes(),percent, downloadedMB + "MB", totalMB + "MB");
                            }
                        }
                        inputStream.close();
                        os.close();
                        finalCallback.setDownloadComplete(fileHandle);
                    } catch (IOException e) {
                        finalCallback.setDownloadError(e.getMessage());
                        if (fileHandle.exists()) {
                            fileHandle.delete();
                        }
                    }
                }

            }

            @Override
            public void failed(Throwable t) {
                finalCallback.setDownloadError(t.getMessage());
                if (fileHandle.exists()){
                    fileHandle.delete();
                }
            }

            @Override
            public void cancelled() {
                finalCallback.setDownloadFailed("Cancelled");
                if (fileHandle.exists()){
                    fileHandle.delete();
                }
            }
        });
    }

    private class FileDownloadCallback {
        private FileDownloadListener listener;
        public FileDownloadCallback(FileDownloadListener listener){
            this.listener=listener;
        }

        public void setListener(FileDownloadListener listener){
            this.listener=listener;
        }
        public void setProgress(byte[] bytes,float percentage, String downloaded, String maxSize){
            listener.onProgress(bytes,percentage,downloaded,maxSize);
        }

        public void setDownloadComplete(FileHandle fileHandle){
            listener.onDownloadComplete(fileHandle);
        }

        public void setDownloadError(String reason){
            listener.onDownloadError(reason);
        }

        public void setDownloadFailed(String reason){
            listener.onDownloadFailed(reason);
        }
    }

    public interface FileDownloadListener{
        void onProgress(byte[]  bytes,float percentage, String downloaded, String maxSize);

        void onDownloadComplete(FileHandle fileHandle);

        void onDownloadError(String reason);

        void onDownloadFailed(String reason);
    }

    public static class FileToDownload {
        private String url, name;

        public FileToDownload(String url, String name) {
            this.url = url;
            this.name = name;
        }
    }
}
