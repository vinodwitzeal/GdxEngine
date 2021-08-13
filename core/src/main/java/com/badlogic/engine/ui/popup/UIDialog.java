package com.badlogic.engine.ui.popup;

import com.badlogic.engine.EngineController;
import com.badlogic.engine.controller.AssetContainer;
import com.badlogic.engine.ui.screens.UIScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;

public abstract class UIDialog extends Group {
    public EngineController controller;
    public UIScreen screen;
    public Color tempColor;
    public Drawable background;
    public Table contentTable;

    boolean cancelHide;
    Actor previousKeyboardFocus, previousScrollFocus;
    FocusListener focusListener;

    protected InputListener ignoreTouchDown = new InputListener() {
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            event.cancel();
            return false;
        }
    };

    public UIDialog(UIScreen screen) {
        this.controller = screen.controller;
        this.screen = screen;
        this.tempColor = Color.valueOf("ffffff");
        this.background = AssetContainer.getInstance().getBoxDrawable("00000088");
        this.setTouchable(Touchable.childrenOnly);
        this.contentTable = new Table();
        addActor(contentTable);
        initialize();
    }

    protected void initialize() {
        focusListener = new FocusListener() {
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) focusChanged(event);
            }

            public void scrollFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (!focused) focusChanged(event);
            }

            private void focusChanged(FocusEvent event) {
                Stage stage = getStage();
                if (stage != null && stage.getRoot().getChildren().size > 0
                        && stage.getRoot().getChildren().peek() == UIDialog.this) { // Dialog is top most actor.
                    Actor newFocusedActor = event.getRelatedActor();
                    if (newFocusedActor != null && !newFocusedActor.isDescendantOf(UIDialog.this)
                            && !(newFocusedActor.equals(previousKeyboardFocus) || newFocusedActor.equals(previousScrollFocus)))
                        event.cancel();
                }
            }
        };

        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode== Input.Keys.BACK){
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            hide();
                        }
                    });
                }
                return false;
            }
        });
    }

    public abstract void buildUI();

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        Actor actor = super.hit(x, y, touchable);
        if (actor == null) {
            hide();
            return this;
        }
        return actor;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawBackground(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
    }

    protected void drawBackground(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        tempColor.set(batch.getColor());
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        background.draw(batch, x, y, width, height);
    }

    public void show() {
        clearActions();
        Stage stage = screen.stage;
        setSize(stage.getWidth(), stage.getHeight());
        setPosition(0, 0);
        contentTable.clear();
        buildUI();
        setContentPosition();
        clearActions();
        removeCaptureListener(ignoreTouchDown);
        previousKeyboardFocus = null;
        Actor actor = stage.getKeyboardFocus();
        if (actor != null && !actor.isDescendantOf(this)) previousKeyboardFocus = actor;
        previousScrollFocus = null;
        actor = stage.getScrollFocus();
        if (actor != null && !actor.isDescendantOf(this)) previousScrollFocus = actor;
        stage.addActor(this);
        stage.cancelTouchFocus();
        stage.setKeyboardFocus(this);
        stage.setScrollFocus(this);
        onShow();
    }

    protected void setContentPosition(){
        contentTable.pack();
        contentTable.setPosition((getWidth() - contentTable.getWidth()) / 2f, (getHeight() - contentTable.getHeight()) / 2f);
    }

    protected void onShow(){

    }

    public void hide() {
        Stage stage = getStage();
        if (stage != null) {
            removeListener(focusListener);
            if (previousKeyboardFocus != null && previousKeyboardFocus.getStage() == null)
                previousKeyboardFocus = null;
            Actor actor = stage.getKeyboardFocus();
            if (actor == null || actor.isDescendantOf(this))
                stage.setKeyboardFocus(previousKeyboardFocus);

            if (previousScrollFocus != null && previousScrollFocus.getStage() == null)
                previousScrollFocus = null;
            actor = stage.getScrollFocus();
            if (actor == null || actor.isDescendantOf(this))
                stage.setScrollFocus(previousScrollFocus);
            onHide();
        }
        remove();
    }

    public void onHide(){

    }
}
