/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

public class GameImpl implements Game {
	
	private GameMode mode;
	private int level; 
	private GameStats stats;
	
	public GameImpl(GameMode mode,int level) {
		this.mode = mode;
		this.level = level;
	}

	@Override
	public void start() {
		
	}

	@Override
	public void makeAttempt(Code c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRemainingAttempts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getHints() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameStats getGameStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameStats generateStats() {
		return stats;
	}

}
