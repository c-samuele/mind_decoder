/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameImpl implements Game {
	
	private GameMode mode;
	private int level; 
	
	private int attempts;
	
	private List<Color> secretCode;
	
	private GameStats stats;
	
	
	
	// CONSTRUCTOR FOR GAMEMODE = SINGLE_PLAYER
	public GameImpl(GameMode mode,int level) {
		this.mode = mode;
		this.level = level;
		this.attempts = 10 + (level * 2);
		this.secretCode = makeSecretCode(level);
	}
	// CONSTRUCTOR FOR GAMEMODE = MULTY_PLAYER OR AI_CHALLENGE
	public GameImpl(GameMode mode) {
		this.mode = mode;
	}	
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void end() {
		
	}
	
	public List<Color> makeSecretCode(int level) {
		System.out.print("Number of color for level:\t" + numberOfColors(level) + "\n");
		
		List<Color> allColors = new ArrayList<>(Arrays.asList(Color.values()));
		
		System.out.print("All colors:\t\t" + allColors + "\n");
		
		List<Color> availableColors = allColors.subList(0,numberOfColors(level));
		
		System.out.print("Availables colors:\t" + availableColors + "\n");
		Collections.shuffle(availableColors);

		return availableColors;
	}
	
	public List<Color> getSecretCode(){
		System.out.println("Secret Code:\t\t" + secretCode);
		return secretCode;
	}

	@Override
	public void makeAttempt(Code codeAttempt) throws IllegalStateException {
		if(attempts > 0) {
			this.attempts--;
			System.out.println("Attempts code:\t\t" + codeAttempt.getColor());
		} else throw new IllegalStateException("No attempt remaining");
	}

	@Override
	public boolean isOver() {
		
		return false;
	}

	@Override
	public boolean isWon() {
		
		return false;
	}

	@Override
	public int getRemainingAttempts() {
		System.out.println("Attempts remaining:\t" + attempts);
		return attempts;
	}

	@Override
	public void getHints() {

		
	}

	@Override
	public GameStats getGameStats() {

		return null;
	}

	@Override
	public GameStats generateStats() {
		return stats;
	}


	@Override
	public int getLevel() {
		return this.level;
	}

	
	public int numberOfColors(int level) {
		if(level<=9)
			return level+=2;
		else
			return 12;
	}
	
}
