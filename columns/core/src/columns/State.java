package columns;

public class State {

	private Field _field;
	private Figure _figure;
	private Score _score;
	
	int col=Field.Width/2+1;
	int row=1;
 
	public class BundleData {
		int[][] Fnew;
		int[] figData;
		int col, row, score, level;
	}
	
	public Field getField() {
		return _field;
	}
	public int[][] getOldFieldData() {
		return getField().getOldData();
	}
	
	public void copyFieldData() {
		int[][] Fnew = getField().getData();
		int[][] Fold = getField().getOldData();
		for (int row=0;row<Fnew.length;row++)
			System.arraycopy(Fnew[row], 0, Fold[row], 0, Fnew[row].length);
	}
	public void setField(Field _field) {
		this._field = _field;
	}
	public Figure getFigure() {
		return _figure;
	}
	public void setFigure(Figure _figure) {
		this._figure = _figure;
		col = Field.Width/2+1;
		row = 1;
	}
	public Score getScore() {
		return _score;
	}
	public void setScore(Score _score) {
		this._score = _score;
	}
	
	public BundleData getBundleData() {
		BundleData bd = new BundleData();
// for brevity. Normally, should not expose the arrays, use copy instead
		bd.Fnew = getField().getData();
		bd.figData = getFigure().c;
		bd.level = _score.Level;
		bd.score = _score.Score;
		bd.col = col;
		bd.row = row;
		return bd;
	}
}
