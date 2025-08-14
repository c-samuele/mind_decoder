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
	
	int nColors;
	
	private GameStats stats;
	
	
	// CONSTRUCTOR FOR GAMEMODE = SINGLE_PLAYER
	public GameImpl(GameMode mode,int level) {
		this.mode = mode;
		this.level = level;
		this.nColors = numberOfColors(level);
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
		
		int colorCorrect,		/* Number of correct colors for gameStats */
			indexCorrect = 0;	/* Number of color and index correct for gameStats */
		
		if(attempts > 0) {
			this.attempts--;
			System.out.println("Attempts code:\t\t" + codeAttempt.getColor());
		
		// Color check
		colorCorrect = verifyColor(codeAttempt);	
		System.out.println("Number of correct colors: " + colorCorrect);
		
		// Index check
		indexCorrect = verifyIndex(codeAttempt);
		System.out.println("Number of correct index: " + indexCorrect);
		
		/* Win check 
		 * IMPORTANT FIRST BY LOSE CHECK FOR ATTEMPT = 0
		 */
		if(isWon(indexCorrect,this.nColors)) {
			// GENERATE GAMESTATS
			System.out.println("You win!");
			// EXIT THE GAME
		}
			
		// Lose check
		if(isOver()) {
			// GENERATE GAMESTATS
			System.out.println("Game over!");
			// EXIT THE GAME
		}
		
		// GENERATE HINTS
			
		
		} else throw new IllegalStateException("No attempt remaining");

	}
	
	
	@Override
	public int verifyColor(Code codeAttempt) throws IllegalArgumentException {
		
		int numberOfColors = this.numberOfColors(this.level);
		int colorCorrect = 0; 
		
		if(codeAttempt.size() != numberOfColors)
			throw new IllegalArgumentException("The code contains an incorrect number of colors");
		
		for(int i = 0;i < numberOfColors; i++) {
			if(this.secretCode.contains(codeAttempt.getColorByIndex(i)))
				colorCorrect++;
		}
		return colorCorrect;
	}
	
	@Override
	public int verifyIndex(Code codeAttempt) throws IllegalArgumentException {
		
		int numberOfColors = this.numberOfColors(this.level);
		int indexCorrect = 0;
		
		if(codeAttempt.size() != numberOfColors)
			throw new IllegalArgumentException("The code contains an incorrect number of colors");
		
		for(int i = 0; i < numberOfColors; i++)
			if(codeAttempt.getColorByIndex(i).equals(this.secretCode.get(i)))
				indexCorrect++;
		
		return indexCorrect;
	}

	@Override
	public boolean isWon(int indexCorrect,int numberOfColors) {
		if(indexCorrect == numberOfColors)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean isOver() {
		if(this.attempts == 0)
			return true;
		else 
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
			return level + 2;
		else
			return 12;
	}
	
}
