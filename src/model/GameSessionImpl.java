package model;

import java.util.List;

public class GameSessionImpl implements GameSession {
	
	
	public GameSessionImpl() {
	
		Player ply = new PlayerImpl("Samuele");
		
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
