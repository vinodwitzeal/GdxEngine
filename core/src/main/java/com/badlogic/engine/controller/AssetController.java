package com.badlogic.engine.controller;

import com.badlogic.engine.enums.UIFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.HashMap;

public class AssetController {
    private HashMap<String,Texture> textureMap;
    private HashMap<String,BitmapFont> fontMap;
    public NinePatchDrawable boxDrawable,roundedBoxDrawable;
    public TextureRegionDrawable circleDrawable;

    private ShaderProgram fontShader;
    private AssetController(){
        textureMap=new HashMap<String, Texture>();
        fontMap=new HashMap<String, BitmapFont>();
        boxDrawable=new NinePatchDrawable(new NinePatch(getTexture("box"),1,1,1,1));
        roundedBoxDrawable=new NinePatchDrawable(new NinePatch(getTexture("rounded_box"),5,5,5,5));
        circleDrawable=new TextureRegionDrawable(getTexture("circle"));
    }

    private Texture getTexture(String name) {
        if (textureMap.containsKey(name)){
            return textureMap.get(name);
        }
        Texture texture=new Texture("textures/"+name+".png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureMap.put(name,texture);
        return texture;
    }

    private Texture getFontTexture(String name){
        if (textureMap.containsKey(name)){
            return textureMap.get(name);
        }
        Texture texture=new Texture("fonts/"+name+".png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureMap.put(name,texture);
        return texture;
    }

    public ShaderProgram getFontShader(){
        if (fontShader==null){
            fontShader=new ShaderProgram(Gdx.files.internal("shaders/distance.vert"),Gdx.files.internal("shaders/distance.frag"));
            fontShader.isCompiled();
        }
        return fontShader;
    }

    public BitmapFont getFont(UIFont font){
        String name=font.toString();
        if (fontMap.containsKey(name)){
            return fontMap.get(name);
        }
        BitmapFont.BitmapFontData fontData=new BitmapFont.BitmapFontData(Gdx.files.internal("fonts/"+name+".fnt"),false);
        TextureRegion region=new TextureRegion(getFontTexture(name));
        BitmapFont bitmapFont=new BitmapFont(fontData,region,false);
        fontMap.put(name,bitmapFont);
        return bitmapFont;
    }


    private static AssetController instance;
    public static AssetController getInstance(){
        if (instance==null){
            instance=new AssetController();
        }
        return instance;
    }
}
