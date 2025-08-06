/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.List;

public class CodeImpl implements Code {
	
	/**
	 * secretCode sequence of code
	 * */
	private List<Color> secretCode;	 
	
	/**
	 * @param colors variable sequence of secret colors 
	 */
	CodeImpl(Color...colors){
		secretCode = new ArrayList<Color>(List.of(colors));
	}

	@Override
	public List<Color> getColor() {
		return List.copyOf(secretCode);
	}
	
	@Override
	public Color getColorByIndex(int index) {
		return secretCode.get(index);
	}

}
