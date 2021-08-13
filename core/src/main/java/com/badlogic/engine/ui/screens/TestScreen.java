package com.badlogic.engine.ui.screens;

import com.badlogic.engine.EngineController;
import com.badlogic.engine.ui.popup.WelcomeDialog;
import com.badlogic.engine.widgets.UITextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TestScreen extends UIScreen {
    public TestScreen(EngineController controller) {
        super(controller, "ff00ff");
    }

    @Override
    public void buildUI() {
        UITextButton label = new UITextButton("Hello",UITextButton.UITextButtonStyle.getStyle(), 45){
            @Override
            public void onClicked() {
                WelcomeDialog welcomeDialog=new WelcomeDialog(TestScreen.this);
                welcomeDialog.hide();
                welcomeDialog.show();
            }
        };
        label.setSize(200,60);
        label.setPosition((width - label.getWidth()) / 2f, (height - label.getHeight()) / 2f);

        TextField textField;

        stage.addActor(label);
    }
}
