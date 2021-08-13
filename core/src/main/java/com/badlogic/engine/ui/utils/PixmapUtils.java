package com.badlogic.engine.ui.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class PixmapUtils {
    public static NinePatchDrawable createBoxDrawable(){
        Pixmap pixmap=new Pixmap(4,4, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf("ffffff"));
        pixmap.fill();
        Texture texture=new Texture(pixmap);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pixmap.dispose();
        return new NinePatchDrawable(new NinePatch(texture,1,1,1,1));
    }

    public static NinePatchDrawable createRoundedDrawable(int corner){
        int width=2*corner;
        int height=2*corner;
        int pixmapWidth=width+2*corner;
        int pixmapHeight=height+2*corner;
        Pixmap pixmap=new Pixmap(pixmapWidth,pixmapHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf("ffffff"));
        pixmap.fillRectangle(corner,0,width,pixmapHeight);
        pixmap.fillRectangle(0,corner,pixmapWidth,height);

        pixmap.fillCircle(corner,corner,corner);
        pixmap.fillCircle(width+corner-1,corner,corner);
        pixmap.fillCircle(corner,height+corner-1,corner);
        pixmap.fillCircle(width+corner-1,height+corner-1,corner);
        Texture texture=new Texture(pixmap);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pixmap.dispose();
        return new NinePatchDrawable(new NinePatch(texture,corner+1,corner+1,corner+1,corner+1));
    }
}
