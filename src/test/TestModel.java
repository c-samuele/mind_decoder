package test;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class TestModel {

	private String name = "player1";
	private GameMode mode;
	private Session session;
	Code c1,c2;
	
	@Before
	public void setUp() {
		Player player = new PlayerImpl(name);
		mode = GameMode.SINGLE_PLAYER;
		session = new SessionImpl(player,Optional.empty());
		session.createNewGame(mode,session.getUnlockedLevel());

		c1 = new CodeImpl(Color.RED, Color.BLUE, Color.GREEN,Color.PINK);
		c2 = new CodeImpl(Color.PINK);
	}
	
	@Test
	public void testBasicSession() {
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
	public void testVerifyColorAttempt() {
		assertThrows(IllegalArgumentException.class,() -> session.getCurrentGame().makeAttempt(c1)); // Attempt with too many colors
		assertThrows(IllegalArgumentException.class,() -> session.getCurrentGame().makeAttempt(c2)); // Attempt with too few colors
	}
	
	
	@Test
	public void testMakeAttempt() {
		
	}
	
	
	
	
	@Test
	public void testGame() {
		
	}
	
	@Test
	public void testCode() {
		
	}
	
	
	


}
