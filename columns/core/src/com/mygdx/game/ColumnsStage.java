package com.mygdx.game;
import columns.Controller;
import columns.Model;
import columns.State.BundleData;
import columns.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ColumnsStage extends Stage {
	private Thread t;

	public static int inputType = 0;
	public ClipData clipData = new ClipData();
	public ColumnsView view;
	public Controller controller;
	
	public class ClipData {
		/*volatile*/ int ch;
		/*volatile*/ boolean keyPressed;
		/*volatile*/ boolean enginePaused = false;
		/*volatile*/ boolean engineStopped = false;
	}
	
	public ColumnsStage() {
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true/*, w, h*/);
		setViewport(new ScreenViewport(camera));
	}
	
	public void init() {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		View.setParams(w, h);
		
        Model model = new Model();
        controller = new Controller();
        model.addListener(controller);       
        
        view = new ColumnsView(this);
        
        controller.setModel(model);
        controller.setView(view);
        controller.requestRepaintEvent();
        
        if (inputType==0) {
        	Gdx.input.setInputProcessor(this);
        	addListener(new ColumnsKeyListener(clipData));
        }
        else {        	
        	Gdx.input.setInputProcessor(new GestureDetector
        			(new ColumnsGestureListener(this)));
        }
//---------------------------------------------        
        t = new Thread(new ColumnsEngine(controller, clipData));
//        System.out.println("Creating a thread from "+Thread.currentThread().getName());
        t.setDaemon(true);
        t.start();
	}
	
	public void pauseEngine() {
		clipData.enginePaused = true;
		clipData.keyPressed = true;
		clipData.ch = Input.Keys.P;	
	}
	
	public void resumeEngine() {
		clipData.enginePaused = false;
		clipData.keyPressed = true;
		clipData.ch = -1;		
	}
	
	public void stopEngine() {
		System.out.println("stopping the engine");
		clipData.ch = -1;
		clipData.engineStopped = true;
		clipData.keyPressed = true;
		
	}

	public BundleData getBundleData() {
		return controller.getBundleData();
	}	
}