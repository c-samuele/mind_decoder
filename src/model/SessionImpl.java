/**
 * Class implementing the gameSession.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SessionImpl implements Session {
	
	private static SessionImpl sessionInst; 
	
	private static final int MAX_LEVEL = 12;				
	
	private final Player firstPlayer;						
	
	private int unlockedLevel;
	
	private List<GameStats> gameStats = new ArrayList<GameStats>();
						
	private Game game;										// Current Game
	private boolean gameActive;								// Active game
	
	
	private SessionImpl(Player p1) {
		this.unlockedLevel = 1;		// current unlocked level
		this.gameActive = false;	// status game
		this.firstPlayer = p1;		// player1
	}
	
	/* Singleton */
	public static SessionImpl getInstance(Player p1) {
	    if (sessionInst == null) {
	    	sessionInst = new SessionImpl(p1); 
	    }
	    return sessionInst;
	}
	/* Get instance of session */
	public static SessionImpl getInstance() throws IllegalStateException {
	    if (sessionInst != null)
	    	return sessionInst;
	    else
	    	throw new IllegalStateException("Singleton session null");
	}

	
	@Override
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	
	@Override
	public int getMaxLevel() {
		return MAX_LEVEL;
	}
	
	@Override
	public int getUnlockedLevel() {
		return this.unlockedLevel;
	}

	@Override
	public void unlockNextLevel() {
		if(this.unlockedLevel < MAX_LEVEL)
			this.unlockedLevel++;
		else
			throw new IllegalStateException("Maximum level already reached.");
	}

	// for test
	@Override
	public void createNewGame(GameMode mode,int level) {
		game = new GameImpl(mode,level);
		gameActive = true;
		
	}
	

	@Override
	public List<GameStats> getGameStats() {
		return this.gameStats;
	}
	
	public void setCurrentGame(Game game) {
	    this.game = game;
	}

	@Override
	public Game getCurrentGame() {
		return this.game;
	}

	@Override
	public boolean hasActiveGame() {
		return this.gameActive;
	}
	
	@Override
	public int getBestScore() {
		int bestScore = 0;
		
		if(!gameStats.isEmpty())
			bestScore = gameStats.stream()
								.mapToInt(GameStats::getScore)
								.max()
								.getAsInt();
		
		return bestScore;
		
	}
	
	@Override
	public int getTimeAvg() {
		if(gameStats.size()==0)
			return 0;
		else {
		 int avgTime = (gameStats.stream()
								.mapToInt(GameStats::getTime)
								.sum()/gameStats.size());
		return avgTime;
		}
	}


	@Override
	public int getAttemptsAvg() {
		if(gameStats.isEmpty())
			return 0;
		else
			return  (gameStats.stream()
							.mapToInt(GameStats::getAttemptsUsed)
							.sum()
					)/gameStats.size();
	}

	@Override
	public void sessionLog() {
		System.out.println("[FIRST PLAYER] \t\t" + this.getFirstPlayer().getName());
		System.out.println("[UNLOCKED LEVEL] \t" + this.getUnlockedLevel() + "/" + this.getMaxLevel());
		System.out.println("[GAME ACTIVE] \t\t"  + this.hasActiveGame());
	}

	@Override
	public void addGameStats(GameStats stats) {
		gameStats.add(stats);
	}
	

}
