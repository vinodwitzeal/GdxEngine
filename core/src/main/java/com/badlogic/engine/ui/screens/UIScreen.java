package com.badlogic.engine.ui.screens;

import com.badlogic.engine.EngineController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class UIScreen implements Screen {
    public EngineController controller;
    public Stage stage;
    public Color clearColor;
    public int width,height;
    public UIScreen(EngineController controller,String clearColor){
        this.controller=controller;
        this.clearColor=Color.valueOf(clearColor);
        this.width=Gdx.graphics.getWidth();
        this.height=Gdx.graphics.getHeight();
    }

    public UIScreen(EngineController controller){
        this(controller,"ffffff");
    }

    protected void buildStage(){
        stage=new Stage(new ScreenViewport());
    }

    public abstract void buildUI();

    @Override
    public void show() {
        buildStage();
        buildUI();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(clearColor);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
