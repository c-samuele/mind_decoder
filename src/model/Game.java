/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

public interface Game {
	public void start();
	public void makeAttempt(Code c);
	public boolean isOver();
	public boolean isWon();
	public int getRemainingAttempts();
	public void getHints(); // List<Hint>
	public GameStats getGameStats();
	public GameStats generateStats();
}
