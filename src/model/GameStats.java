package model;

public interface GameStats {
	public int getBestScore();		// in points si parte da (10 * Attempt-1) points ed a ogni tentativo viene consumato un -10
	public int getBestTime();		// in minuti	
	public String getLog();			// serve?			
	
	
}
