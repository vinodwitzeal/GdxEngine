package com.badlogic.engine.widgets;

import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class UIButton extends Table implements Disableable {
    private boolean disabled;
    private UIButtonStyle style;

    private ClickListener clickListener;
    private boolean programmaticChangeEvents = true;
    public UIButton(UIButtonStyle style){
        this.style=style;
        this.setBackground(this.style.background);
        setSize(getPrefWidth(),getPrefHeight());
        setTouchable(Touchable.enabled);
        addListener(clickListener = new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                if (isDisabled()) return;
                onClicked();
            }
        });
    }

    public boolean isPressed () {
        return clickListener.isVisualPressed();
    }

    public boolean isOver () {
        return clickListener.isOver();
    }

    public void onClicked(){
        Gdx.app.error("UIButton","Button Clicked");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();

        Color color,tempColor;
        tempColor=getColor();
        if (isPressed() && !isDisabled()) {
            color=style.pressedColor;
        } else {
            color=style.unpressedColor;
        }
        setColor(color);
        super.draw(batch, parentAlpha);
        setColor(tempColor);
        Stage stage = getStage();
        if (stage != null && stage.getActionsRequestRendering() && isPressed() != clickListener.isPressed())
            Gdx.graphics.requestRendering();
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled=disabled;
    }

    @Override
    public boolean isDisabled() {
        return this.disabled;
    }

    public static class UIButtonStyle{
        public Drawable background;
        public Color unpressedColor;
        public Color pressedColor;


        public static UIButtonStyle getStyle(){
            UIButtonStyle buttonStyle=new UIButtonStyle();
            buttonStyle.background= AssetContainer.getInstance().getRoundedDrawable(5,"ffff00");
            buttonStyle.unpressedColor=Color.valueOf("ffffff");
            buttonStyle.pressedColor= Color.valueOf("dddddd");
            return buttonStyle;
        }
    }
}
