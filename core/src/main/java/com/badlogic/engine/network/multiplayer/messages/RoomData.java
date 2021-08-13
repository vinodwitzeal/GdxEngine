package com.badlogic.engine.network.multiplayer.messages;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class RoomData {
    public String roomId;
    public String[] joinedUsers;
    public int maxUsers;
    public RoomData(Object object){
        JsonValue value=new JsonReader().parse((String)object);
        roomId=value.getString("roomId");
        if (value.has("joinedUsers")) {
            joinedUsers = value.get("joinedUsers").asStringArray();
        }else {
            joinedUsers=new String[]{};
        }
        maxUsers=value.getInt("maxUsers");
    }

    public String getRoomId(){
        return this.roomId;
    }

    public String[] getJoinedUsers(){
        return this.joinedUsers;
    }

    public int getMaxUsers(){
        return this.maxUsers;
    }


}
