/**
 * interface for the action game in session
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;

public interface GameAction {

	/**
	 * for start the game
	 */
	public void start();
	
	/**
	 * for end the game
	 */
	public void end();
	
	/**
	 * for make new attempt
	 * 
	 * @param code for current attempt
	 */
	public void makeAttempt(Code c);
	
	/**
	 * if over
	 * 
	 * @return
	 */
	public boolean isOver();
	
	/**
	 * if won
	 * 
	 * @return
	 */
	public boolean isWon();
	
	/**
	 * get attempts remaining
	 * 
	 * @return number of remaining attempts
	 */
	public int getRemainingAttempts();
	
	/**
	 * get the hints
	 * 
	 */
	public void getHints(); // List<Hint>
	
	/**
	 * for collect the stats
	 * 
	 * @return stats of the game
	 */
	public List<GameStats>getGameStats();
	
	/**
	 * generate the stats of the round
	 * 
	 * @return stats of current round game
	 */
	public GameStats generateStats();
	
}
