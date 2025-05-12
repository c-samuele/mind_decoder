package model;

import java.util.List;

public class GameSessionImpl implements GameSession {
	private final int MAX_LEVEL = 10;
	private final Player firstPlayer;
	private GameStats stats;
	private int level;
	private Game game;
	private boolean gameActive;
	
	
	public GameSessionImpl(Player p,GameStats s) {
		this.gameActive = false;
		this.firstPlayer = p;
		this.stats = s;
		this.level = 1;
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
	public List<GameStats> getGameHistory() {
		
		return null;
	}

	
	

}
