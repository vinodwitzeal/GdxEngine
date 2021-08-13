package com.badlogic.engine.ui.screens;

import com.badlogic.engine.EngineController;
import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.engine.widgets.UILabel;
import com.badlogic.engine.widgets.UITextButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuScreen extends UIScreen{
    public MenuScreen(EngineController controller) {
        super(controller,"c7c7c7");
    }

    @Override
    public void buildUI() {
        Table menuTable=new Table();
        menuTable.left();
        menuTable.setBackground(AssetContainer.getInstance().getBoxDrawable("ff00ff"));
        menuTable.pad(2,5,2,5);

        UITextButton fileButton=new UITextButton("File",new UITextButton.UITextButtonStyle(),25);
        menuTable.add(fileButton).width(100).height(30).padRight(2);

        UITextButton editButton=new UITextButton("Edit",new UITextButton.UITextButtonStyle(),25);
        menuTable.add(editButton).width(100).height(30).padRight(2);

        UITextButton viewButton=new UITextButton("View",new UITextButton.UITextButtonStyle(),25);
        menuTable.add(viewButton).width(100).height(30).padRight(2);

        UITextButton componentButton=new UITextButton("Component",new UITextButton.UITextButtonStyle(),25);
        menuTable.add(componentButton).width(150).height(30).padRight(2);

        menuTable.setSize(width,34);
        menuTable.setPosition(0,height-34);

        stage.addActor(menuTable);

        Table hierarchyTable=new Table();
        hierarchyTable.top();
        UILabel hierarchyLabel=new UILabel("Hierarchy",20);
        hierarchyLabel.getStyle().fontColor= Color.valueOf("888888");
        hierarchyTable.add(hierarchyLabel).row();
        hierarchyTable.setBackground(AssetContainer.getInstance().getBoxDrawable("ffffff"));
        hierarchyTable.setSize(250,height-36);
        hierarchyTable.setPosition(0,0);
        stage.addActor(hierarchyTable);


        Table inspectorTable=new Table();
        inspectorTable.setBackground(AssetContainer.getInstance().getBoxDrawable("ffffff"));
        inspectorTable.setSize(250,height-36);
        inspectorTable.setPosition(width-250,0);
        stage.addActor(inspectorTable);


        Table assetsTable=new Table();
        assetsTable.setBackground(AssetContainer.getInstance().getBoxDrawable("ffffff"));
        assetsTable.setSize(width-504,200);
        assetsTable.setPosition(252,0);
        stage.addActor(assetsTable);
    }
}
