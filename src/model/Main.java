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
		System.out.print("|---|--|---------------------------------------------------------------------------------------------------|\n\n");
			
																tSession.createNewGame(GameMode.SINGLE_PLAYER,tSession.getUnlockedLevel());
																tSession.getCurrentGame().getSecretCode();
		
        System.out.print("\n|---|--|---------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |  |- MAKE ATTEMPT \n");
		System.out.print("|---|--|---|-----------------------------------------------------------------------------------------------|\n\n");
																Attempt a1 = new Attempt(List.of(Color.RED, Color.BLUE, Color.GREEN));
																tSession.getCurrentGame().makeAttempt(a1);
		System.out.print("\n|---|--|---|------------------------------------------------------------------------------------------------|\n");
		System.out.print("|   |  |   |- IS CORRET \n");
		System.out.print("|   |  |   |- HINT CREATE\n");
		System.out.print("|   |  |   \n");
		System.out.print("|   |  |- END GAME\n");
		System.out.print("|   |  |  |- SAVE GAMESTATS\n");
     
	}

}
