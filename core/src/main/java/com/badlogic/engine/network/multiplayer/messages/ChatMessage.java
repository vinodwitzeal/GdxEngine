package com.badlogic.engine.network.multiplayer.messages;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class ChatMessage {
    private String sender;
    private String chat;
    public ChatMessage(Object object){
        JsonValue value=new JsonReader().parse((String)object);
        sender=value.getString("sender");
        chat=value.getString("chat","");
    }

    public String getSender(){
        return this.sender;
    }

    public String getChat(){
        return this.chat;
    }
}
