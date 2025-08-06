/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;

public interface Code {
	
	/**
	 *  Returns the list of secret colors.
	 *  
	 * @return List of Colors
	 */
	public List<Color> getColor();
	
	/**
	 * Returns the color at the specified index.
	 * 
	 * @param index index of the color
     * @return the color at the given index
	 */
	public Color getColorByIndex(int index);
	
	
}
