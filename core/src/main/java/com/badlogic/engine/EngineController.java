package com.badlogic.engine;

import com.badlogic.engine.controller.UIController;
import com.badlogic.engine.network.multiplayer.SocketClient;
import com.badlogic.engine.ui.screens.TestScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class EngineController extends UIController {
    public EngineController(){
    }

    @Override
    public void onCreate() {
        Gdx.input.setCatchKey(Input.Keys.BACK,true);
        setScreen(new TestScreen(this));
    }
}