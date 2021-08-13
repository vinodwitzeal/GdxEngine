package com.badlogic.engine.ui.widgets;

import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.engine.ui.screens.MainScreen;
import com.badlogic.engine.widgets.UIButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ActionBar extends WidgetGroup {
    private MainScreen mainScreen;
    private Drawable background;
    public ActionBar(MainScreen mainScreen){
        this.mainScreen=mainScreen;
        background= AssetContainer.getInstance().getBoxDrawable("ff0000");
    }

    public void setMenuButton(UIButton menuButton){

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
