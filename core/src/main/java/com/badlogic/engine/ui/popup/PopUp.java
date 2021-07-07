package com.badlogic.engine.ui.popup;

import com.badlogic.engine.EngineController;
import com.badlogic.engine.controller.AssetController;
import com.badlogic.engine.ui.screens.UIScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public abstract class PopUp extends Group {
    public EngineController controller;
    public UIScreen screen;
    public Color tempColor;
    public Drawable background;
    public Table contentTable;
    public PopUp(UIScreen screen){
        this.controller=screen.controller;
        this.screen=screen;
        this.tempColor=Color.valueOf("ffffff");
        this.background= AssetController.getInstance().boxDrawable.tint(Color.valueOf("00000088"));
        this.setTouchable(Touchable.childrenOnly);
        this.contentTable=new Table();
    }

    public abstract void buildUI();

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor actor=super.hit(x, y, touchable);
        if (actor==null){
            hide();
            return this;
        }
        return actor;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawBackground(batch,parentAlpha,getX(),getY(),getWidth(),getHeight());
        super.draw(batch, parentAlpha);
    }

    protected void drawBackground(Batch batch,float parentAlpha,float x,float y,float width,float height){
        tempColor.set(batch.getColor());
        Color color=getColor();
        batch.setColor(color.r,color.g,color.b,color.a*parentAlpha);
        background.draw(batch,x,y,width,height);
    }

    public void show(){
        Stage stage=screen.stage;
        setSize(stage.getWidth(),stage.getHeight());
        setPosition(0,0);
        contentTable.clear();
        buildUI();
        contentTable.pack();
        contentTable.setPosition((getWidth()-contentTable.getWidth())/2f,(getHeight()-contentTable.getHeight())/2f);
        addActor(contentTable);
        stage.addActor(this);
    }

    public void hide(){
        remove();
    }
}
