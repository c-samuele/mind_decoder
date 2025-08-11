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
	public List<Color> makeSecretCode(int level);
	/**
	 * For make Attempt
	 * @param Attempt's code
	 */
	public void makeAttempt(Code c);
	public List<Color> getSecretCode();
	public boolean isOver();
	public boolean isWon();
	public int getRemainingAttempts();
	public void getHints(); // List<Hint>
	public GameStats getGameStats();
	public GameStats generateStats();
}
