package com.badlogic.engine.network.multiplayer;

import com.badlogic.engine.network.multiplayer.listeners.ChatListener;
import com.badlogic.engine.network.multiplayer.listeners.ConnectionListener;
import com.badlogic.engine.network.multiplayer.listeners.RoomListener;
import com.badlogic.engine.network.multiplayer.messages.ChatMessage;
import com.badlogic.engine.network.multiplayer.messages.Response;
import com.badlogic.engine.network.multiplayer.messages.RoomData;
import com.badlogic.gdx.Gdx;

import java.net.URISyntaxException;
import java.util.Arrays;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketClient {
    private final String TAG = SocketClient.class.getSimpleName();
    private Socket socket;
    private String userId, customData;
    private ConnectionListener connectionListener;
    private RoomListener roomListener;
    private ChatListener chatListener;

    public SocketClient(String address, String namespace) throws URISyntaxException {
        String URL = address + "/" + namespace;
        socket = IO.socket(URL);
        initialize();
    }

    private void initialize() {
        listenConnectionEvents();
        listenRoomEvents();
        listenChatEvents();
    }

    private void listenConnectionEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Socket.EVENT_CONNECT, args);
                socket.emit(Events.CUSTOM, userId, customData);
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Socket.EVENT_DISCONNECT, args);
                onDisconnected();
            }
        }).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Socket.EVENT_CONNECT_ERROR, args);
                onConnectionError();
            }
        }).on(Events.CUSTOM_RESPONSE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.CUSTOM_RESPONSE, args);
                Response response=new Response(args[0]);
                if (response.getResult()==0){
                    onConnected();
                }else {
                    onConnectionError();
                    socket.disconnect();
                }
            }
        });
    }

    public void setConnectionListener(ConnectionListener connectionListener){
        this.connectionListener=connectionListener;
    }

    public void onConnected(){
        if (connectionListener!=null)connectionListener.onConnected();
    }

    public void onConnectionError(){
        if (connectionListener!=null)connectionListener.onConnectionError();
    }

    public void onDisconnected(){
        if (connectionListener!=null)connectionListener.onDisconnected();
    }

    private void listenRoomEvents() {
        socket.on(Events.CREATE_ROOM_RESPONSE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.CREATE_ROOM_RESPONSE, args);
                Response response=new Response(args[0]);
                RoomData roomData=null;
                if (response.getResult()==0){
                    roomData=new RoomData(args[1]);
                }
                onCreateRoomDone(response.getResult(),response.getReason(),roomData);
            }
        }).on(Events.JOIN_ROOM_RESPONSE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.JOIN_ROOM_RESPONSE, args);
                Response response=new Response(args[0]);
                RoomData roomData=null;
                if (response.getResult()==0){
                    roomData=new RoomData(args[1]);
                }
                onJoinRoomDone(response.getResult(),response.getReason(),roomData);
            }
        }).on(Events.JOIN_ROOM_NOTIFY, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.JOIN_ROOM_NOTIFY, args);
                String userId=(String) args[0];
                RoomData roomData=new RoomData(args[1]);
                onUserJoinedRoom(userId,roomData);
            }
        }).on(Events.LEAVE_ROOM_RESPONSE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.LEAVE_ROOM_RESPONSE, args);
                Response response=new Response(args[0]);
                RoomData roomData=null;
                if (response.getResult()==0){
                    roomData=new RoomData(args[1]);
                }
                onLeaveRoomDone(response.getResult(),response.getReason(),roomData);
            }
        }).on(Events.LEAVE_ROOM_NOTIFY, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.LEAVE_ROOM_NOTIFY, args);
                String userId=(String) args[0];
                RoomData roomData=new RoomData(args[1]);
                onUserLeftRoom(userId,roomData);
            }
        });
    }

    public void setRoomListener(RoomListener roomListener){
        this.roomListener=roomListener;
    }

    public void onCreateRoomDone(int result,String reason,RoomData roomData){
        if (roomListener!=null)roomListener.onCreateRoomDone(result,reason,roomData);
    }

    public void onJoinRoomDone(int result,String reason,RoomData roomData){
        if (roomListener!=null)roomListener.onJoinRoomDone(result,reason,roomData);
    }

    public void onUserJoinedRoom(String userId,RoomData roomData){
        if (roomListener!=null)roomListener.onUserJoinedRoom(userId,roomData);
    }


    public void onLeaveRoomDone(int result,String reason,RoomData roomData){
        if (roomListener!=null)roomListener.onLeaveRoomDone(result,reason,roomData);
    }

    public void onUserLeftRoom(String userId,RoomData roomData){
        if (roomListener!=null)roomListener.onUserLeftRoom(userId,roomData);
    }


    private void listenChatEvents() {
        socket.on(Events.CHAT_RESPONSE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.CHAT_RESPONSE,args);
                Response response=new Response(args[0]);
                onChatSent(response.getResult(),response.getReason());
            }
        }).on(Events.CHAT_NOTIFY, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                printLog(Events.CHAT_NOTIFY,args);
                boolean privateChat=(boolean)args[0];
                ChatMessage chatMessage=new ChatMessage(args[1]);
                if (privateChat){
                    onPrivateChatReceived(chatMessage.getSender(),chatMessage.getChat());
                }else {
                    onChatReceived(chatMessage.getSender(),chatMessage.getChat());
                }
            }
        });
    }

    public void setChatListener(ChatListener chatListener){
        this.chatListener=chatListener;
    }

    public void onChatSent(int result,String reason){
        if (chatListener!=null)chatListener.onChatSent(result,reason);
    }

    public void onChatReceived(String sender,String chat){
        if (chatListener!=null)chatListener.onChatReceived(sender,chat);
    }

    public void onPrivateChatReceived(String sender,String chat){
        if (chatListener!=null)chatListener.onPrivateChatReceived(sender,chat);
    }


    public void connect(String userId, String customData) {
        if (isEmpty(userId))
            throw new IllegalArgumentException("userID cannot be null or empty.");
        if (customData == null) {
            customData = "";
        }
        this.userId = userId;
        this.customData = customData;
        socket.connect();
    }

    void printLog(String event, Object... args) {
        Gdx.app.error(event, Arrays.toString(args));
    }


    public void createRoom(String roomId, int maxUsers, String customRoomData) {
        if (isEmpty(roomId) || maxUsers<2)return;
        if (customRoomData==null){
            customRoomData="";
        }
        socket.emit(Events.CREATE_ROOM, roomId, maxUsers, customRoomData);
    }

    public void joinRoom(String roomId) {
        if (isEmpty(roomId))return;
        socket.emit(Events.JOIN_ROOM, roomId);
    }

    public void joinRoom(){
        socket.emit(Events.JOIN_ROOM_RANDOM,"");
    }

    public void leaveRoom(String roomId){
        if (isEmpty(roomId))return;
        socket.emit(Events.LEAVE_ROOM,roomId);
    }

    public void sendChat(String message) {
        if (isEmpty(message))return;
        socket.emit(Events.CHAT, false, message);
    }

    public void sendPrivateChat(String message, String receiver) {
        if (isEmpty(message) || isEmpty(receiver))return;
        socket.emit(Events.CHAT, true, message, receiver);
    }

    private boolean isEmpty(String value){
        return value==null || value.isEmpty();
    }
}
