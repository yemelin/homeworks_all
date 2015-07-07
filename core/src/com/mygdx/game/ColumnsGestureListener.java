package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ColumnsStage.ClipData;

public class ColumnsGestureListener implements GestureListener {
	private ClipData _clipData;
	private ColumnsStage _stage;

	public ColumnsGestureListener(ColumnsStage stage) {
		super();
		_stage = stage;
		_clipData = stage.clipData;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		_clipData.keyPressed = true;
		_clipData.ch = Input.Keys.SPACE;
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		if (_clipData.enginePaused)
			_stage.resumeEngine();
		else
			_stage.pauseEngine();
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (Gdx.graphics.getWidth()-40<=velocityX) {
			_stage.init();
			return true;
		}
		_clipData.keyPressed = true;
		if (Math.abs(velocityX)>=Math.abs(velocityY))
			_clipData.ch = (velocityX>0) ? Input.Keys.RIGHT: Input.Keys.LEFT; 
		else
			_clipData.ch = (velocityY>0) ? Input.Keys.UP: Input.Keys.DOWN;
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1,
			Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}
