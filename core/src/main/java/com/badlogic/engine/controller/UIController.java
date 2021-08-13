package com.badlogic.engine.controller;

import com.badlogic.engine.ui.screens.UIScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public abstract class UIController implements ApplicationListener {
    public UIScreen screen;
    public void setScreen(UIScreen screen){
        UIScreen previousScreen=this.screen;
        if (previousScreen!=null){
            previousScreen.hide();
            previousScreen.dispose();
        }
        this.screen=screen;
        if (this.screen!=null){
            this.screen.show();
        }
    }

    public abstract void onCreate();

    @Override
    public void create() {
        onCreate();
    }


    @Override
    public void resize(int width, int height) {
        if (this.screen!=null)this.screen.resize(width,height);
    }

    @Override
    public void render() {
        if (this.screen!=null)this.screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        if (this.screen!=null)this.screen.pause();
    }

    @Override
    public void resume() {
        if (this.screen!=null)this.screen.resume();
    }

    @Override
    public void dispose() {
        if (this.screen!=null)this.screen.dispose();
        this.screen=null;
    }
}
