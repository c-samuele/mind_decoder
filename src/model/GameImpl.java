/**
 * Class implementing the Code interface.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameImpl implements Game {
	
	private GameMode mode; // mode of game
	private GameState state; // state of game
	
	private int level; // current level
	
	private final int maxAttempts;	// max num of attempts for level
	private int attemptsRemaining;	// attempts remaining
	
	private Code secretCode;	// secret code
	
	private int nColors;	// num of color for secretCode
	
	private GameStats stats;	// stats of current game
	
	private Hints hints;	// hint for current attempts
	
	private int time;	// time of current game
	
	private List<Color> availableColors;
	private Color[] currentAttempt;	// current attempt
	
	private int currentAttemptRow = 0; 

	
	
	// CONSTRUCTOR FOR GAMEMODE = SINGLE_PLAYER
	public GameImpl(GameMode mode,int level) {
		this.state = GameState.PLAYING;
		this.mode = mode;
		this.level = level;
		this.nColors = numberOfColors();
		this.attemptsRemaining = calculateAttempts(level); 
		this.hints = new Hints();
		this.stats = new GameStatsImpl();
		this.maxAttempts = attemptsRemaining;
		this.time = 0;
		this.currentAttempt = new Color[nColors];
		this.availableColors = new ArrayList<>(Arrays.asList(Color.values()).subList(0, numberOfColors()));
		this.secretCode = makeSecretCode(level);

	}
		
	
	public Code generateRandomAttempt() {
		List<Color> available = new ArrayList<Color>(availableColors); 
		Collections.shuffle(available);

		return new CodeImpl(available);
	}
	

	@Override
	public GameState getState() {
		return state;
	}
	
	@Override
	public int getCurrentAttemptRow() {
	    return currentAttemptRow;
	}
	
	public void nextAttemptRow() {
		currentAttemptRow++;	
	}
	
	@Override
	public void setColorCurrentAttempt(int position, Color color) {
		if(position < 0 || position >= nColors)
			throw new IllegalArgumentException("Position out of bounds");
		currentAttempt[position] = color;
	}
	
	public Color[] getCurrentAttempt() {
		return currentAttempt;
	}
	
	@Override
	public Color getColorCurrentAttempt(int position) {
	    if(position < 0 || position >= currentAttempt.length) 
	        throw new IllegalArgumentException("Position out of bounds");
	    return currentAttempt[position];
	}
	@Override
	public boolean isCurrentAttemptFull() {
	    for(Color c : currentAttempt) {
	        if(c == null) 
	        	return false;
	    }
	    return true;
	}
	@Override
	public void resetCurrentAttempt() {
	    Arrays.fill(currentAttempt, null);
	}
	
	@Override
	public List<Color> getAvailableColors() {
	    return Collections.unmodifiableList(availableColors);
	}
	
	public void removeAvailableColor(Color c) {
	    availableColors.remove(c);
	}
	
	public void resetAvailableColors() {
	    availableColors = new ArrayList<>(Arrays.asList(Color.values()).subList(0, numberOfColors()));
	}
	
	
	public Code makeSecretCode(int level) {
		List<Color> secretCodeColors = new ArrayList<>(availableColors);

		Collections.shuffle(secretCodeColors);
		
		Code secretCode = new CodeImpl(secretCodeColors);
	
		return secretCode;
	}
	
	public Code getSecretCode(){
		return secretCode;
	}

	@Override
	public void makeAttempt(Code codeAttempt) throws IllegalStateException {
		
		if(state != GameState.PLAYING || attemptsRemaining == 0) {
		    state = GameState.LOSE;
		    throw new IllegalStateException("Game is over, no more attempts allowed.");
		}
		
		Hint currentHint; 		/* Current hint to add to the hints list */
		
		int colorCorrect,		/* Number of correct colors for gameStats */
			indexCorrect = 0;	/* Number of color and index correct for gameStats */
		
		this.attemptsRemaining--;
		System.out.println("\nAttempts code:\t\t" + codeAttempt.getColor() + "\n");
		
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
			
			if(mode != GameMode.AI_CHALLENGE) {
				// GENERATE GAMESTATS
				this.generateStats();
				SessionImpl.getInstance().addGameStats(stats);
			}
			// CHANGE STATE
			state = GameState.WIN;
			
			// LOG
			System.out.println("You win!");
			System.out.print("\ntime:"+getTime()+"\n");
			System.out.print("attempts remaining:"+getRemainingAttempts()+"\n");
			
			// EXIT THE GAME
		}
			
		// Lose check
		if(isOver()) {
			
			if(mode != GameMode.AI_CHALLENGE) {
				// GENERATE GAMESTATS
				this.generateStats();
				SessionImpl.getInstance().addGameStats(stats);
			}
			// CHANGE STATE
			state = GameState.LOSE;
			
			// LOG
			System.out.println("Game over!");
			
			// EXIT THE GAME
		}
		
		// GENERATE AND ADD HINTS TO LIST
		currentHint = new HintImpl(colorCorrect,indexCorrect);
		hints.addHint(currentHint);
		
	}
	
	@Override
	public int calculateAttempts(int level) {
		return (16 + (level * 2));
	}
	
	
	@Override
	public int verifyColor(Code codeAttempt) throws IllegalArgumentException {
		
		int numberOfColors = this.numberOfColors();
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
		
		int numberOfColors = this.numberOfColors();
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
		if(this.attemptsRemaining == 0)
			return true;
		else 
			return false;
	}

	@Override
	public int getRemainingAttempts() {
		return attemptsRemaining;
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
		
		attemptsUsed = (maxAttempts - attemptsRemaining);
		score = attemptsRemaining * 10;
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

	@Override
	public int numberOfColors() {
		if(level<=9)
			return level + 2;
		else
			return 12;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public GameMode getMode() {
		return mode;
	}
	
	

}
