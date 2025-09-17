/**
 * Class CodeImpl.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Override 
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
	    if (!(obj instanceof Code)) 
	    	return false;
	    
	    Code code2 = (Code) obj;
	    
	    return Objects.equals(this.getColor(), code2.getColor());
		
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(code);
	}
}
