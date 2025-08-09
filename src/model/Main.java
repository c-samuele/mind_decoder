/**
 * Main Class.
 * 
 * @author Samuele Caporale
 * @version 0.1.0
 */

package model;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		
		Player p1 = new PlayerImpl("Samuele");
		
		Session tSession = new SessionImpl(p1,Optional.empty());
		
		tSession.sessionLog();
		
//		// Test Unlock Next Level
//		for(int i =1;i<12;i++) {
//			tSession.unlockNextLevel();
//			System.out.println("[UNLOCKED LEVEL] \t" + tSession.getUnlockedLevel()+"/"+tSession.getMaxLevel());
//		}
	
		// Start new Single player Game
		tSession.createNewGame(GameMode.SINGLE_PLAYER,tSession.getUnlockedLevel());
		
		System.out.println("\n------ Create New Game ------ \n\n");
		
		tSession.sessionLog();
		
		tSession.getCurrentGame().start();
		
		tSession.getCurrentGame().makeSecretCode(tSession.getUnlockedLevel());
		
		
	}

}
