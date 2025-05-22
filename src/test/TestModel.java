package test;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class TestModel {

	private String name = "player1";
	private GameMode mode;
	private GameSession session;
	
	@Before
	public void setUp() {
		Player player = new PlayerImpl(name);
		mode = GameMode.SINGLE_PLAYER;
		GameStats stats = new GameStats();
		session = new GameSessionImpl(player,stats);
	}
	
	@Test
	public void testBasicSession() {
		assertFalse(session.hasActiveGame());
		assertEquals(name,session.getPlayer().getName());
		assertEquals(1,session.getLevel());
	}
	
	@Test
	public void testSessionLevel() {
		while (session.getLevel() < session.getMaxLevel())
			session.unlockNextLevel();
		assertThrows(IllegalStateException.class, () -> session.unlockNextLevel());
	}
	
	@Test
	public void testGame() {
		
	}
	
	@Test
	public void testCode() {
		
	}
	
	
	


}
