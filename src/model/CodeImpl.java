/**
 * Class CodeImpl.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.List;

public class CodeImpl implements Code {
	
	private List<Color> code;	 
	
	public CodeImpl(List<Color> colors){
		code = new ArrayList<Color>(colors);
	}

	@Override
	public List<Color> getColor() {
		return List.copyOf(code);
	}
	
	@Override
	public Color getColorByIndex(int index) {
		return code.get(index);
	}

	@Override
	public int size() {
		return code.size();
	}

}
