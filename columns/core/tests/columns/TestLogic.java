package columns;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLogic {
	
	private Model _model;
	private Logic _logic;
	
	@Before
	public void init() {
		_model = new Model();
		_logic = _model._logic;
	}
	@Test
	public void moveLeftRightTest() {
		int col = _logic.getState().col = 3;
		_logic.moveLeft();
		assertEquals(col = col-1, _logic.getState().col);
		_logic.moveRight();
		assertEquals(col = col+1, _logic.getState().col);
	}
	
	@Test
	public void moveDownTest() {
		int row = _logic.getState().row;
		_logic.moveDown();
		assertEquals(row+1, _logic.getState().row);
	}

	@Test
	public void dropDownTest() {
		_logic.DropFigure();
		assertEquals(Field.Depth-2, _logic.getState().row);
	}
	@Test
	public void moveLeftRightLimitTest() {
		int col = _logic.getState().col = 1;
		_logic.moveLeft();
		assertEquals(col, _logic.getState().col);
		col = _logic.getState().col = Field.Width;
		_logic.moveRight();
		assertEquals(col, _logic.getState().col);		
	}
	
	private interface Paster {
		public void paste();
	}
	
	private void testPaste (int pasteRow, int pasteCol, Paster p ) {
		_logic.getState().row = pasteRow;
		_logic.getState().col = pasteCol;
		Figure fig = _logic._fig;
		int[][] fieldData = _logic.getState().getField().getData();
		int c1 = fig.c[1];
		int c2 = fig.c[2];
		int c3 = fig.c[3];
		p.paste();
		assertEquals(c1, fieldData[pasteRow][pasteCol]);
		assertEquals(c2, fieldData[pasteRow+1][pasteCol]);
		assertEquals(c3, fieldData[pasteRow+2][pasteCol]);		
	}
	@Test
	public void moveDownPasteTest() {
		testPaste(Field.Depth-2, 1, new Paster(){
			@Override
			public void paste() {
				_logic.moveDown();				
			}			
		});
	}
	@Test
	public void pasteFigureTest() {
		testPaste(1,1, new Paster(){
			@Override
			public void paste() {
				_logic.PasteFigure();				
			}			
		});		
	}
	
	@Test
	public void processFieldTest() {
		int[][] fd = _logic.getState().getField().getData();
		fd[5][5] = 1;
		fd[4][4] = 1;
		fd[3][3] = 1;
		assertTrue(_logic.processField(/*5,5,4,4,3,3*/));
	}
	
// Here I realize, that had I started with Tests, logic could have slightly differed.
//	For example, here I can't test packField directly, because _Fold is created in
// processField() function	
	@Test
	public void packFieldTest() {
		int[][] fd = _logic.getState().getField().getData();
		int d = Field.Depth;
		fd[d][1] = fd[d][2] = fd[d][3] = 1;
		fd[d-1][1] = 3; fd[d-1][2] = 4; fd[d-1][3] = 5;
		_logic.processField();
		_logic.PackField();

		assertEquals(3, fd[d][1]);
		assertEquals(4, fd[d][2]);
		assertEquals(5, fd[d][3]);
	}
}