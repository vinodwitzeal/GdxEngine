package com.badlogic.engine.widgets;

import com.badlogic.engine.enums.UIFont;

public class UITextButton extends UIButton{
    private UILabel label;
    public UITextButton(String text,UITextButtonStyle style) {
        this(text,style,16);
    }

    public UITextButton(String text,UITextButtonStyle style,float fontSize){
        super(style);
        if (style.font==null)throw new IllegalArgumentException("Font cannot be null");
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


        public static UITextButtonStyle getStyle(){
            UITextButtonStyle style=new UITextButtonStyle();
            UIButtonStyle buttonStyle=UIButtonStyle.getStyle();
            style.background=buttonStyle.background;
            style.pressedColor=buttonStyle.pressedColor;
            style.unpressedColor=buttonStyle.unpressedColor;
            style.font= UIFont.ROBOTO_BOLD;
            style.fontColor="0000000";
            return style;
        }

    }
}
