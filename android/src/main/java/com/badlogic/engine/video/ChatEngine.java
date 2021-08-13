package com.badlogic.engine.video;

import com.badlogic.engine.AndroidLauncher;
import com.badlogic.engine.network.multiplayer.SocketClient;

import java.net.URISyntaxException;

public class ChatEngine {
    private AndroidLauncher launcher;
    private SocketClient socketClient;
    public ChatEngine(AndroidLauncher launcher){
        this.launcher=launcher;
        try {
            this.socketClient=new SocketClient("http://192.168.1.28:3000","chat"){

            };
        }catch (URISyntaxException e){

        }

    }


    public void startSignal(){
        if (socketClient==null)return;
        this.socketClient.connect("1234","{}");
    }
}
