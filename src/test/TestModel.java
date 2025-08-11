package test;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class TestModel {

	private String name = "player1";
	private GameMode mode;
	private Session session;
	
	@Before
	public void setUp() {
		Player player = new PlayerImpl(name);
		mode = GameMode.SINGLE_PLAYER;
		session = new SessionImpl(player,null);
	}
	
	@Test
	public void testBasicSession() {
		assertFalse(session.hasActiveGame());
		assertEquals(name,session.getFirstPlayer().getName());
		assertEquals(1,session.getUnlockedLevel());
	}
	
	@Test
	public void testSessionLevel() {
		while (session.getUnlockedLevel() < session.getMaxLevel())
			session.unlockNextLevel();
		assertThrows(IllegalStateException.class, () -> session.unlockNextLevel());
	}
	

	
	@Test
	public void testLevelUnlock() {
		// Test Unlock Next Level
		for(int i = 1;i < 12; i++) {
			session.unlockNextLevel();
		}	
		assertFalse(session.getUnlockedLevel() > 12 || session.getUnlockedLevel() < 1  );
	}
	
	
	
	
	@Test
	public void testGame() {
		
	}
	
	@Test
	public void testCode() {
		
	}
	
	
	


}
