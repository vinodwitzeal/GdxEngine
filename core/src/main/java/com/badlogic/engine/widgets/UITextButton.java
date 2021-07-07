package com.badlogic.engine.widgets;

import com.badlogic.engine.controller.AssetController;
import com.badlogic.engine.enums.UIFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class UITextButton extends UIButton{
    private UILabel label;
    public UITextButton(String text,UITextButtonStyle style) {
        super(style);
        UILabelStyle labelStyle=new UILabelStyle(style.font,style.fontColor);
        label=new UILabel(text,labelStyle);
        add(label);
        setSize(getPrefWidth(),getPrefHeight());
    }

    public UITextButton(String text,UITextButtonStyle style,float fontSize){
        super(style);
        UILabelStyle labelStyle=new UILabelStyle(style.font,style.fontColor);
        label=new UILabel(text,labelStyle,fontSize);
        add(label);
        setSize(getPrefWidth(),getPrefHeight());
    }

    public UITextButton(String text,float fontSize){
        super(new UITextButtonStyle());
        label=new UILabel(text,fontSize);
        add(label);
        setSize(getPrefWidth(),getPrefHeight());
    }

    public static class UITextButtonStyle extends UIButtonStyle{
        public UIFont font;
        public String fontColor;

        public UITextButtonStyle() {
            super();
            font= UIFont.ROBOTO_BOLD;
            fontColor="0000000";
        }
    }
}
