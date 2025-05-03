package Test;
import model.*;

import static org.junit.Assert.assertEquals;

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
	session = new GameSessionImpl(player,mode,stats);
	}
	
	@Test
	public void testSession() {
		assertEquals(name,session.getPlayer().getName());
		assertEquals(mode,session.getMode());
		assertEquals(1,session.getLevel());
	}
	
	@Test
	public void testGame() {
		
	}
	
	@Test
	public void testCode() {
		
	}
	
	
	


}
