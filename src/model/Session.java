/**
 * Interface GameSession.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;

public interface Session {
	
	/**
	 * Method for start new game - single player
	 * 
	 * @param mode mode of game
	 * @param level of game
	 */
	public void startNewGame(GameMode mode,int level);
	
	/**
	 * Method for start new game - multy player or ai challenge
	 * 
	 * @param mode mode of game
	 * @param level of game
	 */
	public void startNewGame(GameMode mode);
	
	/**
	 * For end the game
	 * 
	 * for close current game
	 */
	public void endGame();
	
	/**
	 * Get the current game
	 * 
	 * @return a current game
	 */
	public Game getCurrentGame();
	
	/** 
	 * Game Active
	 * 
	 * @return true or false for the game status
	 */
	public boolean hasActiveGame();
	
	/**
	 * Get the unlocked level
	 * 
	 * @return the last level unlocked
	 */
	public int getUnlockedLevel();
	
	/**
	 * Unlock the next level
	 * 
	 * for unlocked the next level
	 */
	public void unlockNextLevel();
	
	/**
	 * Method for get the game stats
	 * 
	 * @return the list of game stats
	 */
	public List<GameStats> getGameStats();
	
	/**
	 * Get the best score of session
	 * 
	 * @return best score of the session current
	 */
	public GameStats getBestScore();
	
	/**
	 * Get the current player
	 * 
	 * @return the current player
	 */
	public Player getPlayer();
	
	/**
	 * Get the current level
	 * 
	 * @return level
	 */
	public int getLevel();
	
	/**
	 * Get the max level of the session
	 * 
	 * @return max level of the session
	 */
	public int getMaxLevel();
	
	
	public boolean loadSession();
	public boolean saveSession();
	public boolean resetSession();
	
}
