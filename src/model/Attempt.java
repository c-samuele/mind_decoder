/**
 * Class Attempt.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;

public class Attempt {

	private Code attemptCode;
	
	private int correctPosition;
	private int corretColors;
	
	
	public Attempt(Code attemptCode) {	
		this.attemptCode = attemptCode;
	}
	
	public Code getCode() {
		return attemptCode;
	}
	
	
}
