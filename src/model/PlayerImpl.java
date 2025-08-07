/**
 * Class PlayerImpl.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

public class PlayerImpl implements Player {
	
	private String name;
	
	public PlayerImpl(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
