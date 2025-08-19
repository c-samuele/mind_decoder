package model;

public class GameStatsImpl implements GameStats {

	private int score;
	private int time;
	private int AttemptsUsed;
	
	public GameStatsImpl() {
		this.AttemptsUsed = 0;
		this.score = 0;
		this.time = 0;
	}

	@Override
	public int getScore() {
		return this.score;
	}
	
	@Override
	public int getTime() {
		return this.time;
	}

	@Override
	public int getAttemptsUsed() {
		return this.AttemptsUsed;
	}


	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public void setAttemptsUsed(int att) {
		this.AttemptsUsed = att;		
	}
	
}
