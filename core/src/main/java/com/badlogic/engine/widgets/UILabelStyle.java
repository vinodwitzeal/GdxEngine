package com.badlogic.engine.widgets;

import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.engine.enums.UIFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class UILabelStyle {
    public BitmapFont font;
    public Color fontColor;
    public Drawable background;
    private ShaderProgram defaultShader, distanceShader;
    private int enableShadow = 0;
    private Vector2 shadow = new Vector2();
    private Color shadowColor = Color.valueOf("000000");
    private int enableOutline = 0;
    private float outline = 0;
    private Color outlineColor = Color.valueOf("ffffff");

    public UILabelStyle(){
        updateShader();
    }

    public static UILabelStyle getStyle() {
        UILabelStyle labelStyle=new UILabelStyle();
        labelStyle.font= AssetContainer.getInstance().getFont(UIFont.ROBOTO_BOLD);
        labelStyle.fontColor=Color.valueOf("ffffff");
        return labelStyle;
    }

    public UILabelStyle(UIFont font,String color){
        this.font= AssetContainer.getInstance().getFont(font);
        this.fontColor=Color.valueOf(color);
        updateShader();
    }

    public UILabelStyle color(Color color){
        this.fontColor=color;
        return this;
    }

    public UILabelStyle shadow(float x,float y,Color shadowColor){
        this.enableShadow=1;
        this.shadow.set(x,y);
        this.shadowColor.set(shadowColor);
        return this;
    }

    public UILabelStyle outline(float outline,Color outlineColor){
        this.enableOutline=1;
        this.outline=outline;
        this.outlineColor.set(outlineColor);
        return this;
    }

    public void updateShader() {
        if (distanceShader==null){
            distanceShader = AssetContainer.getInstance().getFontShader();
        }
    }

    public void applyShader(Batch batch, float smoothing) {
        defaultShader = batch.getShader();
        batch.flush();
        batch.setShader(distanceShader);
        distanceShader.setUniformf("u_smoothing", smoothing);
        distanceShader.setUniformi("enableOutline", enableOutline);
        distanceShader.setUniformi("enableShadow", enableShadow);
        distanceShader.setUniformf("u_outline", outline);
        distanceShader.setUniformf("u_outlineColor", outlineColor);
        distanceShader.setUniformf("u_shadow", shadow);
        distanceShader.setUniformf("u_shadowColor", shadowColor);
    }

    public void removeShader(Batch batch) {
        batch.flush();
        batch.setShader(defaultShader);
    }
}
