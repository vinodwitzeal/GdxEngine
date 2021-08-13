package com.badlogic.engine.ui.widgets;

import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.engine.ui.screens.MainScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Container extends Group {
    private MainScreen mainScreen;
    private Drawable background;
    public Container(MainScreen mainScreen){
        this.mainScreen=mainScreen;
        background= AssetContainer.getInstance().getBoxDrawable("c7c7c7");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawBackground(batch,parentAlpha,getX(),getY());
        super.draw(batch, parentAlpha);
    }

    protected void drawBackground(Batch batch,float parentAlpha,float x,float y){
        Color color=getColor();
        batch.setColor(color.r,color.g,color.b,color.a*parentAlpha);
        background.draw(batch,x,y,getWidth(),getHeight());
    }
}
