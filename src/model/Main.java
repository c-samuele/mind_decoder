/**
 * Main Class.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.List;
import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		
		System.out.print("|-- PLAYER CREATE\n");				Player p1 = new PlayerImpl("Samuele");	
		System.out.print("|-- SESSION CREATE \n");				Session tSession = new SessionImpl(p1, Optional.empty());
		System.out.print("|   |- GAME CREATE \n");			
		System.out.print("|   |  |- MAKE A SECRET CODE \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");
			
																tSession.createNewGame(GameMode.SINGLE_PLAYER,tSession.getUnlockedLevel());
																tSession.getCurrentGame().getSecretCode();
																tSession.getCurrentGame().getRemainingAttempts();
		
        System.out.print("\n|---|------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |---- MAKE ATTEMPT \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");
																List<Color> l1 = List.of(Color.GREEN, Color.RED, Color.BLUE); 
																List<Color> l2 = List.of(Color.BLUE, Color.RED, Color.GREEN); 
																Code c1 = new CodeImpl(l1);
																Code c2 = new CodeImpl(l2);
																tSession.getCurrentGame().makeAttempt(c1);
																tSession.getCurrentGame().getRemainingAttempts();
		System.out.print("\n|---|------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |---- MAKE ATTEMPT \n");
		System.out.print("|---|------------------------------------------------------------------------------------------------------|\n\n");
																tSession.getCurrentGame().makeAttempt(c2);
																tSession.getCurrentGame().getRemainingAttempts();
		System.out.print("\n|---|-------------------------------------------------------------------------------------------------------|\n\n");
																List<Hint> hints = tSession.getCurrentGame().getHints();
																System.out.printf("%-10s | %-12s%n", "Colore", "Indice");
																System.out.println("----------------------");
																for (Hint h : hints) 
																    System.out.printf("%-10d | %-12d%n", h.getColorCorrect(), h.getIndexCorrect());
		System.out.print("\n|---|-------------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |  |   |- IS WON \n");
		System.out.print("|   |  |   |- HINT CREATE\n");
		System.out.print("|   |  |   \n");
		System.out.print("|   |  |- END GAME\n");
		System.out.print("|   |  |  |- SAVE GAMESTATS\n");
     
	}

}
