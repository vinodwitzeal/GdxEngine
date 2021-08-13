package com.badlogic.engine.ui.popup;

import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.engine.ui.screens.UIScreen;
import com.badlogic.engine.widgets.UILabel;
import com.badlogic.gdx.Gdx;

public class WelcomeDialog extends UIDialog {
    public WelcomeDialog(UIScreen screen) {
        super(screen);
    }

    @Override
    public void buildUI() {
        UILabel label=new UILabel("Hello",45);
        label.getStyle().background= AssetContainer.getInstance().getBoxDrawable("ff0000");
        contentTable.add(label).width(200).height(60);
    }

    @Override
    protected void onShow() {
        Gdx.app.error("WelcomeDialog","OnShow");
    }

    @Override
    public void onHide() {
        Gdx.app.error("WelcomeDialog","OnHide");
    }
}
