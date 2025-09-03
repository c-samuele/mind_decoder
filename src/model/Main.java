/**
 * Main Class.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		System.out.print("|-- PLAYER CREATE\n");				Player p1 = new PlayerImpl("Samuele");	
		System.out.print("|-- SESSION CREATE \n");				Session tSession = SessionImpl.getInstance(p1);
		System.out.print("|   |- GAME CREATE \n");			
		System.out.print("|   |  |- MAKE A SECRET CODE \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");
			
																tSession.createNewGame(GameMode.SINGLE_PLAYER,tSession.getUnlockedLevel());
																tSession.getCurrentGame().getSecretCode();
																tSession.getCurrentGame().getRemainingAttempts();
		
        System.out.print("\n|---|------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |---- MAKE ATTEMPT \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");
																List<Color> l1 = List.of(Color.GREEN, Color.GREEN, Color.BLUE); 
																List<Color> l2 = List.of(Color.BLUE, Color.BLUE, Color.GREEN);
																
																tSession.getCurrentGame().setColorCurrentAttempt(0, Color.RED);
																tSession.getCurrentGame().setColorCurrentAttempt(1, Color.GREEN);
																tSession.getCurrentGame().setColorCurrentAttempt(2, Color.BLUE);
																Code attemptCode = new CodeImpl(Arrays.asList(tSession.getCurrentGame().getCurrentAttempt()));
																tSession.getCurrentGame().makeAttempt(attemptCode);
																
																
																Code wrong1 = new CodeImpl(l1);
																Code wrong2 = new CodeImpl(l2);
																tSession.getCurrentGame().makeAttempt(wrong1);
																tSession.getCurrentGame().getRemainingAttempts();
		System.out.print("\n|---|------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |---- MAKE ATTEMPT \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");
																tSession.getCurrentGame().makeAttempt(wrong2);
																tSession.getCurrentGame().getRemainingAttempts();
		System.out.print("\n|---|------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |---- MAKE ATTEMPT \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");															
																Code correctCode = tSession.getCurrentGame().getSecretCode();
																tSession.getCurrentGame().makeAttempt(correctCode);
																
																List<Hint> hints = tSession.getCurrentGame().getHints();
																
		System.out.printf("%-10s | %-12s%n", "correctColor", "correctIndex");
		System.out.println("----------------------");
		for (Hint h : hints) 
		    System.out.printf(" %-10d  | %-12d%n", h.getColorCorrect(), h.getIndexCorrect());
		System.out.print("\n|---|-------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |  |- END GAME\n");
		System.out.print("|   |  |  |- SAVE GAMESTATS\n");
		System.out.print("|   |  |  |- PRINT GAMESTATS\n");
		System.out.println("BEST SCORE: " + tSession.getBestScore());
		System.out.println("AVG ATTEMPTS: " + tSession.getAttemptsAvg());
		
     
	}

}
