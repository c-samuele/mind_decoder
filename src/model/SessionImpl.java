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

public class SessionImpl implements Session,GameAction {
	
	private final int MAX_LEVEL = 12;						// Max Level
	
	private final Player firstPlayer;						// 1° player
	private final Optional<Player> secondPlayer; 			// 2° player
	
	private int unlockedLevel;
	
	private List<GameStats> gameStats = new ArrayList<GameStats>();
						
	private Game game;										// Current Game
	private boolean gameActive;								// Active game
	
	/**
	 * Constructor for the Session
	 * 
	 * @param first player
	 * @param second player optional
	 */
	public SessionImpl(Player p1,Optional<Player> p2) {
		this.unlockedLevel = 1;		// current unlocked level
		this.gameActive = false;	// status game
		this.firstPlayer = p1;		// player1
		this.secondPlayer = p2;		// player2
	}
	
	@Override
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	
	@Override
	public Optional<Player> getSecondPlayer() {
		return secondPlayer;
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
	public void createNewGame(GameMode mode,int level) {
		game = new GameImpl(mode,level);
		gameActive = true;
		
	}
	
	@Override
	public void createNewGame(GameMode mode) {
		game = new GameImpl(mode);
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
		return this.gameStats;
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
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
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
	public GameStats generateStats() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
