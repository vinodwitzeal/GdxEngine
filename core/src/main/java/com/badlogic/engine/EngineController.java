package com.badlogic.engine;

import com.badlogic.engine.controller.UIController;
import com.badlogic.engine.ui.screens.MenuScreen;
import com.badlogic.engine.ui.screens.TestScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.Menu;
import java.util.HashMap;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class EngineController extends UIController {
    public EngineController(){

    }

    @Override
    public void onCreate() {
        setScreen(new TestScreen(this));
    }
}