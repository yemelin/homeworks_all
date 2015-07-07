package com.mygdx.game;

import columns.State.BundleData;
import columns.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class ColumnsScreen implements Screen {
	
	private ColumnsStage _stage;

	@Override
	public void show() {
		_stage =  new ColumnsStage();
		_stage.init();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_stage.act();
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		View.setParams(width, height);
		_stage.view.resetBoxes();
		_stage.view.resetLabels();
		_stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		System.out.println("ONPAUSE!");
		_stage.pauseEngine();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		_stage.resumeEngine();
		System.out.println("RESUME!");

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		System.out.println("HIDE!");
		_stage.stopEngine();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("DISPOSE!");
	}

	public BundleData getBundleData() {
		return _stage.getBundleData();
	}
}
