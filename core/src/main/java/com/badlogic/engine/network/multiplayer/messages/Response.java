package com.badlogic.engine.network.multiplayer.messages;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class Response {
    private int result;
    private String reason;
    public Response(Object object){
        JsonValue value=new JsonReader().parse((String)object);
        result=value.getInt("result");
        reason=value.getString("reason","");
    }

    public int getResult(){
        return this.result;
    }

    public String getReason(){
        return this.reason;
    }
}
