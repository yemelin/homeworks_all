package columns;

public class View {
	private MyGraphics _graphics;
	private int saveX;
	private int saveY;

	public static int SL;//=25;
	public static int LeftBorder;//=2;
	public static int TopBorder;//=2;
    
	void setGraphics(final MyGraphics graphics) {
		_graphics = graphics;
	}
	
	public static void setParams (int w, int h) {
		if (w==0 || h==0) {
			SL=25;
			LeftBorder=TopBorder=2;
		}
		else {
			View.SL = w/(Field.Width+4);
			if (View.SL*(Field.Depth+4)>h)
				View.SL = h/(Field.Depth+4);
			LeftBorder = (w - (Field.Width*SL))/2;
			TopBorder = (h - (Field.Depth*SL))/2;;
		}
	}
	
	protected void DrawBox(int col, int row, int c) {
    	int x = LeftBorder+col*SL-SL;
    	int y= TopBorder+row*SL-SL;
    	int mwidth = (c==8) ? 2:0;	//white margin's width
    	_graphics.fillBox(x,y,SL,SL,c,mwidth);
    }
	 	
    void DrawField(int[][] data) {
    	
        int i,j;
        for (i=1; i<=Field.Depth; i++) {
            for (j=1; j<=Field.Width; j++) {
                DrawBox(j,i,data[i][j]);
            }
        }
        System.out.println("Field drawn");
//        Delay(1000);
    }
    void DrawFigure(Figure f, int x, int y) {
    	System.out.println("View.drawFigure from "+Thread.currentThread().getName());
    	saveX = x;
    	saveY = y;
        DrawBox(x,y,f.c[1]);
        DrawBox(x,y+1,f.c[2]);
        DrawBox(x,y+2,f.c[3]);
    }
    
    private void HideFigureAt(int saveX, int saveY) {
        DrawBox(saveX,saveY,0);
        DrawBox(saveX,saveY+1,0);
        DrawBox(saveX,saveY+2,0);   	
    }

    void moveFigure(Figure fig, int x, int y) {
    	HideFigureAt(saveX, saveY);
    	DrawFigure(fig, x, y);		
    }

    void DrawFigure (State state) {
    	DrawFigure(state.getFigure(), state.col, state.row);
    }
    
    void moveFigure(State state) {
    	moveFigure(state.getFigure(), state.col, state.row);    	
    }

	void HideFigure() {
		HideFigureAt(saveX,saveY);
	}
    
    protected void ShowLevel(State state) {
        _graphics.drawString("Level: "+state.getScore().Level,View.LeftBorder+100,400);
    }

    protected void ShowScore(State state) {
        _graphics.drawString("Score: "+state.getScore().Score,View.LeftBorder,400);
    }
}
