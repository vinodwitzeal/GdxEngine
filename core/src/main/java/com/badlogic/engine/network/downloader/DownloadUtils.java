package com.badlogic.engine.network.downloader;

import com.badlogic.gdx.Net;

import java.math.BigInteger;

public class DownloadUtils {
    public static long getTotalBytes(Net.HttpResponse response){
        String lengthString=response.getHeader("content-length");
        return Long.parseLong(lengthString);
    }

    public static long bytesToKB(long totalBytes){
        return totalBytes/1024L;
    }

    public static long KBToMB(long totalKiloBytes){
        return totalKiloBytes/1024L;
    }

    public static long bytesToMB(long totalBytes){
        return KBToMB(bytesToKB(totalBytes));
    }
}
