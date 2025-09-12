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
	 * 
	 * @return state of the current game (PLAYNG,WIN,LOSE)
	 */
	public GameState getState();
	
	/**
	 * Get current Level
	 * 
	 * @return the level
	 */	
	public int getLevel();
	
	/**
	 * 
	 * @return available Colors
	 */
	public List<Color> getAvailableColors();
	
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
	 * 
	 * @param level of current game
	 * @return number of attempt to use
	 */
	public int calculateAttempts(int level);
	
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
	public int getCurrentAttemptRow();
	public void setColorCurrentAttempt(int position, Color color);
	public Color getColorCurrentAttempt(int position);
	public boolean isCurrentAttemptFull();
	public void resetCurrentAttempt();
	public Color[] getCurrentAttempt();
	public int numberOfColors();
	public Code generateRandomAttempt();
	
	
	public int getRemainingAttempts();
	
	
	public List<Hint> getHints();
	
	
	public GameStats getGameStats();
	
	
	public void generateStats();
	
	public int getTime();
	
}
