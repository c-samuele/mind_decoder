/**
 * Interface Session.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;
import java.util.List;
import java.util.Optional;

public interface Session {
	 /**
     * Sets the current game.
     *
     * @param game the game to set
     */
    public void setCurrentGame(Game game);

    /**
     * Returns the current game.
     *
     * @return the current game
     */
    public Game getCurrentGame();

    /**
     * Checks if there is an active game.
     *
     * @return true if a game is active, false otherwise
     */
    public boolean hasActiveGame();

    /**
     * Returns the last unlocked level
     *
     * @return the number of the last unlocked level
     */
    public int getUnlockedLevel();

    /**
     * Unlocks the next level
     */
    public void unlockNextLevel();

    /**
     * Returns the list of game statistics
     *
     * @return the list of statistics
     */
    public List<GameStats> getGameStats();

    /**
     * Adds the current game statistics to the list.
     *
     * @param stats the game statistics to add
     */
    public void addGameStats(GameStats stats);

    /**
     * Returns the best score of the session.
     *
     * @return the best score of the current session
     */
    public int getBestScore();

    /**
     * Returns the average game time.
     *
     * @return the average time
     */
    public int getTimeAvg();

    /**
     * Returns the average number of attempts of the games played
     *
     * @return the average number of attempts
     */
    public int getAttemptsAvg();

    /**
     * Creates a new game with the specified mode and level.
     *
     * @param mode the game mode
     * @param level the game level
     */
    public void createNewGame(GameMode mode, int level);

    /**
     * Returns the first player of the session.
     *
     * @return the first player
     */
    public Player getFirstPlayer();

    /**
     * Returns the maximum level available in the session.
     *
     * @return the maximum level
     */
    public int getMaxLevel();

    /**
     * Prints the session log.
     */
    public void sessionLog();
	
}
