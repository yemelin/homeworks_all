package com.mygdx.game.android;

import android.os.Bundle;
import columns.State.BundleData;

import com.mygdx.game.ColumnsScreen;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.ColumnsStage;
import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	private MyGdxGame _game;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(_game =new MyGdxGame(), config);
		ColumnsStage.inputType = 1;
	}
	
	@Override
	protected void onStart() {
		System.out.println("onStart");
		super.onStart();
	}
	@Override
	protected void onRestart() {
		System.out.println("onRestart");
		super.onRestart();
	}
	@Override
	protected void onResume() {
		System.out.println("onResume");
		super.onResume();
	}
	@Override
	protected void onPause() {
		System.out.println("onPause");
		super.onPause();
	}
	@Override
	protected void onStop() {
		System.out.println("onStop");
		super.onStop();
	}
	@Override
	protected void onDestroy() {
		System.out.println("onDestroy");		
		super.onDestroy();
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    // Save the user's current game state	    
	    // Always call the superclass so it can save the view hierarchy state
	    super.onSaveInstanceState(savedInstanceState);
	    BundleData bd = ((ColumnsScreen) _game.getScreen()).getBundleData();
	}
}
