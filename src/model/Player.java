package model;

public class Player implements User{
	
	private final String name;
	
	private int level;
	private int attemps;
	private int points;
	
	public static int ATTEMPS_BASE = 9;
	
	public Player(String name,int level) {
		this.name = name;
		this.level = level;
		this.attemps = level + ATTEMPS_BASE;
		this.points = 0;
	}
	
	// GETTER
	public String getName() {
		return this.name;
	}
	public int getLevel() {
		return this.level;
	}
	public int getAttemps() {
		return this.attemps;
	}
	@Override
	public int getPoints() {
		return this.points;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", level=" + level + ", attemps=" + attemps + ", points=" + points + "]\n";
	}
	

}
