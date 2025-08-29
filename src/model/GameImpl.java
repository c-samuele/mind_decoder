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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameImpl implements Game {
	
	private GameMode mode;
	
	private GameState state;
	
	private int level; 
	
	private final int maxAttempts;
	private int attempts;
	
	private Code secretCode;
	
	private int nColors;
	
	private GameStats stats;
	
	private Hints hints;
	
	private StopWatch stopWatch;
	
	
	// CONSTRUCTOR FOR GAMEMODE = SINGLE_PLAYER
	public GameImpl(GameMode mode,int level) {
		this.state = GameState.PLAYING;
		this.mode = mode;
		this.level = level;
		this.nColors = numberOfColors(level);
		this.attempts = calculateAttempts(level);
		this.secretCode = makeSecretCode(level); 
		this.hints = new Hints();
		this.stats = new GameStatsImpl();
		this.maxAttempts = attempts; // dopo dell'init di attempts poichè dipende da esso
		this.stopWatch = new StopWatch();
		Code secret = getSecretCode();
		stopWatch.start(); // start stopWatch
	}
	
	// CONSTRUCTOR FOR GAMEMODE = MULTY_PLAYER OR AI_CHALLENGE
//	public GameImpl(GameMode mode) {
//		this.mode = mode;
//	}	
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void end() {
		
	}
	
	@Override
	public List<Color> getAvailableColors(List<Color> allColors,int level){
		return allColors.subList(0,numberOfColors(level));
	}
	
	
	public Code makeSecretCode(int level) {
		System.out.print("Number of color for level:\t" + numberOfColors(level) + "\n");
		
		List<Color> allColors = new ArrayList<>(Arrays.asList(Color.values()));
		
		System.out.print("All colors:\t\t" + allColors + "\n");
		
		List<Color> availableColors = getAvailableColors(allColors,level);
		
		System.out.print("Availables colors:\t" + availableColors + "\n");
		Collections.shuffle(availableColors);
		
		Code secretCode = new CodeImpl(availableColors);
	
		return secretCode;
	}
	
	public Code getSecretCode(){
		System.out.println("Secret Code:\t\t" + secretCode.getColor());
		return secretCode;
	}

	@Override
	public void makeAttempt(Code codeAttempt) throws IllegalStateException {
		
		if(state != GameState.PLAYING || attempts == 0) {
		    state = GameState.LOSE;
		    throw new IllegalStateException("Game is over, no more attempts allowed.");
		}
		
		Hint currentHint; 		/* Current hint to add to the hints list */
		
		int colorCorrect,		/* Number of correct colors for gameStats */
			indexCorrect = 0;	/* Number of color and index correct for gameStats */
		
		this.attempts--;
		System.out.println("Attempts code:\t\t" + codeAttempt.getColor() + "\n");
		
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
			stopWatch.stop();
			// GENERATE GAMESTATS
			this.generateStats();
			SessionImpl.getInstance().addGameStats(stats);
			// CHANGE STATE
			state = GameState.WIN;
			
			// LOG
			System.out.println("You win!");
			
			// EXIT THE GAME
		}
			
		// Lose check
		if(isOver()) {
			// GENERATE GAMESTATS
			this.generateStats();
			SessionImpl.getInstance().addGameStats(stats);
			// CHANGE STATE
			state = GameState.LOSE;
			
			// LOG
			System.out.println("Game over!");
			
			// EXIT THE GAME
		}
		
		
		// GENERATE AND ADD HINTS TO LIST
		currentHint = new HintImpl(colorCorrect,indexCorrect);
		
		hints.addHint(currentHint);
		System.out.print("\nHINT:\n" +" |- Number of correct colors: " + currentHint.getColorCorrect() + "\n" +" |- Number of correct index: "+ currentHint.getIndexCorrect()+"\n\n");
			
	}
	
	@Override
	public int calculateAttempts(int level) {
		return (10 + (level * 2));
	}
	
	
	@Override
	public int verifyColor(Code codeAttempt) throws IllegalArgumentException {
		
		int numberOfColors = this.numberOfColors(this.level);
		int colorCorrect = 0; 
		
		if(codeAttempt.size() != numberOfColors)
			throw new IllegalArgumentException("The code contains an incorrect number of colors");
		
		for(int i = 0;i < numberOfColors; i++) {
			if(this.secretCode.getColor().contains(codeAttempt.getColorByIndex(i)))
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
			if(codeAttempt.getColorByIndex(i).equals(this.secretCode.getColorByIndex(i)))
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
	public List<Hint> getHints() {
		return this.hints.getAllHints();
	}

	@Override
	public void generateStats() {
		int attemptsUsed,
			time,
			score;
		
		attemptsUsed = (maxAttempts - attempts);
		score = attempts * 10;
		time = getTime();
		
		this.stats.setScore(score);
		this.stats.setAttemptsUsed(attemptsUsed);
		this.stats.setTime(time);
	}
	
	@Override
	public GameStats getGameStats() {
		return this.stats;
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
	
	public int getTime() {
		return stopWatch.getSecondsInt();
	}
	
	
	public StopWatch getStopWatch() {
		return this.stopWatch;
	}
	
}
