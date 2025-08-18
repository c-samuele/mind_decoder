/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;

public interface Game {
	
	/**
	 * For Start the game
	 */
	public void start();
	
	/**
	 * For End the game
	 */
	public void end();
	
	/**
	 * Get current Level
	 * 
	 * @return the level
	 */	
	public int getLevel();
	
	/**
	 * For make a secret code
	 * @param level
	 * @return the secret code
	 */
	public Code makeSecretCode(int level);
	
	/**
	 * For make Attempt
	 * 
	 * @param Attempt's code
	 */
	public void makeAttempt(Code codeAttempt);
	
	/**
	 * Getter for secretCode
	 * 
	 * @return SecretCode
	 */
	public Code getSecretCode();
	
	/**
	 * Verifies how many colors in the given attempt exist in the secret code.
	 *
	 * @param codeAttempt the code to check
	 * @return the number of correct colors
	 * @throws IllegalArgumentException if the number of colors in the attempt is greater or smaller than expected
	 */
	public int verifyColor(Code codeAttempt) throws IllegalArgumentException;
	
	
	public int verifyIndex(Code codeAttempt) throws IllegalArgumentException;
	
	/**
	 * 
	 * @return
	 */
	public boolean isWon(int indexCorrect,int numberOfColors);
	
	/**
	 * 
	 * @return
	 */
	public boolean isOver();
	
	
	
	
	public int getRemainingAttempts();
	
	
	public List<Hint> getHints();
	
	
	public GameStats getGameStats();
	
	
	public GameStats generateStats();
	
	
}
