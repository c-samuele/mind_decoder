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
	
	private List<Color> secretCode; 
	
	
	public GameImpl(GameMode mode,int level) {
		this.mode = mode;
		this.level = level;
		this.secretCode = this.makeSecretCode(this.level);
	}

	public GameImpl(GameMode mode) {
		this.mode = mode;
	}	
	
	@Override
	public void start() {
		System.out.println("\n------ The game is start ------\n");
	}
	
	public List<Color> makeSecretCode(int level) {
		System.out.print("--- MAKE A SECRET COD ---\n\n");
		System.out.print("[LEVEL GAME]\t"+this.level+"\n");
		System.out.print("[NUMBER OF COLORS FOR SECRETCODE]\t"+ this.numberOfColors(this.level)+"\n");
		
		List<Color> allColors = new ArrayList<>(Arrays.asList(Color.values()));
		
		System.out.print("ALL COLORS: "+allColors+"\n");
		
		List<Color> availableColors = allColors.subList(0,this.numberOfColors(level));
		
		System.out.print("AVAILABLE COLORS: "+availableColors+"\n");
		Collections.shuffle(availableColors);
		
		System.out.print("-----------------------------------------------\n\n");
		return availableColors;
	}
	
	public List<Color> getSecretCode(){
		return this.secretCode;
	}

	@Override
	public void makeAttempt(Code c) {
		// TODO Auto-generated method stub
		
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
