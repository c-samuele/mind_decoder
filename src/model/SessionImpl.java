/**
 * Class implementing the gameSession.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionImpl implements Session {
	
	private final Player firstPlayer;						// 1° player
	private final Optional<Player> secondPlayer; 			// 2° player
	
	private final int MAX_LEVEL = 12;						// Max Level
	private int unlockedLevel;
	private int currentLevel;	
	// Current Level
	
	private List<GameStats> gameStats = new ArrayList<>();	// Game stats
						
	private Game game;										// Current Game
	private boolean gameActive;								// Active game
	
	/**
	 * Constructor for the Session
	 * 
	 * @param first player
	 * @param second player optional
	 */
	public SessionImpl(Player p1,Player p2) {
		this.gameActive = false;
		this.firstPlayer = p1;
		this.secondPlayer = Optional.of(p2);
	}
	
	@Override
	public Player getPlayer() {
		return firstPlayer;
	}
	
	@Override
	public int getLevel() {
		return this.unlockedLevel;
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

	@Override
	public void startNewGame(GameMode mode,int level) {
		game = new GameImpl(mode,level);
		gameActive = true;
		
	}
	
	public void endGame() {
	    if (game == null) throw new IllegalStateException("Game doesn't exist.");
	    gameStats.add(game.generateStats());
	    game = null;
	    gameActive = false;
	}
	

	@Override
	public List<GameStats> getGameStats() {
		return gameStats;
	}

	@Override
	public Game getCurrentGame() {
		return this.game;
	}

	@Override
	public boolean hasActiveGame() {
		return this.gameActive;
	}

	
	
	
// ------------	DA FARE IN SEGUITO ------------------
	
	@Override
	public GameStats getBestScore() {
		
		return null;
	}

	@Override
	public boolean loadSession() {
		
		return false;
	}

	@Override
	public boolean saveSession() {
		return false;
	}

	@Override
	public boolean resetSession() {
		return false;
	}

	@Override
	public void startNewGame(GameMode mode) {
		// TODO Auto-generated method stub
		
	}

	
	

}
