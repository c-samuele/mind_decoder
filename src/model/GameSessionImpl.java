package model;

import java.util.ArrayList;
import java.util.List;

public class GameSessionImpl implements GameSession {
	
	private final Player firstPlayer;						// Giocatore 1
	
	private final int MAX_LEVEL = 12;						// Livello massimo del gioco
	private int level;										// Livello corrente
	
	private List<GameStats> gameStats = new ArrayList<>();	// Storico Game passati
						
	private Game game;										// Riferimento a game
	private boolean gameActive;								// flag
	
//	costruttore iniziale
	public GameSessionImpl(Player p) {
		this.gameActive = false;
		this.firstPlayer = p;
		this.level = 1;
	}
	
//	Se ho già partite pregresse
	public GameSessionImpl(Player p,GameStats g) {
		firstPlayer = p;
		level = g.getLevel();
		
	}
	
	@Override
	public Player getPlayer() {
		return firstPlayer;
	}
	
	@Override
	public int getLevel() {
		return level;
	}
	
	@Override
	public int getMaxLevel() {
		return MAX_LEVEL;
	}
	
	@Override
	public int getUnlockedLevel() {
		return this.level;
	}

	@Override
	public void unlockNextLevel() {
		if(this.level < MAX_LEVEL)
			this.level++;
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

	
	

}
