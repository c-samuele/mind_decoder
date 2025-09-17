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
	
	
	public boolean equals(Object obj) {
	    if (this == obj) 
	    	return true; 
	    
	    if (!(obj instanceof PlayerImpl)) 
	    	return false; 
	    
	    PlayerImpl p2 = (PlayerImpl) obj;
	    return this.getName().equals(p2.getName());
	}
	
	@Override
	public int hashCode() {
	    return getName().hashCode();
	}

}
