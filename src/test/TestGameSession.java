package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;

public class TestGameSession {
	
	private String name;
	private Player player;
	private GameMode mode;
	private GameSession session;

// --- SET UP -------------------------------------------- //
	
	@BeforeEach
	public void Setup() {
		name = "Samuele";
		player = new PlayerImpl(name);
		mode = GameMode.SINGLE_PLAYER;
		session = new GameSessionImpl(player);
	}
	
// --- GAME TEST --------------------------------------- //
	@Test 
	public void SessionCreate() {
		assertFalse("All'inizio non ci sono partite attive",session.hasActiveGame());
	}
	
// --- PLAYER TEST --------------------------------------- //
	
	@Test 
	public void  PlayerNotNull() {
		assertNotNull(session.getPlayer());
	}
	
	@Test
	public void PlayerCorrect() {
		assertTrue(session.getPlayer().equals(player));
	}
	

	
// --- LEVEL TEST --------------------------------------- //
// ---  TEST --------------------------------------- //
// --- TEST --------------------------------------- //
// --- TEST --------------------------------------- //
// --- TEST --------------------------------------- //
// --- TEST --------------------------------------- //

	
// --- LOAD TEST --------------------------------------- //
// --- SAVE TEST --------------------------------------- //

	
	
}




