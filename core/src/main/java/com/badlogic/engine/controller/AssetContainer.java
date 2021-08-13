package com.badlogic.engine.controller;

import com.badlogic.engine.enums.UIFont;
import com.badlogic.engine.ui.utils.PixmapUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AssetContainer {
    private Map<String,NinePatchDrawable> drawableMap;
    private Map<String, BitmapFont> fontMap;
    private ShaderProgram fontShader;
    private AssetContainer(){
        drawableMap= Collections.synchronizedMap(new HashMap<String,NinePatchDrawable>());
        fontMap=Collections.synchronizedMap(new HashMap<String, BitmapFont>());
        fontShader=new ShaderProgram(Gdx.files.internal("shaders/distance.vert"),Gdx.files.internal("shaders/distance.frag"));
        fontShader.isCompiled();
    }

    public NinePatchDrawable getBoxDrawable(String color){
        NinePatchDrawable boxDrawable;
        if (drawableMap.containsKey("boxDrawable")){
            boxDrawable=drawableMap.get("boxDrawable");
        }else {
            boxDrawable= PixmapUtils.createBoxDrawable();
            drawableMap.put("boxDrawable",boxDrawable);
        }
        return boxDrawable.tint(Color.valueOf(color));
    }

    public NinePatchDrawable getRoundedDrawable(int corner,String color){
        return getRoundedDrawable(corner).tint(Color.valueOf(color));
    }

    private NinePatchDrawable getRoundedDrawable(int corner){
        String name="corner"+corner;
        if (drawableMap.containsKey(name)){
            return drawableMap.get(name);
        }
        NinePatchDrawable ninePatchDrawable=PixmapUtils.createRoundedDrawable(corner);
        drawableMap.put(name,ninePatchDrawable);
        return ninePatchDrawable;
    }

    public BitmapFont getFont(UIFont font){
        String name=font.toString();
        if (fontMap.containsKey(name)){
            return fontMap.get(name);
        }else {
            BitmapFont.BitmapFontData fontData=new BitmapFont.BitmapFontData(Gdx.files.internal("fonts/"+name+".fnt"),false);
            Texture texture=new Texture(Gdx.files.internal("fonts/"+name+".png"));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            TextureRegion region=new TextureRegion(texture);
            BitmapFont bitmapFont=new BitmapFont(fontData,region,false);
            fontMap.put(name,bitmapFont);
            return bitmapFont;
        }
    }

    public ShaderProgram getFontShader(){
        return fontShader;
    }




    private static AssetContainer instance;
    public static AssetContainer getInstance(){
        if (instance==null){
            instance=new AssetContainer();
        }
        return instance;
    }
}
