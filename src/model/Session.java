/**
 * Interface GameSession.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;
import java.util.List;
import java.util.Optional;

public interface Session {
	
	/**
	 * Method for start new game - single player
	 * 
	 * @param mode mode of game
	 * @param level of game
	 */
	public void createNewGame(GameMode mode,int level);
	
	/**
	 * Method for start new game - multy_player or Ai_challenge
	 * 
	 * @param mode mode of game
	 * @param level of game
	 */
	public void createNewGame(GameMode mode);
	
	/**
	 * Set current game
	 * 
	 * @param game
	 */
	public void setCurrentGame(Game game);
	
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
	 * method for add the current gameStats to the list of gameStats
	 */
	public void addGameStats(GameStats stats);
	
	/**
	 * Get the best score of session
	 * 
	 * @return best score of the session current
	 */
	public int getBestScore();
	public int getTimeAvg();
	public int getAttemptsAvg();
	
	/**
	 * Get the current first player
	 * 
	 * @return the current first player
	 */
	public Player getFirstPlayer();
	
	/**
	 * Get the max level of the session
	 * 
	 * @return max level of the session
	 */
	public int getMaxLevel();
	
	public boolean loadSession();
	public boolean saveSession();
	public boolean resetSession();
	
	/**
	 * Method for print the log of session
	 */
	public void sessionLog();
	
}
