package model;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		
		Player p1 = new PlayerImpl("Samuele");
		
		Session tSession = new SessionImpl(p1,Optional.empty());
		
		System.out.println("[FIRST PLAYER] \t\t" + tSession.getFirstPlayer().getName());
		System.out.println("[SECOND PLAYER] \t" + tSession.getSecondPlayer());
		
		System.out.println("[UNLOCKED LEVEL] \t" + tSession.getUnlockedLevel()+"/"+tSession.getMaxLevel());
		
		System.out.println("[GAME ACTIVE] \t\t"+tSession.hasActiveGame());
		
//		// Test Unlock Next Level
//		for(int i =1;i<12;i++) {
//			tSession.unlockNextLevel();
//			System.out.println("[UNLOCKED LEVEL] \t" + tSession.getUnlockedLevel()+"/"+tSession.getMaxLevel());
//		}
	
		// Start new Single player Game
		tSession.startNewGame(GameMode.SINGLE_PLAYER,tSession.getUnlockedLevel());
		System.out.println("\n------ StartNewGame ------ \n\n"
						  +"[GAME ACTIVE] \t\t"+tSession.hasActiveGame());
			
		
		
	}

}
