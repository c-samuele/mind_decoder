/**
 * Class Attempt.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;

public class Attempt {

	private List<Color> attemptCode;
	
	
	public Attempt(List<Color> att) {	
		attemptCode = att;
	}
	
	public List<Color> getColors() {
		return attemptCode;
	}
	
	
}
