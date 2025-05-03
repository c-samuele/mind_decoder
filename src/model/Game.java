package model;

public interface Game {
	public void start();
	public void makeAttempt(Code c);
	public boolean isOver();
	public boolean isWon();
	public int getRemainingAttempts();
	public void getHints(); // List<Hint>
	public SecretCode getSecretCode();
	public GameStats getGameStats();
	
	
}
