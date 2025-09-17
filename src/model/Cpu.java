/**
 * Cpu Interface
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;
import java.util.Map;

public interface Cpu {
	 /**
     * Initializes the matrix with colors and probabilities set to zero.
     */
    public void initMatrix();

    /**
     * Prints the matrix in tabular format.
     */
    public void printMatrix();

    /**
     * Adds the probabilities of the attempted color sequence to the matrix.
     *
     * @param attemptCode the attempted color sequence
     * @param hint the result obtained from the attempt
     */
    public void addAttempt(Code attemptCode, Hint hint);

    /**
     * Chooses the most probable attempt based on the matrix.
     *
     * @return the most probable sequence
     */
    public Code chooseAttempt();

    /**
     * Checks the uniqueness of an attempt.
     *
     * @param att the color sequence to check
     * @return true if the attempt was already made, false otherwise
     */
    public boolean ifContain(List<Color> att);

    /**
     * Generates a unique random attempt.
     *
     * @return the generated attempt
     */
    public Code makeUniqueRandomAttempt();

    /**
     * Returns the probability matrix.
     *
     * @return a map representing the probability matrix
     */
    public Map<Color, Integer[]> getMatrix();	
    
    public void decrementValues(List<Color> attempt);
    public List<Color> selectRamainingColors(List<Color> partialAttempt, List<Color> available) throws IllegalStateException;
}
