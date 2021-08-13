package com.badlogic.engine.ui.screens;

import com.badlogic.engine.EngineController;
import com.badlogic.engine.ui.widgets.ActionBar;
import com.badlogic.engine.ui.widgets.Container;

public class MainScreen extends UIScreen{
    private ActionBar actionBar;
    private Container container;
    public MainScreen(EngineController controller) {
        super(controller, "ffffff");
    }

    @Override
    public void buildUI() {
        actionBar=new ActionBar(this);
        actionBar.setSize(width,height*0.1f);
        actionBar.setPosition(0,height*0.9f);
        stage.addActor(actionBar);

        container=new Container(this);
        container.setSize(width,height*0.9f);
        container.setPosition(0,0);
        stage.addActor(container);
    }
}
