package model;

import java.util.List;

public class GameSessionImpl implements GameSession {
	private final Player firstPlayer;
	private GameMode mode;
	private GameStats stats;
	private int level;
	
	public GameSessionImpl(Player p,GameMode m,GameStats s) {
		this.firstPlayer = p;
		this.mode = m;
		this.stats = s;
		this.level = 1;
	}
	
	@Override
	public Player getPlayer() {
		return firstPlayer;
	}
	
	@Override
	public GameMode getMode() {
		return mode;
	}
	
	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void startNewGame(GameMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameImpl getCurrentGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAcriveGame() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUnlockedLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unlockNextLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GameStats> getGameHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameStats getBestScore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadSession() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveSession() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetSession() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
