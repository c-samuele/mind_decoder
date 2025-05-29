package model;

import java.util.List;

public interface GameSession {
	public void startNewGame(GameMode mode,int level);
	public void endGame();
	public Game getCurrentGame();
	public boolean hasActiveGame();
	public int getUnlockedLevel();
	public void unlockNextLevel();
	public List<GameStats> getGameStats();
	public GameStats getBestScore();
	public boolean loadSession();
	public boolean saveSession();
	public boolean resetSession();
	public Player getPlayer();
	public int getLevel();
	public int getMaxLevel();
	
}
