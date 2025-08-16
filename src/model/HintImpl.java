/**
 * HintImpl Class.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */


package model;

public class HintImpl implements Hint{

	private int colorCorrect,
				indexCorrect;
	
	public HintImpl(int color,int index) {
		this.colorCorrect = color;
		this.indexCorrect = index;
	}
	
	@Override
	public int getColorCorrect() {
		return colorCorrect;
	}

	@Override
	public int getIndexCorrect() {
		return indexCorrect; 
	}

}
