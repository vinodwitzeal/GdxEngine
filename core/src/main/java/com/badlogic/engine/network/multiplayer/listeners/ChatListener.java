package com.badlogic.engine.network.multiplayer.listeners;

public interface ChatListener {
    void onChatSent(int result, String reason);

    void onChatReceived(String sender, String chat);

    void onPrivateChatReceived(String sender, String chat);
}
