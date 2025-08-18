/**
 * Hints Class.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.List;

public class Hints {
	
	private List<Hint> hints;
	
	public Hints() {
		this.hints = new ArrayList<Hint>();
	}
	
	public void addHint(Hint currentHint) {
		this.hints.add(currentHint);
	}

	public List<Hint> getAllHints() {
		return this.hints;
	}
	
	public Hint getLastHint() {
		return hints.getLast();
	}
	
	public Hint getHintByIndex(int index) {
		return hints.get(index);
	}
	
}
