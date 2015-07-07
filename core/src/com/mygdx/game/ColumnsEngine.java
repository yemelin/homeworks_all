package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.mygdx.game.ColumnsStage.ClipData;

import columns.Controller;
import columns.Utils;

public class ColumnsEngine implements Runnable{
	
	private Controller _controller;
	private ClipData _clipData;
	ColumnsEngine(Controller controller, ColumnsStage.ClipData data) {
		_controller = controller;
		_clipData = data;
	}
	@Override
    public void run() {
		long tc;
// Using the next line here instead of in the main thread leads to an error: no
// openGL context. It happens because Boxes should be created on the main thread,
//	which has the context bound to it
//        _controller.requestRepaintEvent();
        tc = System.currentTimeMillis();
        do {
//        	while(_clipData.enginePaused);
            	if ((int)(System.currentTimeMillis()-tc)>_controller.getDelay()) { 
                    tc = System.currentTimeMillis();
                    _controller.moveDown();
                }
                do {
                    Utils.Delay(50);
                    if (_clipData.keyPressed) {
                    	System.out.println("Processing key from "+Thread.currentThread().getName());
                        _clipData.keyPressed = false;
                        switch (_clipData.ch) {
                            case Input.Keys.LEFT:
                            	_controller.moveLeft();
                                break;
                            case Input.Keys.RIGHT:
                                _controller.moveRight();
                                break;
                            case Input.Keys.UP:
                            	_controller.scrollColorsUp();
                                break;
                            case Input.Keys.DOWN:
                            	_controller.scrollColorsDown();
                                break;
                            case Input.Keys.SPACE:
                            	_controller.dropFigure();
                            	tc = System.currentTimeMillis();
                                break;
                            case Input.Keys.P:
                                while (!_clipData.keyPressed) {
                                	if (!_clipData.enginePaused)
                                		_controller.blink();
                                	else Utils.Delay(50);
                                }
                                tc = System.currentTimeMillis();
                                System.out.println("Pause ended");
                                break;
                            case Input.Keys.PLUS:
                            	_controller.levelDown();
                                break;
                            case Input.Keys.MINUS:
                            	_controller.levelUp();
                                break;
                        }
                    }
                } while ( (int)(System.currentTimeMillis()-tc) <= _controller.getDelay());
        }while (!_controller.FullField()&& !_clipData.engineStopped);
        System.out.println("Engine stopped");
    }
}
