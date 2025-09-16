/**
 * Game interface
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
	
	/**
	 * 
	 * @param codeAttempt the code to check
 	 * @return the number of correct index and colors
	 * @throws IllegalArgumentException f the number of colors in the attempt is greater or smaller than expected
	 */
	public int verifyIndex(Code codeAttempt) throws IllegalArgumentException;
	
	/**
	 * 
	 * @param indexCorrect of the attempt
	 * @param numberOfColors of the secret code
	 * @return true if indexCorrect is equals to number of colors
	 */
	public boolean isWon(int indexCorrect,int numberOfColors);

    /**
     * Checks if the attempts are over.
     *
     * @return true if the attempts are equal to zero
     */
    public boolean isOver();

    /**
     * Returns the number of the current attempt row.
     *
     * @return the number of the current row
     */
    public int getCurrentAttemptRow();

    /**
     * Sets a color at a given position in the current attempt.
     *
     * @param position index for drag and drop
     * @param color color to place
     */
    public void setColorCurrentAttempt(int position, Color color);

    /**
     * Returns the color at the given position of the current attempt.
     *
     * @param position index of the position
     * @return the color at the specified position
     */
    public Color getColorCurrentAttempt(int position);

    /**
     * Checks if the current attempt sequence is complete.
     *
     * @return true if the sequence is complete
     */
    public boolean isCurrentAttemptFull();

    /**
     * Clears the current attempt.
     */
    public void resetCurrentAttempt();

    /**
     * Returns the sequence of colors of the current attempt.
     *
     * @return the array containing the current color sequence
     */
    public Color[] getCurrentAttempt();

    /**
     * Calculates the number of colors to use based on the level.
     *
     * @return the number of colors for the attempt/secret code
     */
    public int numberOfColors();

    /**
     * Generates a random attempt.
     *
     * @return an instance containing the random attempt
     */
    public Code generateRandomAttempt();

    /**
     * Increments the number of the attempt row.
     */
    public void nextAttemptRow();

    /**
     * Returns the number of remaining attempts.
     *
     * @return the number of remaining attempts
     */
    public int getRemainingAttempts();

    /**
     * Returns the list of generated hints.
     *
     * @return the list of hints of the game
     */
    public List<Hint> getHints();

    /**
     * Returns the statistics of the current game.
     *
     * @return an instance containing the statistics
     */
    public GameStats getGameStats();

    /**
     * Generates the game statistics.
     */
    public void generateStats();

    /**
     * Returns the elapsed game time.
     *
     * @return the number of seconds elapsed
     */
    public int getTime();

    /**
     * Returns the current game mode.
     *
     * @return the game mode
     */
    public GameMode getMode();
	
}
