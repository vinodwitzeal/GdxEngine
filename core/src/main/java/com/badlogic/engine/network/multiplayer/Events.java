package com.badlogic.engine.network.multiplayer;

public interface Events {
    String CUSTOM="custom";
    String CUSTOM_RESPONSE="custom_response";
    String CREATE_ROOM="create";
    String CREATE_ROOM_RESPONSE="create_response";
    String JOIN_ROOM="join";
    String JOIN_ROOM_RANDOM="join_random";
    String JOIN_ROOM_RESPONSE="join_response";
    String JOIN_ROOM_NOTIFY="join_notify";
    String LEAVE_ROOM="leave";
    String LEAVE_ROOM_RESPONSE="leave_response";
    String LEAVE_ROOM_NOTIFY="leave_notify";
    String CHAT="chat";
    String CHAT_RESPONSE="chat_response";
    String CHAT_NOTIFY="chat_notify";
}
