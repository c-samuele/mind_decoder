package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import view.SessionView;

public class TestSession {
	
	private String name;
	private Player player;
	private Session session;

	@BeforeEach
	public void Setup() {
		SessionImpl.resetInstance(); 
		name = "Samuele";
		player = new PlayerImpl(name);
		session = SessionImpl.getInstance(player);
	}
	
	@Test
	public void singletonSameInstance() {
	    Session session2 = SessionImpl.getInstance();
	    assertSame(session, session2,"Singleton must always return the same instance.");
	}
	
	@Test 
	public void singletonNull() {
		SessionImpl.resetInstance();
		assertThrows(IllegalStateException.class, () -> {
			  SessionImpl.getInstance();
		
		},"Singleton session null");
	}
		
	@Test 
	public void  PlayerNotNull() {
		assertNotNull(session.getFirstPlayer());
	}
	
	@Test
	public void PlayerCorrect() {
		assertTrue(session.getFirstPlayer().equals(player));
	}
	
	@Test 
	public void unlockNextLevel() {
		while (session.getUnlockedLevel() < session.getMaxLevel()) {
	        session.unlockNextLevel();
	    }
		 assertThrows(IllegalStateException.class, () -> {
		        session.unlockNextLevel();
		    }, "Maximum level already reached.");
	}
	
	@Test 
	public void hasActiveGameStartSession() {
		assertFalse("There are active games when the session is created.",session.hasActiveGame());
	}
	
	@Test
	public void testSetCurrentGame() {
	    Game game = new GameImpl(GameMode.SINGLE_PLAYER, 1);
	    session.setCurrentGame(game);
	    assertEquals(game, session.getCurrentGame());
	}
	
	@Test
	public void testGameStats() {
	    assertEquals(0, session.getBestScore());
	    assertEquals(0, session.getTimeAvg());
	    assertEquals(0, session.getAttemptsAvg());
	}
	
	@Test
	public void testGameStatsGameWin() {
		Game game = new GameImpl(GameMode.SINGLE_PLAYER,1);
		session.setCurrentGame(game);
		game.makeAttempt(game.getSecretCode());// one attempt 
		assertEquals(1,session.getGameStats().getLast().getAttemptsUsed()); // expected 1 
	}

	
}




