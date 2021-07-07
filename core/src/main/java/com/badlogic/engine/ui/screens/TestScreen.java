package com.badlogic.engine.ui.screens;

import com.badlogic.engine.EngineController;
import com.badlogic.engine.widgets.UILabel;
import com.badlogic.engine.widgets.UITextButton;
import com.badlogic.gdx.graphics.Color;

public class TestScreen extends UIScreen {
    public TestScreen(EngineController controller) {
        super(controller, "000000");
    }

    @Override
    public void buildUI() {
        UITextButton label = new UITextButton("Hello",new UITextButton.UITextButtonStyle(), 45);
        label.pack();
        label.setPosition((width - label.getWidth()) / 2f, (height - label.getHeight()) / 2f);
        stage.addActor(label);
    }
}
