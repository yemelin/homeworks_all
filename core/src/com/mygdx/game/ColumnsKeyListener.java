package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.ColumnsStage.ClipData;

public class ColumnsKeyListener extends InputListener {
	private ClipData _clipData;

	public ColumnsKeyListener (ColumnsStage.ClipData data) {
		super();
		_clipData = data;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
    	System.out.println("KeyDown from "+Thread.currentThread().getName()
    			+", pressed:"+event.getCharacter());
        _clipData.keyPressed = true;
        _clipData.ch = (_clipData.ch==Input.Keys.P) ? -1:keycode;
		return true;
	}						

}
