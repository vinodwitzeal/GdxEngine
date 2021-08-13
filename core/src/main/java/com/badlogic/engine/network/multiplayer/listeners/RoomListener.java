package com.badlogic.engine.network.multiplayer.listeners;

import com.badlogic.engine.network.multiplayer.messages.RoomData;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public interface RoomListener {

    void onCreateRoomDone(int result,String reason,RoomData roomData);

    void onJoinRoomDone(int result,String reason,RoomData roomData);

    void onUserJoinedRoom(String user,RoomData roomData);

    void onLeaveRoomDone(int result, String reason, RoomData roomData);

    void onUserLeftRoom(String user,RoomData roomData);
}
