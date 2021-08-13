package com.badlogic.engine.network.multiplayer.listeners;

public interface ConnectionListener {
    void onConnected();

    void onConnectionError();

    void onDisconnected();
}
