package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import columns.Field;
import columns.State;
import columns.View;

public class ColumnsView extends View {
	
	private ColumnsStage _stage;
	private Box[][] _boxes= new Box[Field.Depth+2][Field.Width+2]; 
	private Label score;
	private Label level;
//	--------------------------
	public ColumnsView(ColumnsStage stage) {
		_stage = stage;
		BitmapFont font = new BitmapFont(true);
		score = new Label("SCORE", new Label.LabelStyle(font, Color.BLACK));
		score.setBounds(10, 10, 30, 10);
		_stage.addActor(score);
		
		level = new Label("LEVEL", new Label.LabelStyle(font, Color.BLACK));
		_stage.addActor(level);
		resetLabels();		
	}
   	
	@Override
	protected void DrawBox(final int col, final int row, final int c) {
		if (_boxes[row][col] == null) {
			Box box = new Box(c);
			_boxes[row][col] = box;
			box.setColor(Color.BLACK);
			_stage.addActor(box);
			box.setBounds(LeftBorder+(col-1)*SL, TopBorder+(row-1)*SL, SL, SL);
		}
		_boxes[row][col].setColor(c);

	}
	
	public void resetBoxes() {
		for (int i=1; i<_boxes.length; i++) {
			for (int j=1; j<_boxes[0].length; j++) {
				if (_boxes[i][j]!=null)
					_boxes[i][j].setBounds(LeftBorder+(j-1)*SL, TopBorder+(i-1)*SL, SL, SL);
			}        			
		}
	}
	
	public void resetLabels() {
		score.setFontScale(SL/30f);
		level.setFontScale(SL/30f);
		level.setBounds(10, 10+SL, 30, 10);
	}
	
	@Override
	protected void ShowLevel(State state) {
		level.setText("level:"+state.getScore().Level);
	}
	@Override
	protected void ShowScore(State state) {
		score.setText("score:"+state.getScore().Score);
	}
}
