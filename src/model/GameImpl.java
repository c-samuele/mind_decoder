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
	private GameStats stats;
	
	private int attemps;
	
	private List<Color> secretCode; 
	
	// CONSTRUCTOR FOR GAMEMODE = SINGLE_PLAYER
	public GameImpl(GameMode mode,int level) {
		this.mode = mode;
		this.level = level;
		this.attemps = 10 + (level * 2);
		this.secretCode = makeSecretCode(level);
	}
	// CONSTRUCTOR FOR GAMEMODE = MULTY_PLAYER OR AI_CHALLENGE
	public GameImpl(GameMode mode) {
		this.mode = mode;
	}	
	
	@Override
	public void start() {
		
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
	public void makeAttempt(Attempt attempt) {
		System.out.println("Attempts code:\t\t" + attempt.getColors());
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRemainingAttempts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getHints() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameStats getGameStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameStats generateStats() {
		return stats;
	}

	@Override
	public void end() {
		System.out.println("\n------ The game is end ------\n");
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
