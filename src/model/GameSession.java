package model;

import java.util.List;

public interface GameSession {
	public void startNewGame(GameMode mode);
	public GameImpl getCurrentGame();
	public boolean hasAcriveGame();
	public int getUnlockedLevel();
	public void unlockNextLevel();
	public List<GameStats> getGameHistory();
	public GameStats getBestScore();
	public boolean loadSession();
	public boolean saveSession();
	public boolean resetSession();
}
